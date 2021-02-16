package com.faraway.fwportal.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import com.faraway.fwportal.model.domain.Carga;

public class CargaDto {

	private BigDecimal valor;

	private String produtoPredominante;

	private Set<MedidaDto> medidas;

	public CargaDto(Carga carga) {
		super();
		this.valor = carga.getValor();
		this.produtoPredominante = carga.getProdutoPredominante();
		this.medidas = carga.getMedidas().parallelStream().map(MedidaDto::new).collect(ConcurrentSkipListSet::new,
				Set::add, Set::addAll);
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getProdutoPredominante() {
		return produtoPredominante;
	}

	public Set<MedidaDto> getMedidas() {
		return new HashSet<>(medidas);
	}

}
