package com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.valor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ValorComponenteLayout {

	@JsonProperty("xNome")
	private String nome;
	@JsonProperty("vComp")
	private Double valor;

	public ValorComponenteLayout() {
		// TODO Auto-generated constructor stub
	}

	public ValorComponenteLayout(String nome, Double valor) {
		super();
		this.nome = nome;
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public Double getValor() {
		return valor;
	}

}
