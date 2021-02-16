package com.faraway.fwportal.repositories;

import org.springframework.data.repository.CrudRepository;

import com.faraway.fwportal.model.domain.Taxa;

public interface TaxaRepository extends CrudRepository<Taxa, Long> {

}
