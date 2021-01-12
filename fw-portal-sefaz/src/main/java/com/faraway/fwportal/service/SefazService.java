package com.faraway.fwportal.service;

import java.util.Set;

import com.faraway.fwportal.model.Certificado;

public interface SefazService {

	void findAndSave();

	Set<?> find(Certificado certificado);

}
