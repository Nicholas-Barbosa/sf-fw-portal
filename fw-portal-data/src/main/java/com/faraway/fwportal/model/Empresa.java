package com.faraway.fwportal.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "empresas")
public class Empresa extends Entidade {

	public Empresa() {
		// TODO Auto-generated constructor stub
	}

	public Empresa(String nome, String cnpj, Endereco endereco) {
		finalSetNome(nome);
		finalSetCnpj(cnpj);
		finalSetEndereco(endereco);
	}

	public Empresa(Empresa empresa) {
		finalSetId(empresa.getId());
		finalSetNome(empresa.getNome());
		finalSetCnpj(empresa.getCnpj());
		finalSetEndereco(empresa.getEndereco());
	}

	private final void finalSetId(Long id) {
		super.setId(id);
	}

	private final void finalSetNome(String nome) {
		super.setNome(nome);
	}

	private final void finalSetCnpj(String cnpj) {
		super.setCnpj(cnpj);
	}

	private final void finalSetEndereco(Endereco endereco) {
		super.setEndereco(endereco);
	}
}
