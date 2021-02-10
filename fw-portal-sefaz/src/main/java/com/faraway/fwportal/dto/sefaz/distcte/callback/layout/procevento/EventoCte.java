package com.faraway.fwportal.dto.sefaz.distcte.callback.layout.procevento;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventoCte {

	@JsonProperty("retEventoCTe")
	private RetEventoCTe evento;

	public EventoCte() {
		// TODO Auto-generated constructor stub
	}
	
	public EventoCte(RetEventoCTe evento) {
		super();
		this.evento = evento;
	}

	public RetEventoCTe getEvento() {
		return evento;
	}

	public static EventoCte deserializeString(String st) {
		XmlMapper xmlMapper = new XmlMapper();
		try {
			return xmlMapper.readValue(st, EventoCte.class);

		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String toString() {
		return "ProcEventoCTe [evento=" + evento + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((evento == null) ? 0 : evento.hashCode());
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
		EventoCte other = (EventoCte) obj;
		if (evento == null) {
			if (other.evento != null)
				return false;
		} else if (!evento.equals(other.evento))
			return false;
		return true;
	}

	public String getStatus() {
		// TODO Auto-generated method stub
		return evento.getInfoEvento().getTpEvento();
	}

	public String getChave() {
		// TODO Auto-generated method stub
		return evento.getInfoEvento().getChaveCte();
	}

}
