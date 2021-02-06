package com.faraway.fwportal.boostrap.builder.endereco;

import com.faraway.fwportal.boostrap.builder.Builder;
import com.faraway.fwportal.model.Endereco;

public interface EnderecoBuilder extends Builder<Endereco> {

	EnderecoBuilder setCidade(Object... args);

	EnderecoBuilder setLogradouro(String logradouro);

	EnderecoBuilder setNumero(String numero);

	EnderecoBuilder setCep(String cep);

	EnderecoBuilder setBairro(String bauirro);

	EnderecoBuilder setPais(String pais);
}
