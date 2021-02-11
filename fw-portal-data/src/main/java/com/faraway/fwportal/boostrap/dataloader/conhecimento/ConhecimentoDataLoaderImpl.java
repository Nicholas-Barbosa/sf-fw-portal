package com.faraway.fwportal.boostrap.dataloader.conhecimento;

import java.util.Set;

import com.faraway.fwportal.boostrap.builder.conhecimento.ConhecimentoBuilder;
import com.faraway.fwportal.model.Carga;
import com.faraway.fwportal.model.Cidade;
import com.faraway.fwportal.model.Conhecimento;
import com.faraway.fwportal.model.Entidade;
import com.faraway.fwportal.model.Imposto;
import com.faraway.fwportal.model.Nota;
import com.faraway.fwportal.model.TaxaConhecimento;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConhecimentoDataLoader setNumeroAndSerieAndChaveAndEmissaoAndTotal(Object... args) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public ConhecimentoDataLoader setCidadeAndCidadeDestino(Cidade... args) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public ConhecimentoDataLoader setEmitenteAndRemetenteAndDestinatario(Entidade... args) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public ConhecimentoDataLoader setNotasAndTaxasCollection(Set<Nota> notas, Set<TaxaConhecimento> taxas) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public ConhecimentoDataLoader setImpostoAndCarga(Imposto imposto, Carga carga) {
		// TODO Auto-generated method stub
		return null;
	}

}
