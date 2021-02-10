package com.faraway.fwportal.boostrap.dataloader.nota;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.faraway.fwportal.boostrap.builder.Builder;
import com.faraway.fwportal.model.Empresa;
import com.faraway.fwportal.model.Nota;

public interface NotaDataLoader extends Builder<Nota> {

	NotaDataLoader setEmitente(Empresa args);

	NotaDataLoader setDestinatario(Empresa args);

	NotaDataLoader setChave(String chave);

	NotaDataLoader setNumero(String numero);

	NotaDataLoader setSerie(String serie);

	NotaDataLoader setEmissao(LocalDate emissao);

	NotaDataLoader setTotal(BigDecimal total);

}
