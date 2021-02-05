package com.faraway.fwportal.boostrap.builder.endereco;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.faraway.fwportal.boostrap.builder.cidade.CidadeBuilder;
import com.faraway.fwportal.model.Cidade;
import com.faraway.fwportal.model.Endereco;

public class EnderecoBuilderImpl implements EnderecoBuilder {

	private static final Logger log = LoggerFactory.getLogger(EnderecoBuilderImpl.class);

	private final CidadeBuilder cidadeBuilder;
	private Cidade cidade;

	public EnderecoBuilderImpl(CidadeBuilder cidadeBuilder, Cidade cidade) {
		super();
		this.cidadeBuilder = cidadeBuilder;
		this.cidade = cidade;
	}

	@Override
	public Endereco buildObject(Object... args) {
		// TODO Auto-generated method stub
		log.info("Creating Cidade Object...");
		if (cidade == null) {
			throw new RuntimeException("Cidade attribute cannot be null when you call this method!");
		}
		String logradouro = (String) args[0];
		String numero = (String) args[1];
		String cep = (String) args[2];
		String bairro = (String) args[3];
		String pais = (String) args[4];
		log.info("Cidade Object has been created!");
		return new Endereco(logradouro, numero, cep, cidade, bairro, pais);
	}

	@Override
	public EnderecoBuilder setCidade(Object... args) {
		cidade = cidadeBuilder.buildObject(args);
		return this;
	}

}
