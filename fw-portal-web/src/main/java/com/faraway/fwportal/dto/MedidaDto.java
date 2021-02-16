package com.faraway.fwportal.dto;

import com.faraway.fwportal.model.domain.Medida;

public class MedidaDto implements Comparable<MedidaDto> {

	private String tipoMedida;

	private Float quantidade;

	public MedidaDto(Medida medida) {
		super();
		this.tipoMedida = medida.getMedida().getNome();
		this.quantidade = medida.getQuantidade();
	}

	public String getTipoMedida() {
		return tipoMedida;
	}

	public Float getQuantidade() {
		return quantidade;
	}

	@Override
	public int compareTo(MedidaDto o) {
		return quantidade.compareTo(o.getQuantidade());
	}

}
