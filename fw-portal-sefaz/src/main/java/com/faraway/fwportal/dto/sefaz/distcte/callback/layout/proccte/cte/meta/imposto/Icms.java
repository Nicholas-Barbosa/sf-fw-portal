package com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.imposto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Icms {

	@JsonProperty("ICMS00")
	private Icms00 icm00;

	public Icms00 getIcms00() {
		return icm00;
	}

	
	
}
