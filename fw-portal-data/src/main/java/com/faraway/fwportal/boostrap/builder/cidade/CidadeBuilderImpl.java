package com.faraway.fwportal.boostrap.builder.cidade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.faraway.fwportal.model.Cidade;

@Component
public class CidadeBuilderImpl implements CidadeBuilder {

	private static final Logger log = LoggerFactory.getLogger(CidadeBuilderImpl.class);

	@Override
	public Cidade buildObject(Object... args) {
		log.info("Creating Cidade Object...");
		if (args.length < 3) {
			throw new IllegalArgumentException("Invalid array length.");
		}
		String nome = ((String) args[0]);
		String uf = ((String) args[1]);
		String codigoIbge = ((String) args[2]);
		log.info("Cidade Object has been created!");
		return new Cidade(nome, uf, codigoIbge);
	}
}
