package com.faraway.fwportal.service.jpabuilder.nota;

import com.faraway.fwportal.dto.faraway.nota.NotaResponse;
import com.faraway.fwportal.model.Empresa;
import com.faraway.fwportal.model.Nota;
import com.faraway.fwportal.service.jpabuilder.JpaBuilder;

public interface NotaBder extends JpaBuilder<Nota, String> {

	Nota toJpaEntity(String object, Empresa emitente, Empresa cliente);

	NotaResponse findNota(String chave);
}
