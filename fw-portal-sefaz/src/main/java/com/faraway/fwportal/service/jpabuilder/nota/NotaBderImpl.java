package com.faraway.fwportal.service.jpabuilder.nota;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collections;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.faraway.fwportal.dto.faraway.nota.NotaResponse;
import com.faraway.fwportal.model.Empresa;
import com.faraway.fwportal.model.Nota;
import com.faraway.fwportal.service.NotaCrdService;
import com.faraway.fwportal.service.distcte.DistCteSefazService;

@Component
public class NotaBderImpl implements NotaBder {

	private final NotaCrdService notaCrudService;

	private static final Logger log = LoggerFactory.getLogger(NotaBderImpl.class);

	public NotaBderImpl(NotaCrdService notaCrudService) {
		super();
		this.notaCrudService = notaCrudService;
	}

	@Override
	public Nota toJpaEntity(String chave, Empresa emitente, Empresa cliente) {
		log.info("converting nota to JPA entity...");
		Locale.setDefault(new Locale("pt", "BR"));

		NotaResponse response = findNota(chave);
		String numero = response.getNumero();
		String serie = response.getSerie();
		LocalDate emissao = LocalDate.parse(response.getEmissao(),
				DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
		BigDecimal total = new BigDecimal(response.getValor());
		Nota nota = new Nota(emitente, cliente, chave, numero, serie, emissao, total);
		log.info("nota converted to JPA entity!");
		return notaCrudService.save(nota);
	}

	@Override
	public Nota toJpaEntity(String object) {
		return null;
	}

	@Override
	public NotaResponse findNota(String chave) {
		log.info("Fetching nota #" + chave);

		String uri = "http://179.108.122.43:" + DistCteSefazService.currentProxyDoor + "/rest/FWNOTAS/" + chave;
		HttpHeaders headers = new HttpHeaders();

		// set `Content-Type` and `Accept` headers
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<HttpHeaders> request = new HttpEntity<>(headers);

		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

		NotaResponse response = restTemplate.exchange(uri, HttpMethod.GET, request, NotaResponse.class).getBody();
		log.info("Got response for #" + chave);
		return response;

	}

}
