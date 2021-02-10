package com.faraway.fwportal.boostrap.builder.entidade;

import com.faraway.fwportal.model.Endereco;

public interface EntidadeBuilder<T> {

	T setEndereco(Object... args);

	T setEndereco(Endereco endereco);

	T setNome(String nome);

	T setCnpj(String cnpj);

	T setInscEstadual(String inscEstadual);

	T setFone(String fone);
}
