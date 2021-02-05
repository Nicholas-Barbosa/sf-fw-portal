package com.faraway.fwportal.boostrap.builder.entidade.empresa;

import com.faraway.fwportal.boostrap.builder.Builder;
import com.faraway.fwportal.model.Empresa;

public interface EmpresaBuilder extends Builder<Empresa> {

	EmpresaBuilder setEndereco(Object... args);
}
