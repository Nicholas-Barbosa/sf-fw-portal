package com.faraway.fwportal.dto;

import com.faraway.fwportal.model.domain.Cidade;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CidadeDto {

	private String nome;
	private String uf;
	@JsonProperty("codigo")
	private String codigoIbge;

	public CidadeDto(Cidade cidade) {
		super();
		this.nome = cidade.getNome();
		this.uf = cidade.getUf();
		this.codigoIbge = cidade.getCodigoIbge();
	}

	public String getNome() {
		return nome;
	}

	public String getUf() {
		return uf;
	}

	public String getCodigoIbge() {
		return codigoIbge;
	}

}
