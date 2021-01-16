package com.faraway.fwportal.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.faraway.fwportal.model.Conhecimento;

public interface ConhecimentoCrdService extends CrudService<Conhecimento, Long> {

	Optional<Conhecimento> findByChave(String chave);

	Set<Conhecimento> findByNota(String chaveNota);


	Page<Conhecimento> findAllPage(Pageable page);
}
