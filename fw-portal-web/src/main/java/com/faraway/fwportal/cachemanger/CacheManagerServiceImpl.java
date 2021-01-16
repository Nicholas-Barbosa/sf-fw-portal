package com.faraway.fwportal.cachemanger;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import com.faraway.fwportal.exception.CacheNotFoundException;

@Component
public class CacheManagerServiceImpl implements CacheManagerService {

	private final CacheManager cacheManager;

	private static final Logger log = LoggerFactory.getLogger(CacheManagerServiceImpl.class);

	public CacheManagerServiceImpl(CacheManager cacheManager) {
		super();
		this.cacheManager = cacheManager;
	}

	@Override
	public void evictSingleCacheValue(String cacheName, String cacheKey) {
		// TODO Auto-generated method stub

	}

	@Override
	public void evictAllCacheValues(String cacheName) {
		Locale.setDefault(new Locale("pt", "BR"));
		DateTimeFormatter formater = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.FULL);

		log.info("Finding cache: " + cacheName + " at " + formater.format(ZonedDateTime.now()) + "...");
		boolean containsCache = cacheManager.getCacheNames().parallelStream().anyMatch(ch -> ch.equals(cacheName));

		if (containsCache) {
			log.info("Cleaning cache: " + cacheName + " at " + formater.format(ZonedDateTime.now()) + "...");
			Cache cache = cacheManager.getCache(cacheName);
			cache.clear();
			log.info("Cache: " + cacheName + " at " + formater.format(ZonedDateTime.now()) + " cleaned successfully!");
			return;
		}
		log.info("Cache: " + cacheName + " at " + formater.format(ZonedDateTime.now()) + " does not exist!");
		throw new CacheNotFoundException("Cache " + cacheName + " does not exists!");

	}

}
