package com.faraway.fwportal.model.builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.faraway.fwportal.model.domain.Carga;
import com.faraway.fwportal.model.domain.Cidade;
import com.faraway.fwportal.model.domain.Conhecimento;
import com.faraway.fwportal.model.domain.Empresa;
import com.faraway.fwportal.model.domain.Imposto;
import com.faraway.fwportal.model.domain.Nota;
import com.faraway.fwportal.model.domain.TaxaConhecimento;
import com.faraway.fwportal.model.domain.Transportadora;

public class ConhecimentoBuilder implements Builder<Conhecimento> {

	private String numero, serie, chave;
	private LocalDate emissao;
	private BigDecimal total;
	private Cidade cidadeInicio, cidadeDestino;
	private Transportadora emitente;
	private Empresa remetente, destinatario;
	private Set<Nota> notas = new HashSet<>();
	private Imposto imposto;
	private Carga carga;
	private Set<TaxaConhecimento> taxas = new HashSet<>();

	public static ConhecimentoBuilder builder() {
		// TODO Auto-generated method stub
		return new ConhecimentoBuilder();
	}

	@Override
	public Conhecimento build() {
		// TODO Auto-generated method stub
		return null;
	}

	public ConhecimentoBuilder setNumero(String numero) {
		this.numero = numero;
		return this;
	}

	public ConhecimentoBuilder setSerie(String serie) {
		this.serie = serie;
		return this;
	}

	public ConhecimentoBuilder setChave(String chave) {
		this.chave = chave;
		return this;
	}

	public ConhecimentoBuilder setEmissao(LocalDate emissao) {
		this.emissao = emissao;
		return this;
	}

	public ConhecimentoBuilder setTotal(BigDecimal total) {
		this.total = total;
		return this;
	}

	public ConhecimentoBuilder setCidadeInicio(Cidade cidade) {
		this.cidadeInicio = new Cidade(cidade);
		return this;
	}

	public ConhecimentoBuilder setCidadeDestino(Cidade cidade) {
		this.cidadeDestino = new Cidade(cidade);
		return this;
	}

	public ConhecimentoBuilder setEmitente(Transportadora emitente) {
		this.emitente = new Transportadora(emitente);
		return this;
	}

	public ConhecimentoBuilder setRemetente(Empresa remetente) {
		this.remetente = new Empresa(remetente);
		return this;
	}

	public ConhecimentoBuilder setDestinatario(Empresa destinatario) {
		this.destinatario = new Empresa(destinatario);
		return this;
	}

	public ConhecimentoBuilder setNotas(Set<Nota> notas) {
		this.notas = new HashSet<>(notas);
		return this;
	}

	public ConhecimentoBuilder setImposto(Imposto imposto) {
		this.imposto = new Imposto(imposto);
		return this;
	}

	public ConhecimentoBuilder setCarga(Carga carga) {
		this.carga = new Carga(carga);
		return this;
	}

	public ConhecimentoBuilder setTaxas(Set<TaxaConhecimento> taxas) {
		this.taxas = new HashSet<>(taxas);
		return this;
	}

}
