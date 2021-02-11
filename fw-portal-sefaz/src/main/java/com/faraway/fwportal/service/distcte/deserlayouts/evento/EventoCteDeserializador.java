package com.faraway.fwportal.service.distcte.deserlayouts.evento;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.faraway.fwportal.dto.sefaz.distcte.callback.DocumentosZipDto;
import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.procevento.EventoCte;
import com.faraway.fwportal.service.gzip.AbstractGzipService;

@Component
public class EventoCteDeserializador extends AbstractGzipService {

	private static final Logger log = LoggerFactory.getLogger(EventoCteDeserializador.class);

	public EventoCte stringToObject(String st) {
		log.info("Deserializing event xml...");
		return EventoCte.deserializeString(st);
	}

	public Set<EventoCte> deserializaEventos(Collection<DocumentosZipDto> docs) {
		// TODO Auto-generated method stub
		log.info("getting cte events...");
		return new HashSet<EventoCte>(
				docs.parallelStream().filter(evnf -> evnf.getSchema().contains("procEvento")).map(event -> {

					String xmlDecoded = super.decode(event.getDocZip());

					EventoCte eventoResponse = this.stringToObject(xmlDecoded);

					return eventoResponse;

				}).collect(HashSet::new, Set::add, Set::addAll));
	}

}
