package com.faraway.fwportal.service.jpabuilder.taxa;

import org.springframework.stereotype.Component;

import com.faraway.fwportal.model.Taxa;
import com.faraway.fwportal.service.TaxaCrdService;

@Component
public class TaxaBderImpl implements TaxaBder {

	private final TaxaCrdService taxaCrudService;

	public TaxaBderImpl(TaxaCrdService taxaCrudService) {
		super();
		this.taxaCrudService = taxaCrudService;
	}

	@Override
	public Taxa toJpaEntity(String nome) {
		// TODO Auto-generated method stub
		return taxaCrudService.save(new Taxa(nome));
	}

}
