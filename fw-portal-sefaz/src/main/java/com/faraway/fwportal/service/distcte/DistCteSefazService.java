package com.faraway.fwportal.service.distcte;

import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.faraway.fwportal.dto.sefaz.distcte.callback.CallBackCte;
import com.faraway.fwportal.dto.sefaz.distcte.callback.DocZipDto;
import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.CteProc;
import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.procevento.EventoCte;
import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.procevento.InfEvento;
import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.procevento.RetEventoCTe;
import com.faraway.fwportal.model.Certificado;
import com.faraway.fwportal.service.CertificadoCrdService;
import com.faraway.fwportal.service.ClientSefazService;
import com.faraway.fwportal.service.SefazService;
import com.faraway.fwportal.service.distcte.layouts.cte.CteLayoutDeserializationService;
import com.faraway.fwportal.service.distcte.layouts.event.EventLayoutDeserializationService;
import com.faraway.fwportal.service.jpabuilder.conhecimento.ConhecimentoBder;

@Service
public class DistCteSefazService implements SefazService {

	private final ClientSefazService clientSefazService;

	private final CertificadoCrdService certificadoCrudService;

	private static final Logger log = LoggerFactory.getLogger(DistCteClientSefazService.class);

	private final EventLayoutDeserializationService eventLayoutDeserializationService;

	private final CteLayoutDeserializationService cteLayoutDeserializationService;

	private final ConhecimentoBder conhecimentoBuilder;

	public static String currentProxyDoor;

	private final Deque<String> nsuLidos = new ConcurrentLinkedDeque<>();

	private final Deque<String> nsuNaoLidos = new ConcurrentLinkedDeque<>();

	public DistCteSefazService(ClientSefazService clientSefazService, CertificadoCrdService certificadoCrudService,
			EventLayoutDeserializationService eventLayoutDeserializationService,
			CteLayoutDeserializationService cteLayoutDeserializationService, ConhecimentoBder conhecimentoBuilder) {
		super();
		this.clientSefazService = clientSefazService;
		this.certificadoCrudService = certificadoCrudService;
		this.eventLayoutDeserializationService = eventLayoutDeserializationService;
		this.cteLayoutDeserializationService = cteLayoutDeserializationService;
		this.conhecimentoBuilder = conhecimentoBuilder;
	}

	@Override
	public void findAndSave() {
		for (Certificado certificado : certificadoCrudService.findAll()) {
			log.info("Getting cte documents for certificate #" + certificado.getCnpj());

			Set<DocZipDto> documentos = find(certificado).parallelStream().map(callBack -> callBack.getLotesSchemas())
					.flatMap(ls -> ls.stream()).collect(Collectors.toSet());

			Map<Boolean, List<EventoCte>> cteCancelados = eventLayoutDeserializationService.getCollectionOf(documentos)
					.stream().collect(Collectors.partitioningBy(x -> x.getStatus().equals("110181")));

			Predicate<CteProc> predVerificaSeObjectEstaCancelado = cp -> !cteCancelados.get(true)
					.contains(new EventoCte(new RetEventoCTe(new InfEvento(cp.getChave(), "110181"))));

			Predicate<CteProc> predVerificaSeCteEComplementar = cp -> cp.isntCteComplementar();

			Predicate<CteProc> predVerificaSeCteExiste = cp -> !conhecimentoBuilder.isExisting(cp.getChave());

			Set<CteProc> cteProcs = new HashSet<>(cteLayoutDeserializationService
					.getCollectionOf(documentos).parallelStream().filter(predVerificaSeObjectEstaCancelado
							.and(predVerificaSeCteEComplementar).and(predVerificaSeCteExiste))
					.collect(Collectors.toSet()));

			long ctesLidos = cteProcs.parallelStream()
					.map(cp -> conhecimentoBuilder.toJpaEntity(cp, nsuLidos, nsuNaoLidos)).filter(cp -> cp != null)
					.collect(Collectors.counting());

			compareNsusAndUpdateCertificadoEntityObject(certificado);
			log.info(ctesLidos + " Conhecimentos objects read for certificate #" + certificado.getCnpj());
		}
	}

	@Override
	public Set<CallBackCte> find(Certificado certificado) {
		Set<CallBackCte> responses = new HashSet<>();
		clientSefazService.setCertficado(certificado);
		CallBackCte callBack;
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
