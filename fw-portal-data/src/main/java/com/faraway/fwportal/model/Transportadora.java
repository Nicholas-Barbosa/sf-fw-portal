package com.faraway.fwportal.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "transportadoras")
public class Transportadora extends Entidade {

	public Transportadora() {
		// TODO Auto-generated constructor stub
	}

	public Transportadora(String nome, String cnpj, Endereco endereco, String insEstadual, String fone) {
		finalSetNome(nome);
		finalCnpj(cnpj);
		finalSetEndereco(endereco);

		finalSetInscEstadual(insEstadual);
		finalSetFone(fone);
	}

	public Transportadora(Transportadora emitente) {
		finalSetId(emitente.getId());
		finalSetNome(emitente.getNome());
		finalCnpj(emitente.getCnpj());
		finalSetEndereco(emitente.getEndereco());
		finalSetInscEstadual(emitente.getInscEstadual());

		finalSetFone(emitente.getFone());
	}

	private final void finalSetId(Long id) {
		super.setId(id);
	}

	private final void finalSetNome(String nome) {
		super.setNome(nome);
	}

	private final void finalCnpj(String cnpj) {
		super.setCnpj(cnpj);
	}

	private final void finalSetEndereco(Endereco endereco) {
		super.setEndereco(endereco);
	}

	private final void finalSetInscEstadual(String inscEstadual) {
		super.setInscEstadual(inscEstadual);
	}

	private final void finalSetFone(String fone) {
		super.setFone(fone);
	}
}
