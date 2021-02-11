package com.faraway.fwportal.boostrap.dataloader.nota;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.faraway.fwportal.boostrap.builder.nota.NotaBuilder;
import com.faraway.fwportal.model.Empresa;
import com.faraway.fwportal.model.Nota;
import com.faraway.fwportal.service.NotaCrdService;

@Component
public class NotaDataLoaderImpl implements NotaDataLoader {

	private final NotaCrdService notaCrudService;
	private final NotaBuilder notaBuilder;

	public NotaDataLoaderImpl(NotaCrdService notaCrudService, NotaBuilder notaBuilder) {
		super();
		this.notaCrudService = notaCrudService;
		this.notaBuilder = notaBuilder;
	}

	@Override
	public Nota load() {
		// TODO Auto-generated method stub
		return notaCrudService.save(notaBuilder.buildObject());
	}

	@Override
	public NotaDataLoader setEmitente(Empresa args) {
		this.notaBuilder.setEmitente(args);
		return this;
	}

	@Override
	public NotaDataLoader setDestinatario(Empresa args) {
		this.notaBuilder.setDestinatario(args);
		return this;
	}

	@Override
	public NotaDataLoader setChave(String chave) {
		this.notaBuilder.setChave(chave);
		return this;
	}

	@Override
	public NotaDataLoader setNumero(String numero) {
		this.notaBuilder.setNumero(numero);
		return this;
	}

	@Override
	public NotaDataLoader setSerie(String serie) {
		this.notaBuilder.setSerie(serie);
		return this;
	}

	@Override
	public NotaDataLoader setEmissao(LocalDate emissao) {
		this.notaBuilder.setEmissao(emissao);
		return this;
	}

	@Override
	public NotaDataLoader setTotal(BigDecimal total) {
		this.notaBuilder.setTotal(total);
		return this;
	}

}
