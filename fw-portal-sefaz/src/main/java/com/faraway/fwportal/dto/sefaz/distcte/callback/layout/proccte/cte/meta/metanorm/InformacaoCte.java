package com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.metanorm;

import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.metanorm.carga.CargaLayout;
import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.metanorm.doc.Doc;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InformacaoCte {

	@JsonProperty("infCarga")
	private CargaLayout infoCarga;

	@JsonProperty("infDoc")
	private Doc infoDoc;

	public CargaLayout getInfoCarga() {
		return infoCarga;
	}

	public Doc getInfoDoc() {
		return infoDoc;
	}
}
