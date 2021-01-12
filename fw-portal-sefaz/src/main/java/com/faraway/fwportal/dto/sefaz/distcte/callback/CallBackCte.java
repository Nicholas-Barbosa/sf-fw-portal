package com.faraway.fwportal.dto.sefaz.distcte.callback;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "retDistDFeInt", namespace = "http://www.portalfiscal.inf.br/cte")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CallBackCte {

	@JacksonXmlProperty(localName = "ultNSU", namespace = "http://www.portalfiscal.inf.br/cte")
	private String ultimoNsu;

	@JacksonXmlProperty(localName = "maxNSU", namespace = "http://www.portalfiscal.inf.br/cte")
	private String nsuMaximo;

	@JacksonXmlProperty(localName = "loteDistDFeInt", namespace = "http://www.portalfiscal.inf.br/cte")
	private LoteDistDto lotes;

	@JacksonXmlProperty(localName = "cStat", namespace = "http://www.portalfiscal.inf.br/cte")
	private Integer status;

	@JacksonXmlProperty(localName = "xMotivo", namespace = "http://www.portalfiscal.inf.br/cte")
	private String motivo;

	public String getUltimoNsu() {
		return ultimoNsu;
	}

	public String getNsuMaximo() {
		return nsuMaximo;
	}

	public LoteDistDto getLotes() {
		return lotes;
	}

	public Integer getStatus() {
		return status;
	}

	public String getMotivo() {
		return motivo;
	}

	public CallBackCte toDeserialize(String response) throws JsonMappingException, JsonProcessingException {
		response = replaceTags(response).stripTrailing();
		XmlMapper xmlMapper = new XmlMapper();
		CallBackCte value = xmlMapper.readValue(response, CallBackCte.class);
		return value;
	}

	public String replaceTags(String xml) {
		return xml.replace(
				"<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">"
						+ "",
				"").replace("</soap:Envelope>", "").replace("<soap:Body>", "").replace("</soap:Body>", "")
				.replace(
						"<cteDistDFeInteresseResponse xmlns=\"http://www.portalfiscal.inf.br/cte/wsdl/CTeDistribuicaoDFe\">",
						"")
				.replace("</cteDistDFeInteresseResponse>", "").replace("<cteDistDFeInteresseResult>", "")
				.replace("</cteDistDFeInteresseResult>", "");

	}

	@Override
	public String toString() {
		return "CallBackCte [ultimoNsu=" + ultimoNsu + ", nsuMaximo=" + nsuMaximo + ", lotes=" + lotes + ", status="
				+ status + ", motivo=" + motivo + "]";
	}
	
	public List<DocZipDto> getLotesSchemas() {
		return this.getLotes().getDoc();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ultimoNsu == null) ? 0 : ultimoNsu.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CallBackCte other = (CallBackCte) obj;
		if (ultimoNsu == null) {
			if (other.ultimoNsu != null)
				return false;
		} else if (!ultimoNsu.equals(other.ultimoNsu))
			return false;
		return true;
	}

	
}
