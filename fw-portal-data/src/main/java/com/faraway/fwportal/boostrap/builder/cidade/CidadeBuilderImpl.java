package com.faraway.fwportal.boostrap.builder.cidade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.faraway.fwportal.model.Cidade;

@Component
public class CidadeBuilderImpl implements CidadeBuilder {

	private static final Logger log = LoggerFactory.getLogger(CidadeBuilderImpl.class);
	private String nome, uf, codigoIbge;

	@Override
	public Cidade buildObject() {
		log.info("Creating Cidade Object...");
		return new Cidade(nome, uf, codigoIbge);
	}

	@Override
	public CidadeBuilder setNome(String nome) {
		this.nome = nome;

		return this;
	}

	@Override
	public CidadeBuilder setUf(String uf) {
		this.uf = uf;
		return this;
	}

	@Override
	public CidadeBuilder setCodigoIbge(String codigo) {
		this.codigoIbge = codigo;
		return this;
	}
}
