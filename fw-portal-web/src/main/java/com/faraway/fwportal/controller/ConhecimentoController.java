package com.faraway.fwportal.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.faraway.fwportal.dto.ConhecimentoDto;
import com.faraway.fwportal.service.conhecimento.ConhecimentoControllerService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/conhecimentos")
public class ConhecimentoController {

	private final ConhecimentoControllerService conhecimentoControllerService;

	public ConhecimentoController(ConhecimentoControllerService conhecimentoControllerService) {
		super();
		this.conhecimentoControllerService = conhecimentoControllerService;
	}

	@ApiOperation(value = "Return an object of conhecimento, finding by: 'chave 44 digits'")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Found!"),
			@ApiResponse(code = 403, message = "You are not allowed to access it!"),
			@ApiResponse(code = 500, message = "Internal exception!"), })
	@RequestMapping(value = "/findByChave/{chave}", produces = { "application/json",
			"application/xml" }, method = RequestMethod.GET)
	public ResponseEntity<ConhecimentoDto> findByChave(
			@ApiParam(required = true, name = "Chave 44 digits", value = "This parameter will be used to filter objects by the key, in an operations pipeline") @PathVariable("chave") String chave) {

		return conhecimentoControllerService.findByChave(chave);
	}

	@ApiOperation(value = "Return an object of conhecimento, finding by: 'nota chave 44 digits'")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK,Found!"),
			@ApiResponse(code = 403, message = "You are not allowed to access it!"),
			@ApiResponse(code = 500, message = "Internal exception!"), })
	@RequestMapping(value = "/findByNota/{chave}", produces = { "application/json",
			"application/xml", }, method = RequestMethod.GET)
	public ResponseEntity<Set<ConhecimentoDto>> findByNota(
			@ApiParam(required = true, name = "NOTA Chave 44 digits", value = "This parameter will be used to filter objects by the 'nota' key, in an operations pipeline") @PathVariable("chave") String chave) {

		return conhecimentoControllerService.findByNota(chave);
	}
}
