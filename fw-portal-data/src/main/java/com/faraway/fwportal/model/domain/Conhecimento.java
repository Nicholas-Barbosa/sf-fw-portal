package com.faraway.fwportal.model.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "conhecimentos")
public class Conhecimento extends Documento {

	@ManyToOne(fetch = FetchType.LAZY)
	private Cidade cidadeInicio;
	@ManyToOne(fetch = FetchType.LAZY)
	private Cidade cidadeDestino;

	@ManyToOne(fetch = FetchType.LAZY)
	private Transportadora emitente;

	@ManyToOne(fetch = FetchType.LAZY)
	private Empresa remetente;

	@ManyToOne(fetch = FetchType.LAZY)
	private Empresa destinatario;

	@OneToMany(mappedBy = "conhecimento", cascade = CascadeType.ALL)
	private Set<TaxaConhecimento> taxas;

	@ManyToMany
	@JoinTable(joinColumns = @JoinColumn(name = "conhecimento_id"), inverseJoinColumns = @JoinColumn(name = "nota_id"))
	private Set<Nota> notas;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Imposto imposto;

	@OneToOne(fetch = FetchType.LAZY)
	private Carga carga;

	private String chave;

	public Conhecimento() {
		// TODO Auto-generated constructor stub
	}

	public Conhecimento(String chave) {
		super();
		this.chave = chave;
	}

	public Conhecimento(String numero, String serie, LocalDate emissao, BigDecimal total, Cidade cidadeInicio,
			Cidade cidadeDestino, Transportadora emitente, Empresa remetente, Empresa destinatario, Set<Nota> notas,
			Imposto imposto, Carga carga, String chave, Set<TaxaConhecimento> taxas) {
		super();
		this.cidadeInicio = new Cidade(cidadeInicio);
		this.cidadeDestino = new Cidade(cidadeDestino);
		this.emitente = new Transportadora(emitente);
		this.remetente = new Empresa(remetente);
		this.destinatario = new Empresa(destinatario);
		this.notas = new HashSet<Nota>(notas);
		this.imposto = new Imposto(imposto);
		this.carga = new Carga(carga);
		this.chave = chave;
		this.taxas = new HashSet<TaxaConhecimento>(taxas);

		finalSetNumero(numero);
		finalSetSerie(serie);
		finalSetEmissao(emissao);
		finalSetTotal(total);
	}

	public Conhecimento(Conhecimento conhecimento) {
		super();
		this.cidadeInicio = conhecimento.getCidadeInicio();
		this.cidadeDestino = conhecimento.getCidadeDestino();
		this.emitente = conhecimento.getEmitente();
		this.remetente = conhecimento.getRemetente();
		this.destinatario = conhecimento.getDestinatario();
		this.notas = conhecimento.getNotas();
		this.imposto = conhecimento.getImposto();
		this.carga = conhecimento.getCarga();
		this.chave = conhecimento.getChave();
		this.taxas = conhecimento.getTaxas();

		finalSetNumero(conhecimento.getNumero());
		finalSetSerie(conhecimento.getSerie());
		finalSetEmissao(conhecimento.getEmissao());
		finalSetTotal(conhecimento.getTotal());
	}

	final void finalSetNumero(String numero) {
		super.setNumero(numero);
	}

	final void finalSetSerie(String serie) {
		super.setSerie(serie);
	}

	final void finalSetEmissao(LocalDate emissao) {
		super.setEmissao(emissao);
	}

	final void finalSetTotal(BigDecimal valor) {
		super.setTotal(valor);
	}

	public Cidade getCidadeInicio() {
		return new Cidade(cidadeInicio);
	}

	public Cidade getCidadeDestino() {
		return new Cidade(cidadeDestino);
	}

	public Transportadora getEmitente() {
		return new Transportadora(emitente);
	}

	public Empresa getRemetente() {
		return new Empresa(remetente);
	}

	public Empresa getDestinatario() {
		return new Empresa(destinatario);
	}

	public Set<TaxaConhecimento> getTaxas() {
		return new HashSet<>(taxas);
	}

	public void setConhecimentoForTaxas() {
		this.taxas = this.taxas.parallelStream().map(tx -> tx.setConhecimento(this)).collect(ConcurrentSkipListSet::new,
				Set::add, Set::addAll);
	}

	public Carga getCarga() {
		return new Carga(carga);
	}

	public Set<Nota> getNotas() {
		return new HashSet<Nota>(notas);
	}

	public Imposto getImposto() {
		return new Imposto(imposto);
	}

	public String getChave() {
		return chave;
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
		Conhecimento other = (Conhecimento) obj;
		if (chave == null) {
			if (other.chave != null)
				return false;
		} else if (!chave.equals(other.chave))
			return false;
		return true;
	}

}
