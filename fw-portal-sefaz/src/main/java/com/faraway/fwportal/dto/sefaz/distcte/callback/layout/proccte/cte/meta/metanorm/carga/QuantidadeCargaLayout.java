package com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.metanorm.carga;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuantidadeCargaLayout {

	@JsonProperty("cUnid")
	private Integer unidade;

	@JsonProperty("tpMed")
	private String medida;

	@JsonProperty("qCarga")
	private Float quantidade;

	public Integer getUnidade() {
		return unidade;
	}

	public String getMedida() {
		return medida;
	}

	public Float getQuantidade() {
		return quantidade;
	}

}
