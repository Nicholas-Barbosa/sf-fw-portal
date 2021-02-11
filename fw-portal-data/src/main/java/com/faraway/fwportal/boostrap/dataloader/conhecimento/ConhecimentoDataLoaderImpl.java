package com.faraway.fwportal.boostrap.dataloader.conhecimento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.faraway.fwportal.boostrap.builder.conhecimento.ConhecimentoBuilder;
import com.faraway.fwportal.model.Carga;
import com.faraway.fwportal.model.Cidade;
import com.faraway.fwportal.model.Conhecimento;
import com.faraway.fwportal.model.Empresa;
import com.faraway.fwportal.model.Entidade;
import com.faraway.fwportal.model.Imposto;
import com.faraway.fwportal.model.Nota;
import com.faraway.fwportal.model.TaxaConhecimento;
import com.faraway.fwportal.model.Transportadora;
import com.faraway.fwportal.service.ConhecimentoCrdService;

public class ConhecimentoDataLoaderImpl implements ConhecimentoDataLoader {

	private final ConhecimentoBuilder conhecimentoBuilder;
	private final ConhecimentoCrdService conhecimentoCrudService;

	public ConhecimentoDataLoaderImpl(ConhecimentoBuilder conhecimentoBuilder,
			ConhecimentoCrdService conhecimentoCrudService) {
		super();
		this.conhecimentoBuilder = conhecimentoBuilder;
		this.conhecimentoCrudService = conhecimentoCrudService;
	}

	@Override
	public Conhecimento load() {
		return conhecimentoCrudService.save(conhecimentoBuilder.buildObject());
	}

	@Override
	public ConhecimentoDataLoader setNumeroAndSerieAndChaveAndEmissaoAndTotal(Object... args) {
		if (args.length < 5) {
			throw new IllegalArgumentException("Invalid array lenght.Should containg five elements!");
		}
		conhecimentoBuilder.setNumero((String) args[0]).setSerie(((String) args[1])).setChave(((String) args[2]))
				.setEmissao(((LocalDate) args[3])).setTotal(((BigDecimal) args[4]));
		return this;
	}

	@Override
	public ConhecimentoDataLoader setCidadeAndCidadeDestino(Cidade... args) {
		conhecimentoBuilder.setCidadeInicio(new Cidade(args[0])).setCidadeDestino(new Cidade(args[1]));
		return this;
	}

	@Override
	public ConhecimentoDataLoader setEmitenteAndRemetenteAndDestinatario(Entidade... args) {
		conhecimentoBuilder.setEmitente(new Transportadora((Transportadora) args[0]))
				.setRemetente(new Empresa(((Empresa) args[1]))).setDestinatario(new Empresa(((Empresa) args[2])));
		return this;
	}

	@Override
	public ConhecimentoDataLoader setNotasAndTaxasCollection(Set<Nota> notas, Set<TaxaConhecimento> taxas) {
		conhecimentoBuilder.setNotas(new HashSet<>(notas)).setTaxas(new HashSet<>(taxas));
		return this;
	}

	@Override
	public ConhecimentoDataLoader setImpostoAndCarga(Imposto imposto, Carga carga) {
		conhecimentoBuilder.setImposto(new Imposto(imposto)).setCarga(new Carga(carga));
		return this;
	}

}
