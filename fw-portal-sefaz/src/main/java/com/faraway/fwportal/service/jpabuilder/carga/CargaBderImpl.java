package com.faraway.fwportal.service.jpabuilder.carga;

import java.math.BigDecimal;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.stereotype.Component;

import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.metanorm.carga.CargaLayout;
import com.faraway.fwportal.model.Carga;
import com.faraway.fwportal.model.Medida;
import com.faraway.fwportal.service.CargaCrdService;
import com.faraway.fwportal.service.jpabuilder.carga.medida.MedidaBder;

@Component
public class CargaBderImpl implements CargaBder {

	private final MedidaBder medidaBuilder;

	private final CargaCrdService cargaCrudService;

	public CargaBderImpl(MedidaBder medidaBuilder, CargaCrdService cargaCrudService) {
		super();
		this.medidaBuilder = medidaBuilder;
		this.cargaCrudService = cargaCrudService;
	}

	@Override
	public Carga toJpaEntity(CargaLayout object) {
		Carga carga = new Carga(new BigDecimal(object.getvCarga()), object.getProdutoPredominante());

		Set<Medida> medidas = object.getQuantidades().parallelStream().map(q -> {
			return medidaBuilder.toJpaEntity(q, carga);
		}).collect(CopyOnWriteArraySet::new, Set::add, Set::addAll);

		carga.setMedidas(medidas);
		return cargaCrudService.save(carga);
	}

	@Override
	public Carga toJpaEntity(String object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		cargaCrudService.deleteById(id);

	}

}
