package com.faraway.fwportal.boostrap.builder.endereco;

import com.faraway.fwportal.boostrap.builder.Builder;
import com.faraway.fwportal.model.Endereco;

public interface EnderecoBuilder extends Builder<Endereco> {

	EnderecoBuilder setCidade(Object... args);
}
