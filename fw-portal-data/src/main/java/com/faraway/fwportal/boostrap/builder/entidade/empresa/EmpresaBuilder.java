package com.faraway.fwportal.boostrap.builder.entidade.empresa;

import com.faraway.fwportal.boostrap.builder.Builder;
import com.faraway.fwportal.model.Empresa;

public interface EmpresaBuilder extends Builder<Empresa> {

	EmpresaBuilder setEndereco(Object... args);

	EmpresaBuilder setNome(String nome);

	EmpresaBuilder setCnpj(String cnpj);

	EmpresaBuilder setInscEstadual(String inscEstadual);

	EmpresaBuilder setFone(String fone);
}
