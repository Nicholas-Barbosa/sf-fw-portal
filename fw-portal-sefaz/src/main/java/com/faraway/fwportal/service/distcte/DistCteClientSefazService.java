package com.faraway.fwportal.service.distcte;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.faraway.fwportal.dto.sefaz.distcte.callback.CteDistDfeResponse;
import com.faraway.fwportal.dto.sefaz.distcte.request.DistNsu;
import com.faraway.fwportal.dto.sefaz.distcte.request.RequestCte;
import com.faraway.fwportal.model.Certificado;
import com.faraway.fwportal.service.ClientSefazService;

@Service
public class DistCteClientSefazService implements ClientSefazService {

	private static final Logger log = LoggerFactory.getLogger(DistCteClientSefazService.class);

	private Certificado certificado;

	@Override
	public DistCteClientSefazService setCertficado(Certificado certficado) {
		this.certificado = certficado;
		return this;
	}

	@Override
	public DistCteClientSefazService setNsu(String nsu) {
		certificado.setUltimoNsuPesquisado(nsu);
		return this;
	}

	public static int contador = 0;

	@SuppressWarnings("unchecked")
	@Override
	public CteDistDfeResponse execute() {

		HttpPost post = new HttpPost("https://www1.cte.fazenda.gov.br/CTeDistribuicaoDFe/CTeDistribuicaoDFe.asmx");

		if (contador < 1) {
			contador++;
			try (CloseableHttpClient httpclient = certificado.getHttpClient()) {

				String cnpj = certificado.getCnpj();
				String nsu = certificado.getUltimoNsuPesquisado();

				log.info("Argumento NSU: #" + nsu);

				RequestCte msgEntrada = new RequestCte("1", "41", cnpj, new DistNsu(nsu));

				String xml = soapEnvelope(msgEntrada.toSerialize());

				post.setEntity(new ByteArrayEntity(xml.getBytes("UTF-8")));
				post.setHeader("Content-Type", "application/soap+xml");

				CloseableHttpResponse httpresponse = httpclient.execute(post);

				HttpEntity entity = httpresponse.getEntity();

				CteDistDfeResponse callBackCte = new CteDistDfeResponse().toDeserialize(EntityUtils.toString(entity));
				if (callBackCte.getStatus() != 138) {
					log.info("Fim da consulta, motivo " + callBackCte.getMotivo());
					return null;
				} else {
					if (callBackCte.getUltimoNsu().equals(callBackCte.getNsuMaximo())) {
						log.info("NSU maximo atingido!");
						return null;
					}
				}
				log.info("Buscas de cte concluida via argumento NSU: #" + nsu);
				return callBackCte;
			} catch (Exception e) {
				log.error("Exception!", e);

			}

			return null;
		}

		return null;
	}

	@Override
	public String soapEnvelope(String xml) {
		return "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:cted=\"http://www.portalfiscal.inf.br/cte/wsdl/CTeDistribuicaoDFe\">\r\n"
				+ "   <soap:Header/>\r\n" + "   <soap:Body>\r\n" + "      <cted:cteDistDFeInteresse>\r\n"
				+ "         <!--Optional:-->\r\n" + "         <cted:cteDadosMsg>" + xml + " </cted:cteDadosMsg>\r\n"
				+ "      </cted:cteDistDFeInteresse>\r\n" + "   </soap:Body>\r\n" + "</soap:Envelope>";
	}

}
