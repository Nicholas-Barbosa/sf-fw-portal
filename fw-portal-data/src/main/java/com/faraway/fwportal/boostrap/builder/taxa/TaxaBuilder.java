package com.faraway.fwportal.boostrap.builder.taxa;

import com.faraway.fwportal.boostrap.builder.Builder;
import com.faraway.fwportal.model.domain.Taxa;

public interface TaxaBuilder extends Builder<Taxa> {

	Taxa buildObjectWithTaxa(String taxa);
}
