package com.faraway.fwportal.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.faraway.fwportal.model.domain.Empresa;

public interface EmpresaRepository extends CrudRepository<Empresa, Long> {

	@Query("FROM Empresa empresa join fetch empresa.endereco endereco join fetch endereco.cidade")
	Iterable<Empresa> findAll();
}
