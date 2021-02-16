package com.faraway.fwportal.dto;

import com.faraway.fwportal.model.domain.Nota;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("nota")
public class NotaDto implements Comparable<NotaDto> {

	private String numero;

	private String serie;

	private String chave;

	public NotaDto(Nota nota) {
		super();
		this.numero = nota.getNumero();
		this.serie = nota.getSerie().strip();
		this.chave = nota.getChave().strip();
	}

	public String getNumero() {
		return numero;
	}

	public String getSerie() {
		return serie;
	}

	public String getChave() {
		return chave;
	}

	@Override
	public int compareTo(NotaDto o) {
		int byNumero = numero.compareTo(o.numero);
		int bySerie = serie.compareTo(o.serie);
		return byNumero - bySerie;
	}

}
