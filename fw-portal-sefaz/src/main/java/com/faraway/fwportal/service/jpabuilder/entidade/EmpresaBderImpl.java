package com.faraway.fwportal.service.jpabuilder.entidade;

import org.springframework.stereotype.Component;

import com.faraway.fwportal.model.*;

import com.faraway.fwportal.service.EmpresaCrdService;

@Component
public class EmpresaBderImpl implements EmpresaBder {

	private final EmpresaCrdService empresaCrudService;

	public EmpresaBderImpl(EmpresaCrdService empresaCrudService) {
		super();
		this.empresaCrudService = empresaCrudService;
	}

	@Override
	public Empresa toJpaEntity(String object, Endereco endereco) {
		String splitted[] = object.split(",");
		String nome = splitted[0].strip();
		String cnpj = splitted[1].strip();

		Empresa empresa = new Empresa(nome, cnpj, endereco);
		return empresaCrudService.save(empresa);
	}

	@Override
	public Empresa toJpaEntity(String object) {

		return null;
	}

}
