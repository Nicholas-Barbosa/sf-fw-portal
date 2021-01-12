package com.faraway.fwportal.service;

import com.faraway.fwportal.model.Certificado;

public interface ClientSefazService {

	void setCertficado(Certificado certficado);

	void setNsu(String nsu);

	<U>U execute();

	String soapEnvelope(String xml);

}
