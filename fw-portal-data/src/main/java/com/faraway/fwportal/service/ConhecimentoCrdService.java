package com.faraway.fwportal.service;

import java.util.Optional;
import java.util.Set;

import com.faraway.fwportal.model.Conhecimento;

public interface ConhecimentoCrdService extends CrudService<Conhecimento, Long> {

	Optional<Conhecimento> findByChave(String chave);

	Set<Conhecimento> findByNota(String chaveNota);

	Set<Conhecimento> findAllLast3Months();
}
