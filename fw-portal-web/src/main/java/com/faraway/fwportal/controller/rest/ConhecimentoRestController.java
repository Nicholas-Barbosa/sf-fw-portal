package com.faraway.fwportal.controller.rest;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faraway.fwportal.dto.ConhecimentoDto;
import com.faraway.fwportal.exception.ObjectNotFoundException;
import com.faraway.fwportal.model.Conhecimento;
import com.faraway.fwportal.service.ConhecimentoCrdService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "ConhecimentoRestController")
@RestController
@RequestMapping("/conhecimentos")
public class ConhecimentoRestController {

	private final ConhecimentoCrdService conhecimentoCrudService;

	public ConhecimentoRestController(ConhecimentoCrdService conhecimentoCrudService) {
		super();
		this.conhecimentoCrudService = conhecimentoCrudService;
	}

	@ApiOperation(value = "Return an object of conhecimento, finding by: 'chave 44 digits'")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Found!"),
			@ApiResponse(code = 403, message = "You are not allowed to access it!"),
			@ApiResponse(code = 500, message = "Internal exception!"), })
	@GetMapping(value = "/findByChave/{chave}", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<ConhecimentoDto> findByChave(
			@ApiParam(name = "chave", type = "String", value = "This parameter will be used to filter objects by the key, in an operations pipeline", required = true) @PathVariable("chave") String chave) {
		Optional<Conhecimento> conhecimentoEntity = conhecimentoCrudService.findByChave(chave);

		ConhecimentoDto responseDto = new ConhecimentoDto(conhecimentoEntity.orElseThrow(ObjectNotFoundException::new));
		return new ResponseEntity<ConhecimentoDto>(responseDto, HttpStatus.OK);
	}

	@ApiOperation(value = "Return an object of conhecimento, finding by: 'nota chave 44 digits'")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK,Found!"),
			@ApiResponse(code = 403, message = "You are not allowed to access it!"),
			@ApiResponse(code = 500, message = "Internal exception!"), })
	@GetMapping(value = "/findByNota/{chave}", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Set<ConhecimentoDto>> findByNota(
			@ApiParam(name = "chave", type = "String", value = "This parameter will be used to filter objects by the invoice key, in an operations pipeline", required = true) @PathVariable("chave") String chave) {

		Set<Conhecimento> conhecimentos = conhecimentoCrudService.findByNota(chave);
		if (conhecimentos.size() > 0)
			return new ResponseEntity<>(conhecimentos.stream().map(ConhecimentoDto::new).collect(Collectors.toSet()),
					HttpStatus.OK);

		throw new ObjectNotFoundException();
	}
}
