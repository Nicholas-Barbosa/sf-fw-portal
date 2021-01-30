package com.faraway.fwportal.dto;

import com.faraway.fwportal.model.Transportadora;

public class TransportadoraDto extends SuperEntidadeDto {

	public TransportadoraDto(Transportadora emitente) {
		super.setCnpj(emitente.getCnpj());
		super.setNome(emitente.getNome());
		super.setEndereco(emitente.getEndereco());
	}

}
