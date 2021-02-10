package com.faraway.fwportal.boostrap.dataloader.cidade;

import com.faraway.fwportal.boostrap.builder.cidade.CidadeBuilder;
import com.faraway.fwportal.model.Cidade;
import com.faraway.fwportal.service.CidadeCrdService;

public class CidadeDataLoaderImpl implements CidadeDataLoader {

	private final CidadeBuilder cidadeBuilder;
	private final CidadeCrdService cidadeCrudService;

	public CidadeDataLoaderImpl(CidadeBuilder cidadeBuilder, CidadeCrdService cidadeCrudService) {
		super();
		this.cidadeBuilder = cidadeBuilder;
		this.cidadeCrudService = cidadeCrudService;
	}

	@Override
	public Cidade load() {
		// TODO Auto-generated method stub
		return cidadeCrudService.save(cidadeBuilder.buildObject());
	}

	@Override
	public CidadeDataLoaderImpl setNome(String nome) {
		this.cidadeBuilder.setNome(nome);
		return this;
	}

	@Override
	public CidadeDataLoaderImpl setUf(String uf) {
		this.cidadeBuilder.setUf(uf);
		return this;
	}

	@Override
	public CidadeDataLoaderImpl setCodigoIbge(String codigo) {
		this.cidadeBuilder.setCodigoIbge(codigo);
		return this;
	}

}
