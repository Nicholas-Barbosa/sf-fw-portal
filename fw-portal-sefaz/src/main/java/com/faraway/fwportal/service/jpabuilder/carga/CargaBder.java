package com.faraway.fwportal.service.jpabuilder.carga;

import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.metanorm.carga.CargaLayout;
import com.faraway.fwportal.model.Carga;
import com.faraway.fwportal.service.jpabuilder.JpaBuilder;

public interface CargaBder extends JpaBuilder<Carga, String> {

	Carga toJpaEntity(CargaLayout object);

	void delete(Carga carga);
}
