package com.faraway.fwportal.cachemanger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

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
		DateTimeFormatter formater = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);

		log.info("Cleaning cache " + cacheName + " at " + formater.format(LocalDateTime.now()) + "!");
		cacheManager.getCache(cacheName).clear();

	}

}
