package com.faraway.fwportal.dto.sefaz.distcte.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class DistNsu {

	@JacksonXmlProperty(localName = "ultNSU", namespace = "http://www.portalfiscal.inf.br/cte")
	private String ultNsu;

	public DistNsu(String ultNsu) {
		super();
		this.ultNsu = ultNsu;
	}

	public DistNsu() {
		// TODO Auto-generated constructor stub
	}

	public String getUltNsu() {
		return ultNsu;
	}
}
