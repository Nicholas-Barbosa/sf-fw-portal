package com.faraway.fwportal.service;

import java.util.Collection;
import java.util.Set;

import com.faraway.fwportal.dto.sefaz.distcte.callback.DocZipDto;

public interface LayoutDeserializationService<T> {

	T stringToObject(String st);
	
	Set<T>getCollectionOf(Collection<DocZipDto> col);
}
