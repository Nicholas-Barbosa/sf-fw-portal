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

	private String inscEstadual;

	private String fone;

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

	public String getInscEstadual() {
		return inscEstadual;
	}

	public void setInscEstadual(String inscEstadual) {
		this.inscEstadual = inscEstadual;
	}

	

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	@Override
	public String toString() {
		return "Entidade [cnpj=" + cnpj + ", nome=" + nome + ", endereco=" + endereco + ", inscEstadual=" + inscEstadual
				+ ", fone=" + fone + "]";
	}
	
}
