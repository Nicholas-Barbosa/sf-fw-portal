package com.faraway.fwportal.boostrap.dataloader.conhecimento;

import java.util.Set;

import com.faraway.fwportal.boostrap.dataloader.DataLoader;
import com.faraway.fwportal.model.*;

public interface ConhecimentoDataLoader extends DataLoader<Conhecimento> {

	ConhecimentoDataLoader setNumeroAndSerieAndChaveAndEmissaoAndTotal(Object... args);

	ConhecimentoDataLoader setCidadeAndCidadeDestino(Cidade... args);

	ConhecimentoDataLoader setEmitenteAndRemetenteAndDestinatario(Entidade... args);

	ConhecimentoDataLoader setNotasAndTaxasCollection(Set<Nota> notas, Set<TaxaConhecimento> taxas);

	ConhecimentoDataLoader setImpostoAndCarga(Imposto imposto, Carga carga);

}
