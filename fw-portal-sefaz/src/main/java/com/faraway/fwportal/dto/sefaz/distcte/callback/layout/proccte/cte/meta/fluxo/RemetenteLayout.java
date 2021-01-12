package com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.fluxo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class RemetenteLayout extends EmpresaLayout {

	@JacksonXmlProperty(localName = "enderReme")
	private EnderecoLayout endereco;

	public EnderecoLayout getEndereco() {
		return endereco;
	}

}
