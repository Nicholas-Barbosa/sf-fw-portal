package com.faraway.fwportal.service.jpabuilder.carga.medida;

import org.springframework.stereotype.Component;

import com.faraway.fwportal.model.TipoMedida;
import com.faraway.fwportal.service.TipoMedidaCrdService;

@Component
public class TipoMedidaImpl implements TipoMedidaBder {

	private final TipoMedidaCrdService crudService;

	public TipoMedidaImpl(TipoMedidaCrdService crudService) {
		super();
		this.crudService = crudService;
	}

	@Override
	public TipoMedida toJpaEntity(String object) {
		TipoMedida tipo = new TipoMedida(object);
		return crudService.save(tipo);
	}

}
