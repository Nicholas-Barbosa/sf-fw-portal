package com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.Cte;
import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.Ide;
import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.fluxo.DestinatarioLayout;
import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.fluxo.EmitenteLayout;
import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.fluxo.RemetenteLayout;
import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.imposto.Icms00;
import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.metanorm.carga.CargaLayout;
import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.metanorm.doc.NfeSfz;
import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.cte.meta.valor.ValorComponenteLayout;
import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.prot.ProtCte;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "cteProc", namespace = "http://www.portalfiscal.inf.br/cte")
public class CteProc {

	@JsonProperty("CTe")
	private Cte cte;

	@JsonProperty("protCTe")
	private ProtCte protCte;

	private String nsu;

	public Cte getCte() {
		return cte;
	}

	public ProtCte getProtCte() {
		return protCte;
	}

	public static CteProc deserializeString(String doc) {

		XmlMapper xmlMapper = new XmlMapper();
		try {
			return xmlMapper.readValue(doc, CteProc.class);

		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}
		return null;

	}

	public DestinatarioLayout getDestinatario() {
		return cte.getInfo().getDest();
	}

	public EmitenteLayout getEmitente() {
		return cte.getInfo().getEmit();
	}

	public RemetenteLayout getRemetente() {
		return cte.getInfo().getRem();
	}

	public String getChave() {
		return protCte.getInfoProt().getChaveCte().strip();
	}

	public String getCidadeInicio() {
		return cte.getInfo().getIde().getMunicipioInicio() + ", " + cte.getInfo().getIde().getMunicipioCodigo() + ", "
				+ cte.getInfo().getIde().getUfInicio();
	}

	public String getCidadeFim() {
		return cte.getInfo().getIde().getMunicipioFim() + ", " + cte.getInfo().getIde().getMunicipioFimCodigo() + ", "
				+ cte.getInfo().getIde().getUfFim();
	}

	public BigDecimal getTotal() {
		return new BigDecimal(cte.getInfo().getValor().getTotal());
	}

	public List<ValorComponenteLayout> getValores() {
		if (cte.getInfo().getValor().getComponentes() == null) {
			return new ArrayList<>();
		}
		return new ArrayList<>(cte.getInfo().getValor().getComponentes());
	}

	public Icms00 getImpostos() {
		Icms00 icms = cte.getInfo().getImposto().getIcms().getIcms00();
		if (icms == null) {
			icms = new Icms00(0d, 0f, 0d);
		}
		return icms;
	}

	public CargaLayout getCarga() {

		CargaLayout carga = cte.getInfo().getInformacao().getInfoCarga();

		return carga;
	}

	public boolean isntCteComplementar() {
		return cte.getInfo().getCteComplementar() == null;
	}

	public Set<NfeSfz> getNotas() {
		try {
			return cte.getInfo().getInformacao().getInfoDoc().getInfoNfe();
		} catch (Exception e) {
			System.out.println("cte " + protCte.getInfoProt().getChaveCte() + " sem notas");
			return null;
		}

	}

	public Ide getInfoCte() {
		return cte.getInfo().getIde();
	}

	public String getNsu() {
		return nsu;
	}

	public void setNsu(String nsu) {
		this.nsu = nsu;
	}

	@Override
	public String toString() {
		return "CteProc [protCte=" + protCte + "]";
	}

}
