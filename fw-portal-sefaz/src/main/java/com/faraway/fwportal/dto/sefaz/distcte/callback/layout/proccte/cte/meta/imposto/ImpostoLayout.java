package com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.imposto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImpostoLayout {

	@JsonProperty("ICMS")
	private Icms icms;

	public Icms getIcms() {
		return icms;
	}
}
