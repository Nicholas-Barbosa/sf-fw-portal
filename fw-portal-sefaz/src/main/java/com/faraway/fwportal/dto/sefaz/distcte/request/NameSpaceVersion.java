package com.faraway.fwportal.dto.sefaz.distcte.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class NameSpaceVersion {

	@JacksonXmlProperty(isAttribute = true)
	private String versao = "1.00";

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

}
