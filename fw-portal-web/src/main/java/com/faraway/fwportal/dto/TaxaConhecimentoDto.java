package com.faraway.fwportal.dto;

import java.math.BigDecimal;

import com.faraway.fwportal.model.TaxaConhecimento;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TaxaConhecimentoDto implements Comparable<TaxaConhecimentoDto> {

	@JsonProperty("nome")
	private String taxa;

	@JsonProperty("valor")
	private BigDecimal valor;

	public TaxaConhecimentoDto(TaxaConhecimento taxa) {
		super();
		this.taxa = taxa.getTaxa().getNome();
		this.valor = taxa.getValor();

	}

	public String getTaxa() {
		return taxa;
	}

	public BigDecimal getValor() {
		return valor;
	}

	@Override
	public int compareTo(TaxaConhecimentoDto o) {
		// TODO Auto-generated method stub
		return valor.compareTo(o.getValor());
	}

}
