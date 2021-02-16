package com.faraway.fwportal.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.faraway.fwportal.model.domain.Transportadora;

public interface TransportadoraRepository extends CrudRepository<Transportadora, Long> {

	@Query("FROM Transportadora empresa join fetch empresa.endereco endereco join fetch endereco.cidade")
	Iterable<Transportadora> findAll();
}
