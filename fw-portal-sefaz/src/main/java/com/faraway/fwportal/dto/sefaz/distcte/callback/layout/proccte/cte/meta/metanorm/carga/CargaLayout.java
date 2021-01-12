package com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.metanorm.carga;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CargaLayout {

	private Double vCarga;

	@JsonProperty("proPred")
	private String produtoPredominante;

	@JacksonXmlElementWrapper(useWrapping = false)
	@JsonProperty("infQ")
	private List<QuantidadeCargaLayout> quantidades;

	public Double getvCarga() {
		return vCarga;
	}

	public String getProdutoPredominante() {
		return produtoPredominante;
	}

	public List<QuantidadeCargaLayout> getQuantidades() {
		return quantidades;
	}

}
