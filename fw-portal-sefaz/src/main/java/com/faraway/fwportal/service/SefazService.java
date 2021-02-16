package com.faraway.fwportal.service;

import java.util.Set;

import com.faraway.fwportal.model.domain.Certificado;

public interface SefazService {

	void findAndSave();

	Set<?> find(Certificado certificado);

}
