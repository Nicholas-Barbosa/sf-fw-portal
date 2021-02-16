package com.faraway.fwportal.boostrap.dataloader.entidade.empresa;

import org.springframework.stereotype.Component;

import com.faraway.fwportal.boostrap.builder.entidade.empresa.EmpresaBuilder;
import com.faraway.fwportal.model.domain.Empresa;
import com.faraway.fwportal.model.domain.Endereco;
import com.faraway.fwportal.service.EmpresaCrdService;

@Component
public class EmpesaDataLoaderImpl implements EmpresaDataLoader {

	private final EmpresaBuilder empresaBuilder;
	private final EmpresaCrdService empresaCrudService;

	public EmpesaDataLoaderImpl(EmpresaBuilder empresaBuilder, EmpresaCrdService empresaCrudService) {
		super();
		this.empresaBuilder = empresaBuilder;
		this.empresaCrudService = empresaCrudService;
	}

	@Override
	public Empresa load() {
		// TODO Auto-generated method stub
		return empresaCrudService.save(empresaBuilder.buildObject());
	}

	@Override
	public EmpresaDataLoader setEndereco(Endereco endereco) {
		empresaBuilder.setEndereco(endereco);
		return this;
	}

	@Override
	public EmpresaDataLoader setNome(String nome) {
		empresaBuilder.setNome(nome);
		return this;
	}

	@Override
	public EmpresaDataLoader setCnpj(String cnpj) {
		empresaBuilder.setCnpj(cnpj);
		return this;
	}

	@Override
	public EmpresaDataLoader setInscEstadual(String inscEstadual) {
		empresaBuilder.setInscEstadual(inscEstadual);
		return this;
	}

	@Override
	public EmpresaDataLoader setFone(String fone) {
		empresaBuilder.setFone(fone);
		return this;
	}

}
