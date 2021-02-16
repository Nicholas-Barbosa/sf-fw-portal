package com.faraway.fwportal.model.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "tipos_medidas")
@Entity
public class TipoMedida extends BaseEntity {

	private String nome;

	public TipoMedida() {
		// TODO Auto-generated constructor stub
	}

	public TipoMedida(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
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
		TipoMedida other = (TipoMedida) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
