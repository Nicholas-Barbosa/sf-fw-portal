package com.faraway.fwportal.service.distcte;

import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.faraway.fwportal.brokerdataloader.conhecimento.ConhecimentoBrokerDataLoader;
import com.faraway.fwportal.dto.sefaz.distcte.callback.CteDistDfeResponse;
import com.faraway.fwportal.dto.sefaz.distcte.callback.DocumentosZipDto;
import com.faraway.fwportal.model.Certificado;
import com.faraway.fwportal.service.CertificadoCrdService;
import com.faraway.fwportal.service.ClientSefazService;
import com.faraway.fwportal.service.SefazService;
import com.faraway.fwportal.service.distcte.deserlayouts.cte.CteProcDeserializador;

@Service
public class DistCteSefazService implements SefazService {

	private static final Logger log = LoggerFactory.getLogger(DistCteClientSefazService.class);

	private final ClientSefazService clientSefazService;

	private final CertificadoCrdService certificadoCrudService;

	public static String currentProxyDoor;

	private final CteProcDeserializador procDeserializador;

	private final ConhecimentoBrokerDataLoader conhecimentoBrokerDataLoader;

	private final Deque<String> nsuLidos = new ConcurrentLinkedDeque<>();

	private final Deque<String> nsuNaoLidos = new ConcurrentLinkedDeque<>();

	public DistCteSefazService(ClientSefazService clientSefazService, CertificadoCrdService certificadoCrudService,
			CteProcDeserializador procDeserializador, ConhecimentoBrokerDataLoader conhecimentoBrokerDataLoader) {
		super();
		this.clientSefazService = clientSefazService;
		this.certificadoCrudService = certificadoCrudService;
		this.procDeserializador = procDeserializador;
		this.conhecimentoBrokerDataLoader = conhecimentoBrokerDataLoader;
	}

	@Override
	public void findAndSave() {
		for (Certificado certificado : certificadoCrudService.findAll()) {
			log.info("Getting cte documents for certificate #" + certificado.getCnpj());

			Set<DocumentosZipDto> documentos = find(certificado).parallelStream()
					.map(callBack -> callBack.getDocumentos()).flatMap(ls -> ls.stream()).collect(Collectors.toSet());

			procDeserializador.deserializaProcs(documentos).parallelStream()
					.map(procCte -> conhecimentoBrokerDataLoader.brokerLoad(procCte)).collect(Collectors.counting());

			// Predicate<CteProc> predVerificaSeCteEComplementar = cp ->
			// cp.isntCteComplementar();
//
//			Set<CteProc> cteProcs = new HashSet<>(cteLayoutDeserializationService.getCollectionOf(documentos)
//					.parallelStream().filter(predVerificaSeObjectEstaCancelado.and(predVerificaSeCteEComplementar))
//					.collect(Collectors.toSet()));
//
//			long ctesLidos = cteProcs.parallelStream()
//					.map(cp -> conhecimentoBuilder.toJpaEntity(cp, nsuLidos, nsuNaoLidos)).filter(cp -> cp != null)
//					.collect(Collectors.counting());

			// compareNsusAndUpdateCertificadoEntityObject(certificado);
			// log.info(ctesLidos + " Conhecimentos objects read for certificate #" +
			// certificado.getCnpj());
		}
	}

	@Override
	public Set<CteDistDfeResponse> find(Certificado certificado) {
		Set<CteDistDfeResponse> responses = new HashSet<>();
		clientSefazService.setCertficado(certificado);
		CteDistDfeResponse callBack;
		currentProxyDoor = certificado.getPorta().getPorta();
		while ((callBack = clientSefazService.setNsu(certificado.getUltimoNsuPesquisado()).execute()) != null) {
			responses.add(callBack);
			certificado.setUltimoNsuPesquisado(callBack.getUltimoNsu());
		}

		return responses;
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
