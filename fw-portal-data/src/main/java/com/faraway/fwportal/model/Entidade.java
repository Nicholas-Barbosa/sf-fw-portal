package com.faraway.fwportal.model;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
public class Entidade extends BaseEntity {

	private String cnpj;

	private String nome;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Endereco endereco;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return new Endereco(endereco);
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
