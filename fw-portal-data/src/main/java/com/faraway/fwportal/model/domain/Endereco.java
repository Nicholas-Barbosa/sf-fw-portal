package com.faraway.fwportal.model.domain;

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
	private String bairro;
	private String pais;
	@ManyToOne(fetch = FetchType.LAZY)
	private Cidade cidade;

	public Endereco() {
		// TODO Auto-generated constructor stub
	}

	public Endereco(String logradouro, String numero, String cep, Cidade cidade, String bairro, String pais) {
		super();
		this.logradouro = logradouro;
		this.numero = numero;
		this.cep = cep;
		this.cidade = cidade;
		this.bairro = bairro;
		this.pais = pais;
	}

	public Endereco(Endereco endereco) {
		this.logradouro = endereco.getLogradouro();
		this.numero = endereco.getNumero();
		this.cep = endereco.getCep();
		this.cidade = endereco.getCidade();
		this.bairro = endereco.getBairro();
		this.pais = endereco.getPais();
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

	public String getBairro() {
		return bairro;
	}

	public String getPais() {
		return pais;
	}

	@Override
	public String toString() {
		return "Endereco [logradouro=" + logradouro + ", numero=" + numero + ", cep=" + cep + ", bairro=" + bairro
				+ ", pais=" + pais + ", cidade=" + cidade + "]";
	}
	
}
