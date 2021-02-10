package com.faraway.fwportal.boostrap.dataloader.cidade;

import com.faraway.fwportal.boostrap.builder.cidade.CidadeBuilder;
import com.faraway.fwportal.boostrap.dataloader.DataLoader;
import com.faraway.fwportal.model.Cidade;

public interface CidadeDataLoader extends DataLoader<Cidade> {

	CidadeDataLoader setNome(String nome);

	CidadeDataLoader setUf(String uf);

	CidadeDataLoader setCodigoIbge(String codigo);
}
