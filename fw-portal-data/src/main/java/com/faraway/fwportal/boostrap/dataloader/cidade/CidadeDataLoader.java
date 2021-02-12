package com.faraway.fwportal.boostrap.dataloader.cidade;

import com.faraway.fwportal.boostrap.dataloader.DataLoader;
import com.faraway.fwportal.model.Cidade;
import com.faraway.fwportal.model.Conhecimento;
import com.faraway.fwportal.service.CidadeCrdService;
import com.faraway.fwportal.service.CrudService;

public interface CidadeDataLoader extends DataLoader<Cidade> {

	CidadeDataLoader setNome(String nome);

	CidadeDataLoader setUf(String uf);

	CidadeDataLoader setCodigoIbge(String codigo);

	
}
