package com.faraway.fwportal.model.builder;

import com.faraway.fwportal.model.domain.Cidade;
import com.faraway.fwportal.model.domain.Endereco;

public class EnderecoBuilder implements Builder<Endereco> {

	private String logradouro, numero, cep, bairro, pais;
	private Cidade cidade;

	public EnderecoBuilder builder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Endereco build() {
		// TODO Auto-generated method stub
		return new Endereco(logradouro, numero, cep, cidade, bairro, pais);
	}

	public EnderecoBuilder cidade(Cidade cidade) {
		this.cidade = new Cidade(cidade);
		return this;
	}

	public EnderecoBuilder logradouro(String logradouro) {
		this.logradouro = logradouro;
		return this;
	}

	public EnderecoBuilder numero(String numero) {
		this.numero = numero;
		return this;
	}

	public EnderecoBuilder cep(String cep) {
		this.cep = cep;
		return this;
	}

	public EnderecoBuilder bairro(String bairro) {
		this.bairro = bairro;
		return this;
	}

	public EnderecoBuilder pais(String pais) {
		this.pais = pais;
		return this;
	}

}
