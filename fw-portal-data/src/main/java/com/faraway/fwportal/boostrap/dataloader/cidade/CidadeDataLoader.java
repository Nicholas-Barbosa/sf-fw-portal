package com.faraway.fwportal.boostrap.dataloader.cidade;

import com.faraway.fwportal.boostrap.dataloader.DataLoader;
import com.faraway.fwportal.model.domain.Cidade;
import com.faraway.fwportal.model.domain.Conhecimento;
import com.faraway.fwportal.service.CidadeCrdService;
import com.faraway.fwportal.service.CrudService;

public interface CidadeDataLoader extends DataLoader<Cidade> {

	CidadeDataLoader setNome(String nome);

	CidadeDataLoader setUf(String uf);

	CidadeDataLoader setCodigoIbge(String codigo);

	
}
