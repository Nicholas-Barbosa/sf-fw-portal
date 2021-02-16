package com.faraway.fwportal.model.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "taxas")
public class Taxa extends BaseEntity {

	private String nome;

	@OneToMany(mappedBy = "taxa", fetch = FetchType.LAZY)
	private Set<TaxaConhecimento> taxasConhecimento;

	public Taxa() {
		// TODO Auto-generated constructor stub
	}

	public Taxa(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public Set<TaxaConhecimento> getTaxasConhecimento() {
		return new HashSet<TaxaConhecimento>(taxasConhecimento);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Taxa other = (Taxa) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equalsIgnoreCase(other.nome))
			return false;
		return true;
	}

}
