package com.faraway.fwportal.service.jpabuilder.cidade;

import org.springframework.stereotype.Component;

import com.faraway.fwportal.model.Cidade;
import com.faraway.fwportal.service.CidadeCrdService;

@Component
public class CidadeBderImpl implements CidadeBder {

	private final CidadeCrdService cidadeCrudService;

	public CidadeBderImpl(CidadeCrdService cidadeCrudService) {
		super();
		this.cidadeCrudService = cidadeCrudService;
	}

	@Override
	public Cidade toJpaEntity(String obj) {

		String[] splitted = obj.split(",");
		String nome = splitted[0].strip();
		String codigoIbje = splitted[1].strip();
		String uf = splitted[2].strip();

		Cidade cidade = new Cidade(nome, uf, codigoIbje);
		return cidadeCrudService.save(cidade);
	}

}
