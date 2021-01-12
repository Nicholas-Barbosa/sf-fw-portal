package com.faraway.fwportal.service.jpabuilder.carga.medida;

import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.metanorm.carga.QuantidadeCargaLayout;
import com.faraway.fwportal.model.Carga;
import com.faraway.fwportal.model.Medida;
import com.faraway.fwportal.service.jpabuilder.JpaBuilder;

public interface MedidaBder extends JpaBuilder<Medida, String> {

	 Medida toJpaEntity(QuantidadeCargaLayout object,Carga carga);
		
	
}
