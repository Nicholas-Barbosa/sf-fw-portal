package com.faraway.fwportal.boostrap.builder.entidade.empresa;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.faraway.fwportal.boostrap.builder.endereco.EnderecoBuilder;
import com.faraway.fwportal.boostrap.builder.endereco.EnderecoBuilderImpl;
import com.faraway.fwportal.model.Empresa;
import com.faraway.fwportal.model.Endereco;

@Component
public class EmpesaBuilderImpl implements EmpresaBuilder {
	private static final Logger log = LoggerFactory.getLogger(EmpesaBuilderImpl.class);
	private final EnderecoBuilder enderecoBuilder;
	private Endereco endereco;

	public EmpesaBuilderImpl(EnderecoBuilder enderecoBuilder) {
		super();
		this.enderecoBuilder = enderecoBuilder;
	}

	@Override
	public Empresa buildObject(Object... args) {
		// TODO Auto-generated method stub
		log.info("Creating Empresa Object...");
		String nome = (String) args[0];
		String cnpj = (String) args[1];
		String inscEstadual = (String) args[2];
		String fone = (String) args[3];
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
		endereco = enderecoBuilder.setCidade(cidadeProperties).buildObject(args);
		log.info("Endereço has been setted!");
		return this;
	}

}
