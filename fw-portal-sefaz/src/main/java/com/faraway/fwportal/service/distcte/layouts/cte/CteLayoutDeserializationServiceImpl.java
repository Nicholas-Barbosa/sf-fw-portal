package com.faraway.fwportal.service.distcte.layouts.cte;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.faraway.fwportal.dto.sefaz.distcte.callback.DocZipDto;
import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.CteProc;
import com.faraway.fwportal.service.gzip.AbstractGzipService;

@Component
public class CteLayoutDeserializationServiceImpl extends AbstractGzipService
		implements CteLayoutDeserializationService {

	private static final Logger log = LoggerFactory.getLogger(CteLayoutDeserializationServiceImpl.class);

	public CteProc stringToObject(String st) {
		log.info("Deserializing cte xml...");
		return CteProc.deserializeString(st);

	}

	@Override
	public Set<CteProc> getCollectionOf(Collection<DocZipDto> docs) {
		// TODO Auto-generated method stub
		log.info("Getting ctes...");
		return new HashSet<CteProc>(
				docs.parallelStream().filter(evnf -> evnf.getSchema().contains("procCTe")).map(doc -> {

					String xmlDecoded = super.decode(doc.getDocZip());

					CteProc response = this.stringToObject(xmlDecoded);
					response.setNsu(doc.getNsu());
					return response;

				}).collect(HashSet::new, Set::add, Set::addAll));
	}

}
