package com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.metanorm.doc;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Doc {

	@JacksonXmlElementWrapper(useWrapping = false)
	@JsonProperty("infNFe")
	private Set<NfeSfz> infoNfe;

	public Set<NfeSfz> getInfoNfe() {
		if (infoNfe == null) {
			infoNfe = new HashSet<NfeSfz>();
		}
		return new HashSet<NfeSfz>(infoNfe);
	}
}
