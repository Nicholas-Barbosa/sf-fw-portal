package com.faraway.fwportal.service.distcte;

import java.util.Deque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.faraway.fwportal.dto.sefaz.distcte.callback.CallBackCte;
import com.faraway.fwportal.dto.sefaz.distcte.callback.DocZipDto;
import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.CteProc;
import com.faraway.fwportal.model.Certificado;
import com.faraway.fwportal.service.CertificadoCrdService;
import com.faraway.fwportal.service.SefazService;
import com.faraway.fwportal.service.distcte.layouts.cte.CteLayoutDeserializationService;
import com.faraway.fwportal.service.distcte.layouts.event.EventLayoutDeserializationService;
import com.faraway.fwportal.service.jpabuilder.conhecimento.ConhecimentoBder;

@Service
public class DistCteSefazService implements SefazService {

	private final DistCteClientSefazService distCteClient;

	private final CertificadoCrdService certificadoCrudService;

	private static final Logger log = LoggerFactory.getLogger(DistCteClientSefazService.class);

	private final EventLayoutDeserializationService eventLayoutDeserializationService;

	private final CteLayoutDeserializationService cteLayoutDeserializationService;

	private final ConhecimentoBder conhecimentoBuilder;

	public static String currentProxyDoor;

	private final Deque<String> nsuLidos = new ConcurrentLinkedDeque<>();

	private final Deque<String> nsuNaoLidos = new ConcurrentLinkedDeque<>();

	public DistCteSefazService(DistCteClientSefazService distCteClient, CertificadoCrdService certificadoCrudService,
			EventLayoutDeserializationService eventLayoutDeserializationService,
			CteLayoutDeserializationService cteLayoutDeserializationService, ConhecimentoBder conhecimentoBuilder) {
		super();
		this.distCteClient = distCteClient;
		this.certificadoCrudService = certificadoCrudService;
		this.eventLayoutDeserializationService = eventLayoutDeserializationService;
		this.cteLayoutDeserializationService = cteLayoutDeserializationService;
		this.conhecimentoBuilder = conhecimentoBuilder;
	}

	@Override
	public void findAndSave() {
		for (Certificado certificado : certificadoCrudService.findAll()) {
			log.info("Getting cte documents for certificate #" + certificado.getCnpj());
			Set<DocZipDto> documentos = find(certificado);

			eventLayoutDeserializationService.getCollectionOf(documentos);

			Set<CteProc> cteProcs = new HashSet<>(cteLayoutDeserializationService.getCollectionOf(documentos)
					.parallelStream()
					.filter(cp -> !eventLayoutDeserializationService.containsCteKeyOnMapOfCancelled(cp.getChave())
							&& cp.isntCteComplementar() && !conhecimentoBuilder.isExisting(cp.getChave()))
					.collect(Collectors.toSet()));

			long ctesLidos = cteProcs.parallelStream()
					.map(cp -> conhecimentoBuilder.toJpaEntity(cp, nsuLidos, nsuNaoLidos)).filter(cp -> cp != null)
					.collect(Collectors.counting());

			compareNsusAndUpdateCertificadoEntityObject(certificado);
			log.info(ctesLidos + " Conhecimentos objects read for certificate #" + certificado.getCnpj());
		}
	}

	@Override
	public Set<DocZipDto> find(Certificado certificado) {
		Set<DocZipDto> docs = new HashSet<>();
		distCteClient.setCertficado(certificado);
		distCteClient.setNsu(certificado.getUltimoNsuPesquisado());
		CallBackCte callBack;
		currentProxyDoor = certificado.getPorta().getPorta();

		while ((callBack = distCteClient.execute()) != null) {
			distCteClient.setNsu(callBack.getUltimoNsu());
			docs.addAll(callBack.getLotesSchemas());
			certificado.setUltimoNsuPesquisado(callBack.getUltimoNsu());
		}

		return docs;
	}

	private void compareNsusAndUpdateCertificadoEntityObject(Certificado certificado) {
		System.out.println("NSU NAO LIDOS " + nsuNaoLidos);
		System.out.println("NSU LIDOS " + nsuLidos);
		String firstNsuNaoLido = nsuNaoLidos.peekFirst();
		String nsuToInsert;
		if (!nsuLidos.isEmpty()) {
			if (!nsuNaoLidos.isEmpty()) {
				nsuLidos.removeIf(e -> e.compareTo(firstNsuNaoLido) > 0);
				nsuToInsert = nsuLidos.peekLast();
			} else {
				nsuToInsert = nsuLidos.peekLast();
			}
			certificado.setUltimoNsuPesquisado(nsuToInsert);
			certificadoCrudService.save(certificado);
		} else
			log.info("Nenhum NSU foi lido com sucesso!");
	}
}
