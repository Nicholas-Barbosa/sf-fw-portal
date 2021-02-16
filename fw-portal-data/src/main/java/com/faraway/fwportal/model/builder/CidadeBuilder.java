package com.faraway.fwportal.model.builder;

import com.faraway.fwportal.model.domain.Cidade;

public class CidadeBuilder implements Builder<CidadeBuilder, Cidade> {

	private String nome, uf, codigoIbge;

	@Override
	public CidadeBuilder builder() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Cidade build() {
		// TODO Auto-generated method stub
		return new Cidade(nome, uf, codigoIbge);
	}

	public CidadeBuilder nome(String nome) {
		this.nome = nome;

		return this;
	}

	public CidadeBuilder uf(String uf) {
		this.uf = uf;
		return this;
	}

	public CidadeBuilder codigoIbge(String codigo) {
		this.codigoIbge = codigo;
		return this;
	}

}
