package com.faraway.fwportal.repositories;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.faraway.fwportal.model.Conhecimento;

public interface ConhecimentoRepository extends CrudRepository<Conhecimento, Long> {

	Optional<Conhecimento> findByChave(String chave);

	Set<Conhecimento> findByNotasChave(String chaveNota);

	@Cacheable("findByEmissaoBetween")
	Page<Conhecimento> findByEmissaoBetween(LocalDate dateBegin, LocalDate dateEnd, Pageable page);
}