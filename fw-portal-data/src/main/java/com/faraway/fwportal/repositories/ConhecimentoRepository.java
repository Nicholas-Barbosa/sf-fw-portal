package com.faraway.fwportal.repositories;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.faraway.fwportal.model.domain.Conhecimento;

public interface ConhecimentoRepository extends PagingAndSortingRepository<Conhecimento, Long> {

	Optional<Conhecimento> findByChave(String chave);

	Set<Conhecimento> findByNotasChave(String chaveNota);

	Page<Conhecimento> findByEmissaoBetween(LocalDate dateBegin, LocalDate dateEnd, Pageable page);

	Page<Conhecimento> findByEmissaoBetweenAndEmitenteCnpj(LocalDate dateBegin, LocalDate dateEnd, String cnpj,
			Pageable page);

//
	@Query(value = "SELECT conhecimento FROM Conhecimento conhecimento LEFT JOIN FETCH conhecimento.cidadeInicio LEFT JOIN FETCH conhecimento.cidadeDestino"
			+ " LEFT JOIN FETCH conhecimento.emitente emitente" + " LEFT JOIN FETCH emitente.endereco emitEndereco"
			+ " LEFT JOIN FETCH emitEndereco.cidade cEmit"
			+ " LEFT JOIN FETCH conhecimento.remetente remetente LEFT JOIN FETCH remetente.endereco remEndereco"
			+ " LEFT JOIN FETCH conhecimento.destinatario destinatario LEFT JOIN FETCH destinatario.endereco destEndereco"
			+ " LEFT JOIN FETCH conhecimento.taxas taxas LEFT JOIN FETCH taxas.taxa "
			+ "	LEFT JOIN FETCH conhecimento.notas notas LEFT JOIN FETCH notas.emitente nEmitente"
			+ "	LEFT JOIN FETCH nEmitente.endereco LEFT JOIN FETCH notas.destinatario nDest"
			+ " LEFT JOIN FETCH nDest.endereco LEFT JOIN FETCH conhecimento.imposto"
			+ "	LEFT JOIN FETCH conhecimento.carga carga LEFT JOIN FETCH carga.medidas medidas LEFT JOIN FETCH medidas.medida"
			+ " WHERE conhecimento.emissao between ?1 and ?2 AND remetente.cnpj = ?3", countQuery = "select count(conhecimento.id)"
					+ " FROM Conhecimento conhecimento LEFT JOIN conhecimento.remetente remetente WHERE conhecimento.emissao between ?1 and ?2 AND remetente.cnpj =?3")
	Page<Conhecimento> findByEmissaoBetweenAndRemetenteCnpj(LocalDate dateBegin, LocalDate dateEnd, String cnpj,
			Pageable page);
}