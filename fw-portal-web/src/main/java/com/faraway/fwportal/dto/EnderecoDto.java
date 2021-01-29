package com.faraway.fwportal.dto;

import com.faraway.fwportal.model.Endereco;

public class EnderecoDto {

	private String cep;
	private String logradouro;
	private String numero;
	private CidadeDto cidade;
	
	public EnderecoDto(Endereco endereco) {
		super();
		this.cep = endereco.getCep();
		this.logradouro = endereco.getLogradouro();
		this.numero = endereco.getNumero();
		this.cidade = new CidadeDto(endereco.getCidade());
	}

	public String getCep() {
		return cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public CidadeDto getCidade() {
		return cidade;
	}
	
	
	
}
