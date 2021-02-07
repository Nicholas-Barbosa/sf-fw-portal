package com.faraway.fwportal.boostrap.builder.nota;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.faraway.fwportal.boostrap.builder.Builder;
import com.faraway.fwportal.model.Nota;

public interface NotaBuilder extends Builder<Nota> {

	NotaBuilder setEmitente(Object... args);

	NotaBuilder setDestinatario(Object... args);

	NotaBuilder setChave(String chave);

	NotaBuilder setNumero(String numero);

	NotaBuilder setSerie(String serie);

	NotaBuilder setEmissao(LocalDate emissao);

	NotaBuilder setTotal(BigDecimal total);

	NotaBuilder setConhecimentos();
}
