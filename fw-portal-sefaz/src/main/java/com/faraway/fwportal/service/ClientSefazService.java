package com.faraway.fwportal.service;

import com.faraway.fwportal.model.domain.Certificado;

public interface ClientSefazService {

	ClientSefazService setCertficado(Certificado certficado);

	ClientSefazService setNsu(String nsu);

	<U> U execute();

	String soapEnvelope(String xml);

}
