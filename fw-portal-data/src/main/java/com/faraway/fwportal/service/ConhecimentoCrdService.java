package com.faraway.fwportal.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.faraway.fwportal.exception.ObjectNotFoundException;
import com.faraway.fwportal.model.domain.Conhecimento;

public interface ConhecimentoCrdService extends CrudService<Conhecimento, Long> {

	Optional<Conhecimento> findByChave(String chave) throws ObjectNotFoundException;

	Set<Conhecimento> findByNota(String chaveNota) throws ObjectNotFoundException;

	@Query
	Page<Conhecimento> findAllPage(Pageable page);

	Page<Conhecimento> findByEmitenteThreeMonths(String cnpj, Pageable page);

	Page<Conhecimento> findByRemetenteThreeMonths(String cnpj, Pageable page);
}
