package com.faraway.fwportal.boostrap.dataloader.entidade.transportadora;

import org.springframework.stereotype.Component;

import com.faraway.fwportal.boostrap.builder.entidade.transportadora.TransportadoraBuilder;
import com.faraway.fwportal.model.Endereco;
import com.faraway.fwportal.model.Transportadora;
import com.faraway.fwportal.service.TransportadoraCrdService;

@Component
public class TransportadoraDataLoaderImpl implements TransportadoraDataLoader {

	private final TransportadoraBuilder transportadoraBuilder;
	private final TransportadoraCrdService transportadoraCrudService;

	public TransportadoraDataLoaderImpl(TransportadoraBuilder transportadoraBuilder,
			TransportadoraCrdService transportadoraCrudService) {
		super();
		this.transportadoraBuilder = transportadoraBuilder;
		this.transportadoraCrudService = transportadoraCrudService;
	}

	@Override
	public Transportadora load() {
		// TODO Auto-generated method stub
		return transportadoraCrudService.save(transportadoraBuilder.buildObject());
	}

	@Override
	public TransportadoraDataLoader setEndereco(Endereco endereco) {
		// TODO Auto-generated method stub
		transportadoraBuilder.setEndereco(endereco);
		return this;
	}

	@Override
	public TransportadoraDataLoader setNome(String nome) {
		// TODO Auto-generated method stub
		transportadoraBuilder.setNome(nome);
		return this;
	}

	@Override
	public TransportadoraDataLoader setCnpj(String cnpj) {
		// TODO Auto-generated method stub
		transportadoraBuilder.setCnpj(cnpj);
		return this;
	}

	@Override
	public TransportadoraDataLoader setInscEstadual(String inscEstadual) {
		// TODO Auto-generated method stub
		transportadoraBuilder.setInscEstadual(inscEstadual);
		return this;
	}

	@Override
	public TransportadoraDataLoader setFone(String fone) {
		// TODO Auto-generated method stub
		transportadoraBuilder.setFone(fone);
		return this;
	}

}
