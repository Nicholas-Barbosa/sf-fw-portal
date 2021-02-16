package com.faraway.fwportal.boostrap.builder.taxa.conhecimento;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.faraway.fwportal.boostrap.builder.taxa.TaxaBuilder;
import com.faraway.fwportal.model.domain.Taxa;
import com.faraway.fwportal.model.domain.TaxaConhecimento;
@Component
public class TaxaConhecimentoImpl implements TaxaConhecimentoBuilder {

	private static final Logger log = LoggerFactory.getLogger(TaxaConhecimentoImpl.class);
	private Taxa taxa;
	private BigDecimal valor;

	private final TaxaBuilder taxaBuilder;

	public TaxaConhecimentoImpl(TaxaBuilder taxaBuilder) {
		super();
		this.taxaBuilder = taxaBuilder;
	}

	@Override
	public TaxaConhecimento buildObject() {
		// TODO Auto-generated method stub
		if (taxa == null) {
			throw new RuntimeException("You must set an instance for taxa reference type, before class this method!");
		}
		log.info("TaxaConhecimento object has been created!");
		return new TaxaConhecimento(taxa, valor);
	}

	@Override
	public TaxaConhecimentoBuilder setTaxa(Object... args) {
		if (args.length <= 0) {
			throw new IllegalArgumentException("Invalid array lenght.You must set taxa name attribute!");
		}
		taxa = taxaBuilder.buildObjectWithTaxa((String) args[0]);
		return this;
	}

}
