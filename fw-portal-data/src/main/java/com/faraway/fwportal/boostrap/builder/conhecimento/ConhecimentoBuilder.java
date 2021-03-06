package com.faraway.fwportal.boostrap.builder.conhecimento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import com.faraway.fwportal.boostrap.builder.Builder;
import com.faraway.fwportal.model.domain.Carga;
import com.faraway.fwportal.model.domain.Cidade;
import com.faraway.fwportal.model.domain.Conhecimento;
import com.faraway.fwportal.model.domain.Empresa;
import com.faraway.fwportal.model.domain.Imposto;
import com.faraway.fwportal.model.domain.Nota;
import com.faraway.fwportal.model.domain.TaxaConhecimento;
import com.faraway.fwportal.model.domain.Transportadora;

public interface ConhecimentoBuilder extends Builder<Conhecimento> {

//	private String numero, serie, chave;
//	private LocalDate emissao;
//	private BigDecimal total;
//	private Cidade cidadeInicio, cidadeDestino;
//	private Transportadora emitente;
//	private Empresa remetente, destinatario;
//	private Set<Nota> notas = new HashSet<>();
//	private Imposto imposto;
//	private Carga carga;
//	private Set<TaxaConhecimento> taxas = new HashSet<>();

	ConhecimentoBuilder setNumero(String numero);

	ConhecimentoBuilder setSerie(String serie);

	ConhecimentoBuilder setChave(String chave);

	ConhecimentoBuilder setEmissao(LocalDate emissao);

	ConhecimentoBuilder setTotal(BigDecimal total);

	ConhecimentoBuilder setCidadeInicio(Cidade cidade);

	ConhecimentoBuilder setCidadeDestino(Cidade cidade);

	ConhecimentoBuilder setEmitente(Transportadora emitente);

	ConhecimentoBuilder setRemetente(Empresa remetente);

	ConhecimentoBuilder setDestinatario(Empresa destinatario);

	ConhecimentoBuilder setNotas(Set<Nota> notas);

	ConhecimentoBuilder setImposto(Imposto imposto);

	ConhecimentoBuilder setCarga(Carga carga);

	ConhecimentoBuilder setTaxas(Set<TaxaConhecimento> taxas);
}
