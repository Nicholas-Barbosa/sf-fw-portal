package com.faraway.fwportal.service;

import java.util.Collection;
import java.util.Set;

import com.faraway.fwportal.dto.sefaz.distcte.callback.DocumentosZipDto;

public interface LayoutDeserializationService<T> {

	T stringToObject(String st);
	
	Set<T>getCollectionOf(Collection<DocumentosZipDto> col);
}
