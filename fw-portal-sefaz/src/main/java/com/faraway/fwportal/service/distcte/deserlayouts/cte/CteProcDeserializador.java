package com.faraway.fwportal.service.distcte.deserlayouts.cte;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.faraway.fwportal.dto.sefaz.distcte.callback.DocumentosZipDto;
import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.CteProc;
import com.faraway.fwportal.service.distcte.deserlayouts.evento.EventoCteDeserializador;
import com.faraway.fwportal.service.gzip.AbstractGzipService;

@Component
public class CteProcDeserializador extends AbstractGzipService {

	private static final Logger log = LoggerFactory.getLogger(CteProcDeserializador.class);

	private final EventoCteDeserializador eventoDeserializador;

	public CteProcDeserializador(EventoCteDeserializador eventoDeserializador) {
		super();
		this.eventoDeserializador = eventoDeserializador;
	}

	public CteProc stringToObject(String st) {
		log.info("Deserializing cte xml...");
		return CteProc.deserializeString(st);

	}

	public Set<CteProc> deserializaProcs(Collection<DocumentosZipDto> docs) {
		// TODO Auto-generated method stub
		log.info("Getting ctes...");
		ConcurrentMap<String, String> ctesCancelados = new ConcurrentHashMap<>(takeCtesCancelados(docs));

		return new HashSet<CteProc>(
				docs.parallelStream().filter(evnf -> evnf.getSchema().contains("procCTe")).map(doc -> {
					String xmlDecoded = super.decode(doc.getDocZip());
					CteProc response = this.stringToObject(xmlDecoded);
					return response;

				}).filter(cteProc -> !ctesCancelados.containsKey(cteProc.getChave())).collect(HashSet::new, Set::add,
						Set::addAll));
	}

	private ConcurrentMap<String, String> takeCtesCancelados(Collection<DocumentosZipDto> documentos) {
		return eventoDeserializador.deserializaEventos(documentos).parallelStream()
				.filter(evento -> evento.getStatus().equals("110181"))
				.collect(Collectors.toConcurrentMap(evento -> evento.getChave(), ev -> ev.getChave()));
	}
}
