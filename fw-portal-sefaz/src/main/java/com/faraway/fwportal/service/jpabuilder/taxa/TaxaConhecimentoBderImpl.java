package com.faraway.fwportal.service.jpabuilder.taxa;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.faraway.fwportal.model.TaxaConhecimento;

@Component
public class TaxaConhecimentoBderImpl implements TaxaConhecimentoBder {

	private final TaxaBder taxaBuilder;

	public TaxaConhecimentoBderImpl(TaxaBder taxaBuilder) {
		super();
		this.taxaBuilder = taxaBuilder;
	}

	@Override
	public TaxaConhecimento toJpaEntity(String object) {
		String splitted[] = object.split(",");

		String nome = splitted[0].strip();
		BigDecimal valor = new BigDecimal(splitted[1].strip());

		TaxaConhecimento taxaCte = new TaxaConhecimento(taxaBuilder.toJpaEntity(nome), valor);

		return taxaCte;
	}

}
