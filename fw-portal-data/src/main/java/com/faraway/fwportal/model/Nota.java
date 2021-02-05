package com.faraway.fwportal.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "notas")
public class Nota extends Documento {

	@ManyToOne
	private Empresa emitente;

	@ManyToOne
	private Empresa cliente;

	@ManyToMany(cascade = CascadeType.REMOVE, mappedBy = "notas")
	private Set<Conhecimento> conhecimentos;

	private String chave;

	private Boolean isIncomplete;

	public Nota() {
		// TODO Auto-generated constructor stub
	}

	public Nota(Empresa emitente, Empresa cliente, String chave, String numero, String serie, LocalDate emissao,
			BigDecimal total, Boolean isIncomplete) {
		super();
		this.emitente = new Empresa(emitente);
		this.cliente = new Empresa(cliente);
		this.chave = chave;
		this.isIncomplete = isIncomplete;
		finalSetNumero(numero);
		finalSetSerie(serie);
		finalSetEmissao(emissao);
		finalSetTotal(total);
	}

	public Nota(Nota nota) {
		this.emitente = nota.getEmitente();
		this.cliente = nota.getCliente();
		this.chave = nota.getChave();

		finalSetId(nota.getId());
		finalSetNumero(nota.getNumero());
		finalSetSerie(nota.getSerie());
		finalSetEmissao(nota.getEmissao());
		finalSetTotal(nota.getTotal());
	}

	public Empresa getEmitente() {
		return new Empresa(emitente);
	}

	public Empresa getCliente() {
		return new Empresa(cliente);
	}

	public Set<Conhecimento> getConhecimentos() {
		return new HashSet<Conhecimento>(this.conhecimentos);
	}

	public String getChave() {
		return chave;
	}

	final private void finalSetId(Long id) {
		super.setId(id);
	}

	final private void finalSetNumero(String numero) {
		super.setNumero(numero);
	}

	final private void finalSetSerie(String serie) {
		super.setSerie(serie);
	}

	final private void finalSetEmissao(LocalDate emissao) {
		super.setEmissao(emissao);
	}

	final private void finalSetTotal(BigDecimal total) {
		super.setTotal(total);

	}

	public Boolean getIsIncomplete() {
		return isIncomplete;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chave == null) ? 0 : chave.hashCode());
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
		Nota other = (Nota) obj;
		if (chave == null) {
			if (other.chave != null)
				return false;
		} else if (!chave.equals(other.chave))
			return false;
		return true;
	}

}
