package com.faraway.fwportal.boostrap.builder.endereco;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.faraway.fwportal.boostrap.builder.cidade.CidadeBuilder;
import com.faraway.fwportal.model.Cidade;
import com.faraway.fwportal.model.Endereco;

@Component
public class EnderecoBuilderImpl implements EnderecoBuilder {

	private static final Logger log = LoggerFactory.getLogger(EnderecoBuilderImpl.class);

	private final CidadeBuilder cidadeBuilder;
	private Cidade cidade;

	private String logradouro, numero, cep, bairro, pais;

	public EnderecoBuilderImpl(CidadeBuilder cidadeBuilder) {
		super();
		this.cidadeBuilder = cidadeBuilder;
	}

	@Override
	public Endereco buildObject() {
		// TODO Auto-generated method stub
		log.info("Creating Cidade Object...");
		if (cidade == null) {
			throw new RuntimeException("Cidade attribute cannot be null when you call this method!");
		}
		return new Endereco(logradouro, numero, cep, cidade, bairro, pais);
	}

	@Override
	public EnderecoBuilder setCidade(Object... args) {
		cidade = cidadeBuilder.setNome("São Vicente").setUf("SP").setCodigoIbge("xxx211").buildObject();
		return this;
	}

	@Override
	public EnderecoBuilder setLogradouro(String logradouro) {
		this.logradouro = logradouro;
		return this;
	}

	@Override
	public EnderecoBuilder setNumero(String numero) {
		this.numero = numero;
		return this;
	}

	@Override
	public EnderecoBuilder setCep(String cep) {
		this.cep = cep;
		return this;
	}

	@Override
	public EnderecoBuilder setBairro(String bairro) {
		this.bairro = bairro;
		return this;
	}

	@Override
	public EnderecoBuilder setPais(String pais) {
		this.pais = pais;
		return this;
	}

}
