package com.faraway.fwportal.dto;

import com.faraway.fwportal.model.domain.Endereco;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonInclude(Include.NON_NULL)
public class SuperEntidadeDto {

	@JsonProperty("cnpj")
	private String cnpj;

	@JsonProperty("nome")
	private String nome;

	private EnderecoDto endereco;
	
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

	public EnderecoDto getEndereco() {
		return endereco;
	}

	public final void setEndereco(Endereco endereco) {
		this.endereco = new EnderecoDto(endereco);
	}

	
}
