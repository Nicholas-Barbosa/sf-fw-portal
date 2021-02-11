package com.faraway.fwportal.boostrap.dataloader.conhecimento;

import java.util.Set;

import com.faraway.fwportal.boostrap.builder.conhecimento.ConhecimentoBuilder;
import com.faraway.fwportal.boostrap.dataloader.DataLoader;
import com.faraway.fwportal.model.Carga;
import com.faraway.fwportal.model.Cidade;
import com.faraway.fwportal.model.Conhecimento;
import com.faraway.fwportal.model.Entidade;
import com.faraway.fwportal.model.Imposto;
import com.faraway.fwportal.model.Nota;
import com.faraway.fwportal.model.TaxaConhecimento;

public interface ConhecimentoDataLoader extends DataLoader<Conhecimento> {

	ConhecimentoDataLoader setNumeroAndSerieAndChaveAndEmissaoAndTotal(Object... args);

	ConhecimentoDataLoader setCidadeAndCidadeDestino(Cidade... args);

	ConhecimentoDataLoader setEmitenteAndRemetenteAndDestinatario(Entidade... args);

	ConhecimentoBuilder setNotasAndTaxasCollection(Set<Nota> notas, Set<TaxaConhecimento> taxas);

	ConhecimentoBuilder setImpostoAndCarga(Imposto imposto, Carga carga);

}
