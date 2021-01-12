package com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.fluxo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EnderecoLayout {

	@JacksonXmlProperty(localName = "xLgr")
	private String logradouro;

	@JacksonXmlProperty(localName = "nro")
	private String numero;

	@JacksonXmlProperty(localName = "xBairro")
	private String bairro;

	@JacksonXmlProperty(localName = "cMun")
	private String codigoMunicipio;

	@JacksonXmlProperty(localName = "xMun")
	private String municipio;

	@JacksonXmlProperty(localName = "CEP")
	private String cep;

	@JacksonXmlProperty(localName = "UF")
	private String uf;

	@JacksonXmlProperty(localName = "fone")
	private String fone;

	@JacksonXmlProperty(localName = "xPais")
	private String pais;

	public String getLogradouro() {
		return logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCodigoMunicipio() {
		return codigoMunicipio;
	}

	public String getMunicipio() {
		return municipio;
	}

	public String getCep() {
		return cep;
	}

	public String getUf() {
		return uf;
	}

	public String getFone() {
		return fone;
	}

	public String getPais() {
		return pais;
	}
}
