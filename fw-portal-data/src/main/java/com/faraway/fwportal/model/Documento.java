package com.faraway.fwportal.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Documento extends BaseEntity {

	private String numero;

	private String serie;

	private LocalDate emissao;

	private BigDecimal total;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {

		this.serie = serie;
	}

	public LocalDate getEmissao() {
		return emissao;
	}

	public void setEmissao(LocalDate emissao) {
		this.emissao = emissao;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
