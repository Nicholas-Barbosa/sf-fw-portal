package com.faraway.fwportal.model.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cidades")
public class Cidade extends BaseEntity {

	private String nome;
	private String uf;
	@Column(name = "codigo_ibge")
	private String codigoIbge;

	@OneToMany(mappedBy = "cidade")
	private Set<Endereco> enderecos;

	public Cidade() {
		// TODO Auto-generated constructor stub
	}

	public Cidade(String nome, String uf, String codigoIbge) {
		super();
		this.nome = nome;
		this.uf = uf;
		this.codigoIbge = codigoIbge;
	}

	public Cidade(Cidade cidade) {
		finalSetId(cidade.getId());
		this.nome = cidade.getNome();
		this.uf = cidade.getUf();
		this.codigoIbge = cidade.getCodigoIbge();

	}

	private final void finalSetId(Long id) {
		super.setId(id);
	}

	public String getNome() {
		return nome;
	}

	public String getUf() {
		return uf;
	}

	public String getCodigoIbge() {
		return codigoIbge;
	}

	public Set<Endereco> getEnderecos() {
		return new HashSet<>(enderecos);
	}

	@Override
	public String toString() {
		return "Cidade [nome=" + nome + ", uf=" + uf + ", codigoIbge=" + codigoIbge;
	}

}
