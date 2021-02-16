package com.faraway.fwportal.model.domain;

public enum PortaRest {

	GAUSS(""), CDG("50215"), NSG(""), SPG("");

	private final String porta;

	private PortaRest(String p) {
		this.porta = p;
	}

	public String getPorta() {
		return porta;
	}

}
