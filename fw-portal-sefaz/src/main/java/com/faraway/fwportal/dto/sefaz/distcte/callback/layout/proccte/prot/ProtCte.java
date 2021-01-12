package com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.prot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProtCte {

	@JsonProperty("infProt")
	private InfProt infoProt;

	public InfProt getInfoProt() {
		return infoProt;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "chave=" + infoProt.getChaveCte();
	}

	
}
