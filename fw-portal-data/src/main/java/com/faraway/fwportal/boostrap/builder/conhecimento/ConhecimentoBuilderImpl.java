package com.faraway.fwportal.boostrap.builder.conhecimento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.faraway.fwportal.model.Carga;
import com.faraway.fwportal.model.Cidade;
import com.faraway.fwportal.model.Conhecimento;
import com.faraway.fwportal.model.Empresa;
import com.faraway.fwportal.model.Imposto;
import com.faraway.fwportal.model.Nota;
import com.faraway.fwportal.model.TaxaConhecimento;
import com.faraway.fwportal.model.Transportadora;

public class ConhecimentoBuilderImpl implements ConhecimentoBuilder {

	private static final Logger log = LoggerFactory.getLogger(ConhecimentoBuilderImpl.class);

	private String numero, serie, chave;
	private LocalDate emissao;
	private BigDecimal total;
	private Cidade cidadeInicio, cidadeDestino;
	private Transportadora emitente;
	private Empresa remetente, destinatario;
	private Set<Nota> notas = new HashSet<>();
	private Imposto imposto;
	private Carga carga;
	private Set<TaxaConhecimento> taxas = new HashSet<>();

	@Override
	public Conhecimento buildObject() {
		// TODO Auto-generated method stub
		log.info("Conhecimento object has been created.");
		return new Conhecimento(numero, serie, emissao, total, cidadeInicio, cidadeDestino, emitente, remetente,
				destinatario, notas, imposto, carga, chave, taxas);
	}

	@Override
	public ConhecimentoBuilder setNumero(String numero) {
		this.numero = numero;
		return this;
	}

	@Override
	public ConhecimentoBuilder setSerie(String serie) {
		this.serie = serie;
		return this;
	}

	@Override
	public ConhecimentoBuilder setChave(String chave) {
		this.chave = chave;
		return this;
	}

	@Override
	public ConhecimentoBuilder setEmissao(LocalDate emissao) {
		this.emissao = emissao;
		return this;
	}

	@Override
	public ConhecimentoBuilder setTotal(BigDecimal total) {
		this.total = total;
		return this;
	}

	@Override
	public ConhecimentoBuilder setCidadeInicio(Cidade cidade) {
		this.cidadeInicio = new Cidade(cidade);
		return this;
	}

	@Override
	public ConhecimentoBuilder setCidadeDestino(Cidade cidade) {
		this.cidadeDestino = new Cidade(cidade);
		return this;
	}

	@Override
	public ConhecimentoBuilder setEmitente(Transportadora emitente) {
		this.emitente = new Transportadora(emitente);
		return this;
	}

	@Override
	public ConhecimentoBuilder setRemetente(Empresa remetente) {
		this.remetente = new Empresa(remetente);
		return this;
	}

	@Override
	public ConhecimentoBuilder setDestinatario(Empresa destinatario) {
		this.destinatario = new Empresa(destinatario);
		return this;
	}

	@Override
	public ConhecimentoBuilder setNotas(Set<Nota> notas) {
		this.notas = new HashSet<>(notas);
		return this;
	}

	@Override
	public ConhecimentoBuilder setImposto(Imposto imposto) {
		this.imposto = new Imposto(imposto);
		return this;
	}

	@Override
	public ConhecimentoBuilder setCarga(Carga carga) {
		this.carga = new Carga(carga);
		return this;
	}

	@Override
	public ConhecimentoBuilder setTaxas(Set<TaxaConhecimento> taxas) {
		this.taxas = new HashSet<>(taxas);
		return this;
	}

}
