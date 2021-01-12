package com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CteComplementarLayout {

	@JsonProperty("chCTe")
	private String chave;

	public String getChave() {
		return chave;
	}
}
