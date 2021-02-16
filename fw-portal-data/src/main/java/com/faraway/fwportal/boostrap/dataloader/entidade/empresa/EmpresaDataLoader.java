package com.faraway.fwportal.boostrap.dataloader.entidade.empresa;

import com.faraway.fwportal.boostrap.builder.entidade.EntidadeBuilder;
import com.faraway.fwportal.boostrap.dataloader.DataLoader;
import com.faraway.fwportal.model.domain.Empresa;

public interface EmpresaDataLoader extends DataLoader<Empresa>, EntidadeBuilder<EmpresaDataLoader> {

	@Override
	default EmpresaDataLoader setEndereco(Object... args) {
		// TODO Auto-generated method stub
		return null;
	}
}
