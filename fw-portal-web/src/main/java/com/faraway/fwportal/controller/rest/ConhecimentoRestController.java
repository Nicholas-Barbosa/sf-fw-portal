package com.faraway.fwportal.controller.rest;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.faraway.fwportal.dto.ConhecimentoDto;
import com.faraway.fwportal.exception.ObjectNotFoundException;
import com.faraway.fwportal.model.Conhecimento;
import com.faraway.fwportal.service.ConhecimentoCrdService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

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

		ConhecimentoDto responseDto = new ConhecimentoDto(
				conhecimentoEntity.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Object not found on Pipeline!", new ObjectNotFoundException())));

		return new ResponseEntity<ConhecimentoDto>(responseDto, HttpStatus.OK);
	}

	@ApiOperation(value = "Return a Collection of conhecimento objects, finding by: 'nota chave 44 digits'")
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

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Collection for objects not found on Pipeline!",
				new ObjectNotFoundException());
	}

	

	@Cacheable(value = "findAll")
	@ApiOperation(value = "Return a Collection of conhecimento objects that have been issued in the last three months ")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/findAll")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)", defaultValue = "0"),
			@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page.", defaultValue = "10"),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). "
					+ "Default sort order is ascending. " + "Multiple sort criteria are supported.") })
	public ResponseEntity<Page<ConhecimentoDto>> findAllPage(
			@ApiIgnore("Ignored because swagger ui shows the wrong params, "
					+ "instead they are explained in the implicit params") @PageableDefault(sort = "emissao", page = 0, size = 10) Pageable currentPage) {

		Page<Conhecimento> conhecimentos = conhecimentoCrudService.findAllPage(currentPage);
		if (conhecimentos.getSize() > 0) {
			return new ResponseEntity<Page<ConhecimentoDto>>(conhecimentos.map(ConhecimentoDto::new), HttpStatus.OK);
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Collection for objects not found on Pipeline!",
				new ObjectNotFoundException());
	}
}
