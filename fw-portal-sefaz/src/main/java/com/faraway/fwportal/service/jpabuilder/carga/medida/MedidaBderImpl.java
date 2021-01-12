package com.faraway.fwportal.service.jpabuilder.carga.medida;

import org.springframework.stereotype.Component;

import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.metanorm.carga.QuantidadeCargaLayout;
import com.faraway.fwportal.model.Carga;
import com.faraway.fwportal.model.Medida;

@Component
public class MedidaBderImpl implements MedidaBder {

	private final TipoMedidaBder tipoMedidaBuilder;

	public MedidaBderImpl(TipoMedidaBder tipoMedidaBuilder) {
		super();
		this.tipoMedidaBuilder = tipoMedidaBuilder;
	}

	@Override
	public Medida toJpaEntity(QuantidadeCargaLayout object, Carga carga) {
		Medida medida = new Medida(tipoMedidaBuilder.toJpaEntity(object.getMedida()), object.getQuantidade(), carga);
		return medida;
	}

	@Override
	public Medida toJpaEntity(String object) {
		// TODO Auto-generated method stub
		return null;
	}

}
