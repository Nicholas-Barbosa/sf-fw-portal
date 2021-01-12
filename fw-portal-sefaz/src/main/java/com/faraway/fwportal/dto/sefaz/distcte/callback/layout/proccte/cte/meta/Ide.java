package com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ide {

	@JacksonXmlProperty(localName = "cUF")
	private String uf;

	@JacksonXmlProperty(localName = "nCT")
	private String nCte;

	private String serie;

	@JacksonXmlProperty(localName = "dhEmi")
	private String dataEmissao;

	@JacksonXmlProperty(localName = "cMunIni")
	private String municipioCodigo;

	@JacksonXmlProperty(localName = "xMunIni")
	private String municipioInicio;

	@JacksonXmlProperty(localName = "UFIni")
	private String ufInicio;

	@JacksonXmlProperty(localName = "cMunFim")
	private String municipioFimCodigo;

	@JacksonXmlProperty(localName = "xMunFim")
	private String municipioFim;

	@JacksonXmlProperty(localName = "UFFim")
	private String ufFim;

	public String getUf() {
		return uf;
	}

	public String getnCte() {
		return nCte;
	}

	public String getSerie() {
		return serie;
	}

	public String getDataEmissao() {
		return dataEmissao;
	}

	public String getMunicipioCodigo() {
		return municipioCodigo;
	}

	public String getMunicipioInicio() {
		return municipioInicio;
	}

	public String getMunicipioFimCodigo() {
		return municipioFimCodigo;
	}

	public String getMunicipioFim() {
		return municipioFim;
	}

	public String getUfInicio() {
		return ufInicio;
	}

	public String getUfFim() {
		return ufFim;
	}

	
}
