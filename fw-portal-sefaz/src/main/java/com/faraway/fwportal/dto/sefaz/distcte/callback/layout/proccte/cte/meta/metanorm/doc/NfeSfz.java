package com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.metanorm.doc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NfeSfz {

	@JsonProperty("chave")
	private String chave;

	public String getChave() {
		return chave;
	}

	@Override
	public String toString() {
		return "Nfe [chave=" + chave + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chave == null) ? 0 : chave.hashCode());
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
		NfeSfz other = (NfeSfz) obj;
		if (chave == null) {
			if (other.chave != null)
				return false;
		} else if (!chave.equals(other.chave))
			return false;
		return true;
	}

}
