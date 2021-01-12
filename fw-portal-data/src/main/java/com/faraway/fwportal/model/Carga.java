package com.faraway.fwportal.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cargas")
public class Carga extends BaseEntity {

	private BigDecimal valor;

	private String produtoPredominante;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "carga")
	private Set<Medida> medidas;

	@OneToOne(mappedBy = "carga")
	private Conhecimento conhecimento;

	public Carga() {
		// TODO Auto-generated constructor stub
	}

	public Carga(BigDecimal valor, String produtoPredominante) {
		super();
		this.valor = valor;
		this.produtoPredominante = produtoPredominante;
	}

	public Carga(Carga carga) {
		finalSetId(carga.getId());
		this.valor = carga.getValor();
		this.produtoPredominante = carga.getProdutoPredominante();
		this.medidas = carga.getMedidas();
	}

	public void finalSetId(Long id) {
		super.setId(id);
	}

	public void setMedidas(Set<Medida> medidas) {
		this.medidas = new HashSet<>(medidas);
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getProdutoPredominante() {
		return produtoPredominante;
	}

	public Set<Medida> getMedidas() {
		return new HashSet<>(medidas);
	}

	public Conhecimento getConhecimento() {
		return conhecimento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conhecimento == null) ? 0 : conhecimento.hashCode());
		result = prime * result + ((medidas == null) ? 0 : medidas.hashCode());
		result = prime * result + ((produtoPredominante == null) ? 0 : produtoPredominante.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		Carga other = (Carga) obj;
		if (conhecimento == null) {
			if (other.conhecimento != null)
				return false;
		} else if (!conhecimento.equals(other.conhecimento))
			return false;
		if (medidas == null) {
			if (other.medidas != null)
				return false;
		} else if (!medidas.equals(other.medidas))
			return false;
		if (produtoPredominante == null) {
			if (other.produtoPredominante != null)
				return false;
		} else if (!produtoPredominante.equals(other.produtoPredominante))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

	
}
