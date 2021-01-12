package com.faraway.fwportal.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "enderecos")
public class Endereco extends BaseEntity {

	private String logradouro;
	private String numero;
	private String cep;

	@ManyToOne(fetch = FetchType.LAZY)
	private Cidade cidade;

	public Endereco() {
		// TODO Auto-generated constructor stub
	}

	public Endereco(String logradouro, String numero, String cep, Cidade cidade) {
		super();
		this.logradouro = logradouro;
		this.numero = numero;
		this.cep = cep;
		this.cidade = cidade;
	}

	public Endereco(Endereco endereco) {
		this.logradouro = endereco.getLogradouro();
		this.numero = endereco.getNumero();
		this.cep = endereco.getCep();
		this.cidade = endereco.getCidade();
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public String getCep() {
		return cep;
	}

	public Cidade getCidade() {
		return new Cidade(cidade);
	}
}
