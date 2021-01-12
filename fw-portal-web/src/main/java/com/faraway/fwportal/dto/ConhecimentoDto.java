package com.faraway.fwportal.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import com.faraway.fwportal.model.Conhecimento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonRootName("response")
@JsonPropertyOrder({ "chave", "numero", "serie", "emissao", "total", "taxas", "imposto", "emitente", "remetente",
		"destinatario", "origem", "destino", "carga", "notas" })
@JsonInclude(Include.NON_NULL)
public class ConhecimentoDto {

	@JacksonXmlProperty(isAttribute = true, localName = "chaveCte")
	@JsonProperty("chaveCte")
	private String chave;
	private String numero;
	private String serie;
	private BigDecimal total;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate emissao;

	private TransportadoraDto emitente;

	private EmpresaDto remetente;

	private EmpresaDto destinatario;

	private CidadeDto origem;

	private CidadeDto destino;

	@JsonProperty("camponentesPrest")
	private Set<TaxaConhecimentoDto> taxas;

	private ImpostoDto imposto;

	@JsonProperty("infoCarga")
	private CargaDto carga;

	private Set<NotaDto> notas;

	public ConhecimentoDto(Conhecimento conhecimento) {
		super();
		this.chave = conhecimento.getChave();
		this.numero = conhecimento.getNumero();
		this.serie = conhecimento.getSerie();
		this.total = conhecimento.getTotal();
		this.emissao = conhecimento.getEmissao();
		this.emitente = new TransportadoraDto(conhecimento.getEmitente());
		this.remetente = new EmpresaDto(conhecimento.getRemetente());
		this.destinatario = new EmpresaDto(conhecimento.getDestinatario());
		this.origem = new CidadeDto(conhecimento.getCidadeInicio());
		this.destino = new CidadeDto(conhecimento.getCidadeDestino());
		this.taxas = conhecimento.getTaxas().parallelStream().map(TaxaConhecimentoDto::new)
				.collect(ConcurrentSkipListSet::new, Set::add, Set::addAll);
		this.imposto = new ImpostoDto(conhecimento.getImposto());
		this.carga = new CargaDto(conhecimento.getCarga());
		this.notas = conhecimento.getNotas().parallelStream().map(NotaDto::new).collect(ConcurrentSkipListSet::new,
				Set::add, Set::addAll);
	}

	public String getChave() {
		return chave;
	}

	public String getNumero() {
		return numero;
	}

	public String getSerie() {
		return serie;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public LocalDate getEmissao() {
		return emissao;
	}

	public TransportadoraDto getEmitente() {
		return emitente;
	}

	public EmpresaDto getRemetente() {
		return remetente;
	}

	public EmpresaDto getDestinatario() {
		return destinatario;
	}

	public CidadeDto getOrigem() {
		return origem;
	}

	public CidadeDto getDestino() {
		return destino;
	}

	public Set<TaxaConhecimentoDto> getTaxas() {
		return new HashSet<TaxaConhecimentoDto>(taxas);
	}

	public ImpostoDto getImposto() {
		return imposto;
	}

	public CargaDto getCarga() {
		return carga;
	}

	public Set<NotaDto> getNotas() {
		return notas;
	}

}
