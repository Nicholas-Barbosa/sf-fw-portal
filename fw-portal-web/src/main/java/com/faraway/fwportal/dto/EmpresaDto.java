package com.faraway.fwportal.dto;

import com.faraway.fwportal.model.Empresa;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


public class EmpresaDto extends SuperEntidadeDto {

	public EmpresaDto(Empresa empresa) {
		super.setCnpj(empresa.getCnpj());
		super.setNome(empresa.getNome());
		super.setEndereco(empresa.getEndereco());
	}

}
