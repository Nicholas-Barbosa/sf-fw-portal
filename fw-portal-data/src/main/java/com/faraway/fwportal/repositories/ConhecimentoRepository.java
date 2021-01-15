package com.faraway.fwportal.repositories;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import com.faraway.fwportal.model.Conhecimento;

public interface ConhecimentoRepository extends CrudRepository<Conhecimento, Long> {

	Optional<Conhecimento> findByChave(String chave);

	Set<Conhecimento> findByNotasChave(String chaveNota);

	// @Query("FROM Conhecimento c where c.emissao between ?1 and ?2")
	@Cacheable("findByEmissaoBetween")
	Set<Conhecimento> findByEmissaoBetween(LocalDate dateBegin, LocalDate dateEnd);
}