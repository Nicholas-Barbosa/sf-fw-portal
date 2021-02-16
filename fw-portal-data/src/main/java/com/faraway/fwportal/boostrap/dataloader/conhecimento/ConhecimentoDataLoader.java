package com.faraway.fwportal.boostrap.dataloader.conhecimento;

import java.util.Set;

import com.faraway.fwportal.boostrap.dataloader.DataLoader;
import com.faraway.fwportal.model.domain.Carga;
import com.faraway.fwportal.model.domain.Cidade;
import com.faraway.fwportal.model.domain.Conhecimento;
import com.faraway.fwportal.model.domain.Entidade;
import com.faraway.fwportal.model.domain.Imposto;
import com.faraway.fwportal.model.domain.Nota;
import com.faraway.fwportal.model.domain.TaxaConhecimento;
import com.faraway.fwportal.service.ConhecimentoCrdService;

public interface ConhecimentoDataLoader extends DataLoader<Conhecimento> {

	ConhecimentoDataLoader setNumeroAndSerieAndChaveAndEmissaoAndTotal(Object... args);

	ConhecimentoDataLoader setCidadeAndCidadeDestino(Cidade... args);

	ConhecimentoDataLoader setEmitenteAndRemetenteAndDestinatario(Entidade... args);

	ConhecimentoDataLoader setNotasAndTaxasCollection(Set<Nota> notas, Set<TaxaConhecimento> taxas);

	ConhecimentoDataLoader setImpostoAndCarga(Imposto imposto, Carga carga);

	ConhecimentoCrdService getCurrentCrudServiceInstance();

	Boolean checkIfCteExists(String chave);
}
