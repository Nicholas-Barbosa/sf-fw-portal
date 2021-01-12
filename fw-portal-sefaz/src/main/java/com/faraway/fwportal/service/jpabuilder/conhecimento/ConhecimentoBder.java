package com.faraway.fwportal.service.jpabuilder.conhecimento;

import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.CteProc;
import com.faraway.fwportal.model.Conhecimento;
import com.faraway.fwportal.service.jpabuilder.JpaBuilder;

public interface ConhecimentoBder extends JpaBuilder<Conhecimento,CteProc> {

	 boolean isExisting(String key);
		
}
