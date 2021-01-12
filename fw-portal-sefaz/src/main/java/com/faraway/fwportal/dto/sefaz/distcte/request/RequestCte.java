package com.faraway.fwportal.dto.sefaz.distcte.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "distDFeInt", namespace = "http://www.portalfiscal.inf.br/cte")
public class RequestCte extends NameSpaceVersion{

	

	@JacksonXmlProperty(namespace = "http://www.portalfiscal.inf.br/cte")
	private String tpAmb;

	@JacksonXmlProperty(localName = "cUFAutor", namespace = "http://www.portalfiscal.inf.br/cte")
	private String ufAutor;

	@JacksonXmlProperty(localName = "CNPJ", namespace = "http://www.portalfiscal.inf.br/cte")
	private String cnpj;

	@JacksonXmlProperty(localName = "distNSU", namespace = "http://www.portalfiscal.inf.br/cte")
	private DistNsu distNsu;

	public RequestCte(String tpAmb, String ufAutor, String cnpj, DistNsu distNsu) {
		super();
		this.tpAmb = tpAmb;
		this.ufAutor = ufAutor;
		this.cnpj = cnpj;
		this.distNsu = distNsu;
	}

	public RequestCte() {
		// TODO Auto-generated constructor stub
	}


	public String getTpAmb() {
		return tpAmb;
	}

	public String getUfAutor() {
		return ufAutor;
	}

	public String getCnpj() {
		return cnpj;
	}

	public DistNsu getDistNsu() {
		return distNsu;
	}


	public String toSerialize() throws JsonProcessingException {
		XmlMapper xmlMapper = new XmlMapper();
		String xml = xmlMapper.writeValueAsString(this);
		return xml;
	}
}
