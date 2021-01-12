package com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.imposto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Icms00 {

	@JsonProperty("vBC")
	private Double baseCalculo;

	@JsonProperty("pICMS")
	private Float aliquotaIcms;

	@JsonProperty("vICMS")
	private Double valorIcms;

	public Icms00() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Icms00(Double baseCalculo, Float aliquotaIcms, Double valorIcms) {
		super();
		this.baseCalculo = baseCalculo;
		this.aliquotaIcms = aliquotaIcms;
		this.valorIcms = valorIcms;
	}


	public Double getBaseCalculo() {
		return baseCalculo;
	}

	public Float getAliquotaIcms() {
		return aliquotaIcms;
	}

	public Double getValorIcms() {
		return valorIcms;
	}

}
