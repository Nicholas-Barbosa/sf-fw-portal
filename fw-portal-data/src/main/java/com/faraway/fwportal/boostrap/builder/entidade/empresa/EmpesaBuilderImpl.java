package com.faraway.fwportal.boostrap.builder.entidade.empresa;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.faraway.fwportal.boostrap.builder.endereco.EnderecoBuilder;
import com.faraway.fwportal.model.Empresa;
import com.faraway.fwportal.model.Endereco;

@Component
public class EmpesaBuilderImpl implements EmpresaBuilder {

	private final EnderecoBuilder enderecoBuilder;
	private Endereco endereco;

	public EmpesaBuilderImpl(EnderecoBuilder enderecoBuilder) {
		super();
		this.enderecoBuilder = enderecoBuilder;
	}

	@Override
	public Empresa buildObject(Object... args) {
		// TODO Auto-generated method stub
		String nome = (String) args[0];
		String cnpj = (String) args[1];
		String inscEstadual = (String) args[2];
		String fone = (String) args[3];
		return new Empresa(nome, cnpj, endereco, inscEstadual, fone);
	}

	@Override
	public EmpresaBuilder setEndereco(Object... args) {
		// TODO Auto-generated method stub
		Object[] cidadeProperties = Arrays.stream(args, 5, args.length).toArray();
		endereco = enderecoBuilder.setCidade(cidadeProperties).buildObject(args);
		return this;
	}

}
