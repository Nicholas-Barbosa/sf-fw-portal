package com.faraway.fwportal.service.jpabuilder.entidade;

import com.faraway.fwportal.model.*;
import com.faraway.fwportal.service.jpabuilder.JpaBuilder;

public interface TransportadoraBder extends JpaBuilder<Transportadora, String> {

	Transportadora toJpaEntity(String object, Endereco endereco);
}
