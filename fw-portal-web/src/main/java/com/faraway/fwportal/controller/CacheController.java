package com.faraway.fwportal.controller;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.faraway.fwportal.cachemanger.CacheManagerService;
import com.faraway.fwportal.dto.CacheDto;
import com.faraway.fwportal.exception.ObjectNotFoundException;

@RestController
@RequestMapping("cache")
public class CacheController {

	private CacheManagerService cacheManagerService;

	public CacheController(CacheManagerService cacheManagerService) {
		super();
		this.cacheManagerService = cacheManagerService;
	}

	@GetMapping(value = "/clean/{cacheName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CacheDto> clearCache(@PathVariable("cacheName") String cacheName) {
		try {
			cacheManagerService.evictAllCacheValues(cacheName);
			DateTimeFormatter formater = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.FULL);
			String response = "Cache " + cacheName + " cleaned successfully at "
					+ ZonedDateTime.now().minusHours(1).format(formater);
			return new ResponseEntity<CacheDto>(new CacheDto(response), HttpStatus.OK);
		} catch (ObjectNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<CacheDto>> caches() {
		Collection<CacheDto> caches = cacheManagerService.caches().parallelStream().flatMap(CacheDto::toDtoCollection)
				.collect(CopyOnWriteArraySet::new, Collection::add, Collection::addAll);
		return new ResponseEntity<Collection<CacheDto>>(caches, HttpStatus.OK);
	}
}
