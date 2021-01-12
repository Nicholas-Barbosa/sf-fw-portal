package com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.valor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Valor {

	@JsonProperty("vTPrest")
	private Double total;

	@JsonProperty("Comp")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<ValorComponenteLayout> componentes;

	public Double getTotal() {
		return total;
	}

	public List<ValorComponenteLayout> getComponentes() {
		return componentes;
	}
}
