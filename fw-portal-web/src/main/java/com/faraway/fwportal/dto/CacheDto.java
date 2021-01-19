package com.faraway.fwportal.dto;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CacheDto {

	private String result;

	private Collection<String> caches = new HashSet<>();

	public CacheDto() {
		// TODO Auto-generated constructor stub
	}

	public CacheDto(String result) {
		super();
		this.result = result;
	}

	public static Stream<CacheDto> toDtoCollection(String st) {
		CacheDto cacheDto = new CacheDto();
		cacheDto.addCacheToCollection(st);
		return Stream.of(cacheDto);
	}

	public String getResult() {
		return result;
	}

	public Collection<String> getCaches() {
		return new HashSet<String>(caches);
	}

	private void addCacheToCollection(String cacheName) {
		caches.add(cacheName);
	}
}
