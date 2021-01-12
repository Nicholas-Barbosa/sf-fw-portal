package com.faraway.fwportal.service.jpabuilder.entidade;

import com.faraway.fwportal.model.*;
import com.faraway.fwportal.service.jpabuilder.JpaBuilder;

public interface EmpresaBder extends JpaBuilder<Empresa, String> {

	Empresa toJpaEntity(String object, Endereco endereco);
}
