package com.faraway.fwportal.boostrap.builder.entidade;

public interface EntidadeBuilder<T> {

	T setEndereco(Object... args);

	T setNome(String nome);

	T setCnpj(String cnpj);

	T setInscEstadual(String inscEstadual);

	T setFone(String fone);
}
