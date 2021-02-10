package com.faraway.fwportal.boostrap.builder.entidade.transportadora;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.faraway.fwportal.boostrap.builder.endereco.EnderecoBuilder;
import com.faraway.fwportal.model.Endereco;
import com.faraway.fwportal.model.Transportadora;

@Component
public class TransportadoraBuilderImpl implements TransportadoraBuilder {

	private static final Logger log = LoggerFactory.getLogger(TransportadoraBuilderImpl.class);
	private final EnderecoBuilder enderecoBuilder;
	private Endereco endereco;
	private String nome, cnpj, inscEstadual, fone;

	public TransportadoraBuilderImpl(EnderecoBuilder enderecoBuilder) {
		super();
		this.enderecoBuilder = enderecoBuilder;
	}

	@Override
	public Transportadora buildObject() {
		log.info("Transportadora object has been created.");
		return new Transportadora(nome, cnpj, endereco, inscEstadual, fone);
	}

	@Override
	public TransportadoraBuilder setEndereco(Object... args) {
		log.info("Setting endereco for Transportadora Object...");
		if (args.length < 5) {
			throw new RuntimeException("Invaid array length!");
		}
		Object[] cidadeProperties = Arrays.stream(args, 5, args.length).toArray();
		endereco = enderecoBuilder.setCidade(cidadeProperties).setLogradouro((String) args[0])
				.setBairro((String) args[1]).setCep((String) args[2]).setNumero((String) args[3])
				.setPais((String) args[4]).buildObject();
		log.info("Transportadora has been setted!");
		return this;
	}

	@Override
	public TransportadoraBuilderImpl setNome(String nome) {
		this.nome = nome;
		return this;
	}

	@Override
	public TransportadoraBuilderImpl setCnpj(String cnpj) {
		this.cnpj = cnpj;
		return this;
	}

	@Override
	public TransportadoraBuilderImpl setInscEstadual(String inscEstadual) {
		this.inscEstadual = inscEstadual;
		return this;
	}

	@Override
	public TransportadoraBuilderImpl setFone(String fone) {
		this.fone = fone;
		return this;
	}

	@Override
	public TransportadoraBuilder setEndereco(Endereco endereco) {
		endereco = new Endereco(endereco);
		return this;
	}

}
