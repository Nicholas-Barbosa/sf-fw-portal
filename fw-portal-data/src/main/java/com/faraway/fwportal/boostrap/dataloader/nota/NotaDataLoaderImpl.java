package com.faraway.fwportal.boostrap.dataloader.nota;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.faraway.fwportal.model.Empresa;
import com.faraway.fwportal.model.Nota;

public class NotaDataLoaderImpl implements NotaDataLoader {

	@Override
	public Nota buildObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotaDataLoader setEmitente(Empresa args) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public NotaDataLoader setDestinatario(Empresa args) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public NotaDataLoader setChave(String chave) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public NotaDataLoader setNumero(String numero) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public NotaDataLoader setSerie(String serie) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public NotaDataLoader setEmissao(LocalDate emissao) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public NotaDataLoader setTotal(BigDecimal total) {
		// TODO Auto-generated method stub
		return this;
	}

}
