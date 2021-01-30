package com.faraway.fwportal.dto;

import com.faraway.fwportal.model.Empresa;


public class EmpresaDto extends SuperEntidadeDto {

	public EmpresaDto(Empresa empresa) {
		super.setCnpj(empresa.getCnpj());
		super.setNome(empresa.getNome());
		super.setEndereco(empresa.getEndereco());
	}

}
