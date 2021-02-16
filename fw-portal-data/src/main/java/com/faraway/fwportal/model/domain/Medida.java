package com.faraway.fwportal.model.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "medidas")
public class Medida extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "tipo_medida_id")
	private TipoMedida medida;

	private Float quantidade;

	@ManyToOne
	private Carga carga;

	public Medida() {
		// TODO Auto-generated constructor stub
	}

	public Medida(TipoMedida medida, Float quantidade, Carga carga) {
		super();
		this.medida = medida;
		this.quantidade = quantidade;
		this.carga = carga;
	}

	public TipoMedida getMedida() {
		return medida;
	}

	public Float getQuantidade() {
		return quantidade;
	}

	public Carga getCarga() {
		return carga;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((medida == null) ? 0 : medida.hashCode());
		result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
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
		Medida other = (Medida) obj;
		if (medida == null) {
			if (other.medida != null)
				return false;
		} else if (!medida.equals(other.medida))
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		return true;
	}

}
