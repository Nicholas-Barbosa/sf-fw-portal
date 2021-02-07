package com.faraway.fwportal.boostrap.builder.entidade.empresa;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.faraway.fwportal.boostrap.builder.endereco.EnderecoBuilder;
import com.faraway.fwportal.model.Empresa;
import com.faraway.fwportal.model.Endereco;

@Component
public class EmpesaBuilderImpl implements EmpresaBuilder {

	private static final Logger log = LoggerFactory.getLogger(EmpesaBuilderImpl.class);
	private final EnderecoBuilder enderecoBuilder;
	private Endereco endereco;
	private String nome, cnpj, inscEstadual, fone;

	public EmpesaBuilderImpl(EnderecoBuilder enderecoBuilder) {
		super();
		this.enderecoBuilder = enderecoBuilder;
	}

	@Override
	public Empresa buildObject() {
		// TODO Auto-generated method stub

		log.info("Empresa Object has been created.");
		return new Empresa(nome, cnpj, endereco, inscEstadual, fone);
	}

	@Override
	public EmpresaBuilder setEndereco(Object... args) {
		// TODO Auto-generated method stub
		log.info("Setting endereco for Empresa Object...");
		if (args.length < 5) {
			throw new RuntimeException("Invaid array length!");
		}
		Object[] cidadeProperties = Arrays.stream(args, 5, args.length).toArray();
		endereco = enderecoBuilder.setCidade(cidadeProperties).setLogradouro((String) args[0])
				.setBairro((String) args[1]).setCep((String) args[2]).setNumero((String) args[3])
				.setPais((String) args[4]).buildObject();
		log.info("Endereço has been setted!");
		return this;
	}

	@Override
	public EmpresaBuilder setNome(String nome) {
		this.nome = nome;
		return this;
	}

	@Override
	public EmpresaBuilder setCnpj(String cnpj) {
		this.cnpj = cnpj;
		return this;
	}

	@Override
	public EmpresaBuilder setInscEstadual(String inscEstadual) {
		this.inscEstadual = inscEstadual;
		return this;
	}

	@Override
	public EmpresaBuilder setFone(String fone) {
		this.fone = fone;
		return this;
	}

}
