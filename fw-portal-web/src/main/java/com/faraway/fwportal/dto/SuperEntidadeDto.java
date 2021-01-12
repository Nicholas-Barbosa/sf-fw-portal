package com.faraway.fwportal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SuperEntidadeDto {

	@JsonProperty("CNPJ")
	private String cnpj;

	@JsonProperty("nome")
	private String nome;

	public String getCnpj() {
		return cnpj;
	}

	public final void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public final void setNome(String nome) {
		this.nome = nome;
	}


	
	
}
