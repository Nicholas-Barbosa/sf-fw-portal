package com.faraway.fwportal.dto.sefaz.distcte.callback;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "docZip")
public class DocumentosZipDto {

	@JacksonXmlProperty(localName = "schema", isAttribute = true)
	private String schema;

	@JacksonXmlProperty(localName = "NSU", isAttribute = true)
	private String nsu;

	@JacksonXmlText
	private String docZip;

	public String getSchema() {
		return schema;
	}

	public String getNsu() {
		return nsu;
	}

	public String getDocZip() {
		return docZip;
	}
}
