package com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta;

import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.fluxo.DestinatarioLayout;
import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.fluxo.EmitenteLayout;
import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.fluxo.RemetenteLayout;
import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.imposto.ImpostoLayout;
import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.metanorm.InformacaoCte;
import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.valor.Valor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InfCte {

	private Ide ide;

	private EmitenteLayout emit;

	private RemetenteLayout rem;

	private DestinatarioLayout dest;

	@JsonProperty("vPrest")
	private Valor valor;

	@JsonProperty("imp")
	private ImpostoLayout imposto;

	@JsonProperty("infCTeNorm")
	private InformacaoCte informacao;

	@JsonProperty("infCteComp")
	private CteComplementarLayout cteComplementar;

	public Ide getIde() {
		return ide;
	}

	public EmitenteLayout getEmit() {
		return emit;
	}

	public RemetenteLayout getRem() {
		return rem;
	}

	public DestinatarioLayout getDest() {
		return dest;
	}

	public Valor getValor() {
		return valor;
	}

	public ImpostoLayout getImposto() {
		return imposto;
	}

	public InformacaoCte getInformacao() {
		return informacao;
	}

	public CteComplementarLayout getCteComplementar() {
		return cteComplementar;
	}
}
