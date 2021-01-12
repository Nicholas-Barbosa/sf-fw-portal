package com.faraway.fwportal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultNotaDto {

	@JsonProperty("NUMERO")
	private String numero;
	@JsonProperty("SERIE")
	private Integer serie;
	@JsonProperty("CHAVE")
	private String chave;

	public ResultNotaDto() {
		// TODO Auto-generated constructor stub
	}

	public ResultNotaDto(String numero, Integer serie, String chave) {
		super();
		this.numero = numero;
		this.serie = serie;
		this.chave = chave;
	}

	public String getNumero() {
		return numero;
	}

	public Integer getSerie() {
		return serie;
	}

	public String getChave() {
		return chave;
	}

}
