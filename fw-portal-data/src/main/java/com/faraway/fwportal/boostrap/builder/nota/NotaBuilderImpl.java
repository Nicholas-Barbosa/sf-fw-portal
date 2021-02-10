package com.faraway.fwportal.boostrap.builder.nota;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.faraway.fwportal.model.Empresa;
import com.faraway.fwportal.model.Nota;

public class NotaBuilderImpl implements NotaBuilder {

	private Empresa emitente, destinatario;
	private String chave, numero, serie;
	private LocalDate emissao;
	private BigDecimal total;
	private boolean isIncomplete;

	@Override
	public Nota buildObject() {
		// TODO Auto-generated method stub
		return new Nota(emitente, destinatario, chave, numero, serie, emissao, total, isIncomplete);
	}

	@Override
	public NotaBuilder setEmitente(Object... args) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public NotaBuilder setDestinatario(Object... args) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public NotaBuilder setChave(String chave) {
		this.chave = chave;
		return this;
	}

	@Override
	public NotaBuilder setNumero(String numero) {
		this.numero = numero;
		return this;
	}

	@Override
	public NotaBuilder setSerie(String serie) {
		this.serie = serie;
		return this;
	}

	@Override
	public NotaBuilder setEmissao(LocalDate emissao) {
		this.emissao = emissao;
		return this;
	}

	@Override
	public NotaBuilder setTotal(BigDecimal total) {
		this.total = total;
		return this;
	}

	@Override
	public NotaBuilder setConhecimentos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotaBuilder setEmitente(Empresa args) {
		this.emitente = new Empresa(args);
		return this;
	}

	@Override
	public NotaBuilder setDestinatario(Empresa args) {
		this.destinatario = new Empresa(args);
		return this;
	}

}
