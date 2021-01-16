package com.faraway.fwportal.controller;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faraway.fwportal.cachemanger.CacheManagerService;
import com.faraway.fwportal.dto.CacheDto;

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
		cacheManagerService.evictAllCacheValues(cacheName);
		DateTimeFormatter formater = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.FULL);
		String response = "Cache " + cacheName + " cleaned successfully at " + ZonedDateTime.now().format(formater);
		return new ResponseEntity<CacheDto>(new CacheDto(response), HttpStatus.OK);
	}
}
