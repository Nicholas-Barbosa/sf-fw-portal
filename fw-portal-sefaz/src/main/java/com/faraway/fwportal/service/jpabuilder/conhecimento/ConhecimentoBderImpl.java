package com.faraway.fwportal.service.jpabuilder.conhecimento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.CteProc;
import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.Ide;
import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.fluxo.EnderecoLayout;
import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.imposto.Icms00;
import com.faraway.fwportal.model.Carga;
import com.faraway.fwportal.model.Cidade;
import com.faraway.fwportal.model.Conhecimento;
import com.faraway.fwportal.model.Empresa;
import com.faraway.fwportal.model.Endereco;
import com.faraway.fwportal.model.Imposto;
import com.faraway.fwportal.model.Nota;
import com.faraway.fwportal.model.TaxaConhecimento;
import com.faraway.fwportal.model.Transportadora;
import com.faraway.fwportal.service.ConhecimentoCrdService;
import com.faraway.fwportal.service.jpabuilder.carga.CargaBder;
import com.faraway.fwportal.service.jpabuilder.cidade.CidadeBder;
import com.faraway.fwportal.service.jpabuilder.entidade.EmpresaBder;
import com.faraway.fwportal.service.jpabuilder.entidade.TransportadoraBder;
import com.faraway.fwportal.service.jpabuilder.nota.NotaBder;
import com.faraway.fwportal.service.jpabuilder.taxa.TaxaConhecimentoBder;

@Component
public class ConhecimentoBderImpl implements ConhecimentoBder {

	private static final Logger log = LoggerFactory.getLogger(ConhecimentoBderImpl.class);

	private final CidadeBder cidadeBuilder;

	private final EmpresaBder empresaBuilder;

	private final TransportadoraBder transportadoraBuilder;

	private final TaxaConhecimentoBder taxaCteBuilder;

	private final CargaBder cargaBuider;

	private final NotaBder notaBuilder;

	private final ConhecimentoCrdService conhecimentoCrudService;

	public boolean isExisting(String key) {
		return conhecimentoCrudService.existsKeyOnMap(key);
	}

	public ConhecimentoBderImpl(CidadeBder cidadeBuilder, EmpresaBder empresaBuilder,
			TransportadoraBder transportadoraBuilder, TaxaConhecimentoBder taxaCteBuilder, CargaBder cargaBuider,
			NotaBder notaBuilder, ConhecimentoCrdService conhecimentoCrudService) {
		super();
		this.cidadeBuilder = cidadeBuilder;
		this.empresaBuilder = empresaBuilder;
		this.transportadoraBuilder = transportadoraBuilder;
		this.taxaCteBuilder = taxaCteBuilder;
		this.cargaBuider = cargaBuider;
		this.notaBuilder = notaBuilder;
		this.conhecimentoCrudService = conhecimentoCrudService;
	}

	@Override
	public Conhecimento toJpaEntity(CteProc obj) {
		return toConhecimento(obj);
	}

	private Conhecimento toConhecimento(CteProc cte) {
		log.info("Converting to conhecimento JPA entity...");
		ExecutorService executor = null;
		Carga carga = null;
		try {

			executor = Executors.newFixedThreadPool(9);

			Future<Cidade> cidadeInicioFuture = executor.submit(() -> getCidade(cte.getCidadeInicio()));

			Future<Cidade> cidadeFimFuture = executor.submit(() -> getCidade(cte.getCidadeFim()));

			Future<Transportadora> emitenteFuture = executor.submit(() -> getTransportadora(cte.getEmitente().getNome(),
					cte.getEmitente().getCnpj(), cte.getEmitente().getEndereco()));

			Future<Empresa> remetenteFuture = executor.submit(() -> getEmpresa(cte.getRemetente().getNome(),
					cte.getRemetente().getCnpj(), cte.getRemetente().getEndereco()));

			Future<Empresa> destinatarioFuture = executor.submit(() -> getEmpresa(cte.getDestinatario().getNome(),
					cte.getDestinatario().getCnpj(), cte.getDestinatario().getEndereco()));

			Future<Set<TaxaConhecimento>> valoresFuture = executor
					.submit(() -> cte.getValores().parallelStream().map(x -> {
						String toSplit = x.getNome() + "," + x.getValor();
						return taxaCteBuilder.toJpaEntity(toSplit);
					}).collect(ConcurrentSkipListSet::new, Set::add, Set::addAll));

			Future<Imposto> impostoFuture = executor.submit(() -> this.getImposto(cte.getImpostos()));

			Future<Carga> cargaFuture = executor.submit(() -> cargaBuider.toJpaEntity(cte.getCarga()));

			Empresa rementente = remetenteFuture.get();
			Empresa destinarario = destinatarioFuture.get();

			Future<Set<Nota>> notasFuture = executor.submit(() -> cte.getNotas().parallelStream()
					.map(n -> notaBuilder.toJpaEntity(n.getChave(), rementente, destinarario))
					.collect(CopyOnWriteArraySet::new, Set::add, Set::addAll));

			Ide ide = cte.getInfoCte();
			String numero = ide.getnCte();
			String serie = ide.getSerie();

			String dataToParse = ide.getDataEmissao().split("T")[0];

			LocalDate emissao = LocalDate.parse(dataToParse, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			BigDecimal total = cte.getTotal();

			Conhecimento conhecimento = new Conhecimento(numero, serie, emissao, total, cidadeInicioFuture.get(),
					cidadeFimFuture.get(), emitenteFuture.get(), rementente, destinarario, notasFuture.get(),
					impostoFuture.get(), (carga = cargaFuture.get()), cte.getChave(), valoresFuture.get());
			conhecimento.setConhecimentoForTaxas();
			log.info("Conhecimento successfully converted!");
			return conhecimentoCrudService.save(conhecimento);

		} catch (Exception e) {
			log.info("Error while converting to conhecimento JPA entity! #" + cte.getChave(), e);
			cargaBuider.delete(carga);
			return null;
		} finally {
			executor.shutdown();
		}

	}

	private Cidade getCidade(String cit) {
		return cidadeBuilder.toJpaEntity(cit);
	}

	private Empresa getEmpresa(String nome, String cnpj, EnderecoLayout en) {
		String toSplit = nome + "," + cnpj;
		return empresaBuilder.toJpaEntity(toSplit, getEnderecoForEntities(en));
	}

	private Transportadora getTransportadora(String nome, String cnpj, EnderecoLayout en) {
		String toSplit = nome + "," + cnpj;
		return transportadoraBuilder.toJpaEntity(toSplit, getEnderecoForEntities(en));
	}

	private Endereco getEnderecoForEntities(EnderecoLayout endereco) {
		String cidadeSplit = endereco.getMunicipio() + "," + endereco.getCodigoMunicipio() + "," + endereco.getUf();
		Endereco enderecoEnt = new Endereco(endereco.getLogradouro(), endereco.getNumero(), endereco.getCep(),
				getCidade(cidadeSplit));
		return enderecoEnt;
	}

	private Imposto getImposto(Icms00 icms) {
		BigDecimal baseCalculo = new BigDecimal(icms.getBaseCalculo());
		Float porcentagemIcms = icms.getAliquotaIcms();
		BigDecimal vIcms = new BigDecimal(icms.getValorIcms());
		return new Imposto(baseCalculo, porcentagemIcms, vIcms);
	}
}
