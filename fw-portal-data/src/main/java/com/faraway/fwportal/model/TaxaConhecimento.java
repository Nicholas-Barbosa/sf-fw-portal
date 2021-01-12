package com.faraway.fwportal.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "taxas_conhecimento")
public class TaxaConhecimento extends BaseEntity implements Comparable<TaxaConhecimento> {

	@ManyToOne
	private Taxa taxa;

	private BigDecimal valor;

	@ManyToOne
	private Conhecimento conhecimento;

	public TaxaConhecimento() {
		// TODO Auto-generated constructor stub
	}

	public TaxaConhecimento(Taxa taxa, BigDecimal valor) {
		super();
		this.taxa = taxa;
		this.valor = valor;
	}

	public Taxa getTaxa() {
		return taxa;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Conhecimento getConhecimento() {
		return conhecimento;
	}

	public TaxaConhecimento setConhecimento(Conhecimento conhecimento) {
		this.conhecimento = conhecimento;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conhecimento == null) ? 0 : conhecimento.hashCode());
		result = prime * result + ((taxa == null) ? 0 : taxa.hashCode());
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
		TaxaConhecimento other = (TaxaConhecimento) obj;
		if (conhecimento == null) {
			if (other.conhecimento != null)
				return false;
		} else if (!conhecimento.equals(other.conhecimento))
			return false;
		if (taxa == null) {
			if (other.taxa != null)
				return false;
		} else if (!taxa.equals(other.taxa))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

	@Override
	public int compareTo(TaxaConhecimento o) {
		return this.valor.compareTo(o.valor);
	}

}
