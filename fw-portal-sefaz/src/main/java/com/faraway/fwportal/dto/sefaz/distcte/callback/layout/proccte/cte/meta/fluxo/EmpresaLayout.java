package com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.fluxo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmpresaLayout {

	@JacksonXmlProperty(localName = "CNPJ")
	private String cnpj;

	@JacksonXmlProperty(localName = "xNome")
	private String nome;

	public String getCnpj() {
		return cnpj;
	}

	public final void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public String getNome() {
		return nome;
	}

	public final void setNome(String nome) {
		this.nome = nome;
	}
}
