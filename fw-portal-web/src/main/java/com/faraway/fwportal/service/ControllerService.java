package com.faraway.fwportal.service;

import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.faraway.fwportal.exception.ObjectNotFoundException;

public interface ControllerService<DTO> {

	ResponseEntity<Set<DTO>> findByNota(String chave)throws ObjectNotFoundException;

	ResponseEntity<DTO> findByChave(String chave)throws ObjectNotFoundException;
}
