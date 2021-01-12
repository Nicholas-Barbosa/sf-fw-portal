package com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.prot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InfProt {

	@JsonProperty("chCTe")
	private String chaveCte;

	public String getChaveCte() {
		return chaveCte;
	}

	@Override
	public String toString() {
		return "InfProt [chaveCte=" + chaveCte + "]";
	}

}
