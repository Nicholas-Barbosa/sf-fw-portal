package com.faraway.fwportal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NotaFarawayDto {

	@JsonProperty("Notas")
	private ResultNotaDto notas;

	public ResultNotaDto getNotas() {
		return notas;
	}

}
