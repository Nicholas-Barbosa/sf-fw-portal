package com.faraway.fwportal.boostrap.builder.cidade;

import com.faraway.fwportal.boostrap.builder.Builder;
import com.faraway.fwportal.model.Cidade;

public interface CidadeBuilder extends Builder<Cidade> {

	CidadeBuilder setNome(String nome);
	
	CidadeBuilder setUf(String uf);
	
	CidadeBuilder setCodigoIbge(String codigo);
}
