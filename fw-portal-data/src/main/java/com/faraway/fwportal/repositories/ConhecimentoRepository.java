package com.faraway.fwportal.repositories;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.faraway.fwportal.model.Conhecimento;

public interface ConhecimentoRepository extends PagingAndSortingRepository<Conhecimento, Long> {

	Optional<Conhecimento> findByChave(String chave);

	Set<Conhecimento> findByNotasChave(String chaveNota);

	Page<Conhecimento> findByEmissaoBetween(LocalDate dateBegin, LocalDate dateEnd, Pageable page);

	Page<Conhecimento> findByEmissaoBetweenAndEmitenteCnpj(LocalDate dateBegin, LocalDate dateEnd, String cnpj,
			Pageable page);

	Page<Conhecimento> findByEmissaoBetweenAndRemetenteCnpj(LocalDate dateBegin, LocalDate dateEnd, String cnpj,
			Pageable page);
}