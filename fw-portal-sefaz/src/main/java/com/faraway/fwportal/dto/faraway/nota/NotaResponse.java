package com.faraway.fwportal.dto.faraway.nota;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NotaResponse {

	@JsonProperty(value = "NUMERO")
	private String numero;

	@JsonProperty(value = "SERIE")
	private String serie;

	@JsonProperty(value = "CHAVE")
	private String chave;

	@JsonProperty(value = "PESO LIQ")
	private Double pesoLiq;

	@JsonProperty(value = "PESO BRUTO")
	private Double pesoBruto;

	@JsonProperty(value = "VALOR")
	private Double valor;

	@JsonProperty(value = "CGC")
	private String cnpjClienet;

	@JsonIgnore
	@JsonProperty(value = "CLIENTE")
	private String cliente;

	@JsonProperty(value = "EMISSAO")
	private String emissao;

	public String getNumero() {
		return numero;
	}

	public String getSerie() {
		return serie;
	}

	public String getChave() {
		return chave;
	}

	public Double getPesoLiq() {
		return pesoLiq;
	}

	public Double getPesoBruto() {
		return pesoBruto;
	}

	public Double getValor() {
		return valor;
	}

	public String getCnpjClienet() {
		return cnpjClienet;
	}

	public String getCliente() {
		return cliente;
	}

	public String getEmissao() {
		return emissao;
	}

	@Override
	public String toString() {
		return "NotaResponse [numero=" + numero + ", serie=" + serie + ", chave=" + chave + ", pesoLiq=" + pesoLiq
				+ ", pesoBruto=" + pesoBruto + ", valor=" + valor + ", cnpjClienet=" + cnpjClienet + ", cliente="
				+ cliente + ", emissao=" + emissao + "]";
	}

}
