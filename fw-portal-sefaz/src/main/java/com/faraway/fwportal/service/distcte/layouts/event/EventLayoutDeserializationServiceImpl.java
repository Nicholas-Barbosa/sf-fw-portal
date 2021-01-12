package com.faraway.fwportal.service.distcte.layouts.event;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.faraway.fwportal.dto.sefaz.distcte.callback.DocZipDto;
import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.procevento.EventoCte;
import com.faraway.fwportal.service.gzip.AbstractGzipService;

@Component
public class EventLayoutDeserializationServiceImpl extends AbstractGzipService
		implements EventLayoutDeserializationService {

	private final Map<String, String> mapCteCancelados = new ConcurrentHashMap<>();

	private static final Logger log = LoggerFactory.getLogger(EventLayoutDeserializationServiceImpl.class);

	@Override
	public EventoCte stringToObject(String st) {
		log.info("Deserializing event xml...");
		return EventoCte.deserializeString(st);
	}

	@Override
	public Set<EventoCte> getCollectionOf(Collection<DocZipDto> docs) {
		// TODO Auto-generated method stub
		log.info("getting cte events...");
		return new HashSet<EventoCte>(
				docs.parallelStream().filter(evnf -> evnf.getSchema().contains("procEvento")).map(event -> {

					String xmlDecoded = super.decode(event.getDocZip());

					EventoCte eventoResponse = this.stringToObject(xmlDecoded);
					if (eventoResponse.getStatus().equals("110181")) {
						System.out.println("CTE cancelado " + eventoResponse.getChave());
						mapCteCancelados.put(eventoResponse.getChave(), eventoResponse.getChave());
					}

					return eventoResponse;

				}).collect(HashSet::new, Set::add, Set::addAll));
	}

	@Override
	public boolean containsCteKeyOnMapOfCancelled(String key) {
		log.info("Verifying if conhecimento key #" + key + " contains on map...");
		return mapCteCancelados.containsKey(key);
	}

}
