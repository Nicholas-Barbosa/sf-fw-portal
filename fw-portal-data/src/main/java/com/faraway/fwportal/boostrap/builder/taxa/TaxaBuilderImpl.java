package com.faraway.fwportal.boostrap.builder.taxa;

import org.springframework.stereotype.Component;

import com.faraway.fwportal.model.Taxa;

@Component
public class TaxaBuilderImpl implements TaxaBuilder {

	@Override
	public Taxa buildObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Taxa buildObjectWithTaxa(String taxa) {
		// TODO Auto-generated method stub
		return new Taxa(taxa);
	}

}
