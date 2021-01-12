package com.faraway.fwportal.service.conhecimento;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.faraway.fwportal.dto.ConhecimentoDto;
import com.faraway.fwportal.exception.ObjectNotFoundException;
import com.faraway.fwportal.model.Conhecimento;
import com.faraway.fwportal.service.ConhecimentoCrdService;

@Service
public class ConhecimentoControllerServiceImpl implements ConhecimentoControllerService {

	private final ConhecimentoCrdService conhecimentoCrudService;

	public ConhecimentoControllerServiceImpl(ConhecimentoCrdService conhecimentoCrudService) {
		super();
		this.conhecimentoCrudService = conhecimentoCrudService;
	}

	@Override
	public ResponseEntity<ConhecimentoDto> findByChave(String chave) throws ObjectNotFoundException {

		Optional<Conhecimento> conhecimentoEntity = conhecimentoCrudService.findByChave(chave);

		ConhecimentoDto responseDto = new ConhecimentoDto(conhecimentoEntity.orElseThrow(ObjectNotFoundException::new));

		return ResponseEntity.ok(responseDto);
	}

	@Override
	public ResponseEntity<Set<ConhecimentoDto>> findByNota(String chave) {
		Set<Conhecimento> conhecimentos = conhecimentoCrudService.findByNota(chave);

		if (conhecimentos.size() > 0)
			return ResponseEntity.ok(conhecimentos.stream().map(ConhecimentoDto::new).collect(Collectors.toSet()));
		throw new ObjectNotFoundException();
	}

}
