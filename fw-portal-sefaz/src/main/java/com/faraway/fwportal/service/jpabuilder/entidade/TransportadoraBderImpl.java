package com.faraway.fwportal.service.jpabuilder.entidade;

import org.springframework.stereotype.Component;

import com.faraway.fwportal.model.Endereco;
import com.faraway.fwportal.model.Transportadora;
import com.faraway.fwportal.service.TransportadoraCrdService;

@Component
public class TransportadoraBderImpl implements TransportadoraBder {

	private final TransportadoraCrdService tCrudService;

	public TransportadoraBderImpl(TransportadoraCrdService empresaCrudService) {
		super();
		this.tCrudService = empresaCrudService;
	}

	@Override
	public Transportadora toJpaEntity(String object, Endereco endereco) {

		String splitted[] = object.split(",");
		String nome = splitted[0].strip();
		String cnpj = splitted[1].strip();
		String inscEstadual = splitted[2].strip();
		String fone = splitted[3].strip();
		Transportadora transportadora = new Transportadora(nome, cnpj, endereco, inscEstadual, fone);
		return tCrudService.save(transportadora);
	}

	@Override
	public Transportadora toJpaEntity(String object) {

		return null;
	}

}
