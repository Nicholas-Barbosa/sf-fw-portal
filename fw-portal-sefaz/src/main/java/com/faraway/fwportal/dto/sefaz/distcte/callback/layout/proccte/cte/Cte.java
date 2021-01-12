package com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte;

import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.InfCte;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement
public class Cte {

	@JacksonXmlProperty(localName = "infCte")
	private InfCte info;

	public InfCte getInfo() {
		return info;
	}

}
