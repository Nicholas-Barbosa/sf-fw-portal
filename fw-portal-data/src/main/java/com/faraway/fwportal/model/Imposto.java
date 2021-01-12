package com.faraway.fwportal.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "impostos")
public class Imposto extends BaseEntity {

	private BigDecimal baseCalculo;
	private Float porcentagemIcms;
	private BigDecimal valorIcms;

	@OneToOne(mappedBy = "imposto")
	private Conhecimento conhecimento;

	public Imposto() {
		// TODO Auto-generated constructor stub
	}

	public Imposto(BigDecimal baseCalculo, Float porcentagemIcms, BigDecimal valorIcms) {
		super();
		this.baseCalculo = baseCalculo;
		this.porcentagemIcms = porcentagemIcms;
		this.valorIcms = valorIcms;
	}

	public Imposto(Imposto imposto) {
		finalSetId(imposto.getId());
		this.baseCalculo = imposto.getBaseCalculo();
		this.porcentagemIcms = imposto.getPorcentagemIcms();
		this.valorIcms = imposto.getValorIcms();
	}

	final private void finalSetId(Long id) {
		super.setId(id);
	}

	public BigDecimal getBaseCalculo() {
		return baseCalculo;
	}

	public Float getPorcentagemIcms() {
		return porcentagemIcms;
	}

	public BigDecimal getValorIcms() {
		return valorIcms;
	}

	public Conhecimento getConhecimento() {
		return conhecimento;
	}
}
