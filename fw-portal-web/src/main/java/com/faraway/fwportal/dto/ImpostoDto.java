package com.faraway.fwportal.dto;

import java.math.BigDecimal;

import com.faraway.fwportal.model.domain.Imposto;

public class ImpostoDto {

	private BigDecimal baseCalculo;
	private Float porcentagemIcms;
	private BigDecimal valorIcms;

	public ImpostoDto(Imposto imposto) {
		super();
		this.baseCalculo = imposto.getBaseCalculo();
		this.porcentagemIcms = imposto.getPorcentagemIcms();
		this.valorIcms = imposto.getValorIcms();
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

}
