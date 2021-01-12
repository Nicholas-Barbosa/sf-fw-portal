package com.faraway.fwportal.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.faraway.fwportal.model.Conhecimento;

public interface ConhecimentoRepository extends CrudRepository<Conhecimento, Long> {

	Optional<Conhecimento> findByChave(String chave);

	Set<Conhecimento> findByNotasChave(String chaveNota);
}
