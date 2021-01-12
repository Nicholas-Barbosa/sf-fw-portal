package com.faraway.fwportal.service.distcte.layouts.event;

import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.procevento.EventoCte;
import com.faraway.fwportal.service.LayoutDeserializationService;

public interface EventLayoutDeserializationService extends LayoutDeserializationService<EventoCte> {

	public boolean containsCteKeyOnMapOfCancelled(String key);

}
