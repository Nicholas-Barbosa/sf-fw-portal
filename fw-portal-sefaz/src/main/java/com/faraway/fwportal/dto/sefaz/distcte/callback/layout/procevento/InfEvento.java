package com.faraway.fwportal.dto.sefaz.distcte.callback.layout.procevento;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InfEvento {

	@JsonProperty("chCTe")
	private String chaveCte;

	private String tpEvento;

	@JsonProperty("xEvento")
	private String evento;

	public String getChaveCte() {
		return chaveCte;
	}

	public String getTpEvento() {
		return tpEvento;
	}

	public String getEvento() {
		return evento;
	}

	@Override
	public String toString() {
		return "InfEvento [chaveCte=" + chaveCte + ", tpEvento=" + tpEvento + ", evento=" + evento + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chaveCte == null) ? 0 : chaveCte.hashCode());
		result = prime * result + ((tpEvento == null) ? 0 : tpEvento.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InfEvento other = (InfEvento) obj;
		if (chaveCte == null) {
			if (other.chaveCte != null)
				return false;
		} else if (!chaveCte.equals(other.chaveCte))
			return false;
		if (tpEvento == null) {
			if (other.tpEvento != null)
				return false;
		} else if (!tpEvento.equals(other.tpEvento))
			return false;
		return true;
	}

}
