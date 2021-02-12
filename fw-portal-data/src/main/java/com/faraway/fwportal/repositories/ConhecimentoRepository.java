package com.faraway.fwportal.repositories;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.faraway.fwportal.model.Conhecimento;

public interface ConhecimentoRepository extends PagingAndSortingRepository<Conhecimento, Long> {

	Optional<Conhecimento> findByChave(String chave);

	Set<Conhecimento> findByNotasChave(String chaveNota);

	Page<Conhecimento> findByEmissaoBetween(LocalDate dateBegin, LocalDate dateEnd, Pageable page);

	Page<Conhecimento> findByEmissaoBetweenAndEmitenteCnpj(LocalDate dateBegin, LocalDate dateEnd, String cnpj,
			Pageable page);

//
	@Query(value = "SELECT conhecimento FROM Conhecimento conhecimento LEFT JOIN conhecimento.cidadeInicio LEFT JOIN conhecimento.cidadeDestino LEFT JOIN conhecimento.emitente emitente"
			+ " LEFT JOIN emitente.endereco LEFT JOIN conhecimento.remetente remetente LEFT JOIN remetente.endereco"
			+ " LEFT JOIN conhecimento.destinatario destinatario LEFT JOIN destinatario.endereco"
			+ " LEFT JOIN conhecimento.taxas taxas LEFT JOIN taxas.taxa "
			+ "	LEFT JOIN conhecimento.notas notas LEFT JOIN notas.emitente nEmitente"
			+ "	LEFT JOIN nEmitente.endereco LEFT JOIN notas.destinatario nDest"
			+ " LEFT JOIN nDest.endereco LEFT JOIN conhecimento.imposto"
			+ "	LEFT JOIN conhecimento.carga carga LEFT JOIN carga.medidas medidas LEFT JOIN medidas.medida"
			+ " WHERE conhecimento.emissao between ?1 and ?2 AND remetente.cnpj = ?3", countQuery = "select count(conhecimento.id)"
					+ " FROM Conhecimento conhecimento LEFT JOIN conhecimento.remetente remetente WHERE conhecimento.emissao between ?1 and ?2 AND remetente.cnpj =?3")
	Page<Conhecimento> findByEmissaoBetweenAndRemetenteCnpj(LocalDate dateBegin, LocalDate dateEnd, String cnpj,
			Pageable page);
}