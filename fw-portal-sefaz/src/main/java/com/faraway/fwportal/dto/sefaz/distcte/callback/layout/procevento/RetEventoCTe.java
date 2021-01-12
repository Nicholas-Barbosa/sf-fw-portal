package com.faraway.fwportal.dto.sefaz.distcte.callback.layout.procevento;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RetEventoCTe {

	@JsonProperty("infEvento")
	private InfEvento infoEvento;

	public InfEvento getInfoEvento() {
		return infoEvento;
	}

	@Override
	public String toString() {
		return "RetEventoCTe [infoEvento=" + infoEvento + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((infoEvento == null) ? 0 : infoEvento.hashCode());
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
		RetEventoCTe other = (RetEventoCTe) obj;
		if (infoEvento == null) {
			if (other.infoEvento != null)
				return false;
		} else if (!infoEvento.equals(other.infoEvento))
			return false;
		return true;
	}
	
	
}
