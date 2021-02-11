package com.faraway.fwportal.boostrap.dataloader.entidade.transportadora;

import com.faraway.fwportal.boostrap.builder.entidade.EntidadeBuilder;
import com.faraway.fwportal.boostrap.dataloader.DataLoader;
import com.faraway.fwportal.model.Transportadora;

public interface TransportadoraDataLoader
		extends DataLoader<Transportadora>, EntidadeBuilder<TransportadoraDataLoader> {

	@Override
	default TransportadoraDataLoader setEndereco(Object... args) {
		// TODO Auto-generated method stub
		return null;
	}
}
