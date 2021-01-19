package com.faraway.fwportal.cachemanger;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collection;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import com.faraway.fwportal.exception.ObjectNotFoundException;
import com.faraway.fwportal.time.TimeHandler;

@Component
public class CacheManagerServiceImpl implements CacheManagerService {

	private final CacheManager cacheManager;

	private static final Logger log = LoggerFactory.getLogger(CacheManagerServiceImpl.class);

	private final DateTimeFormatter formater = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL,
			FormatStyle.FULL);

	public CacheManagerServiceImpl(CacheManager cacheManager) {
		super();
		this.cacheManager = cacheManager;
	}

	@Override
	public void evictSingleCacheValue(String cacheName, String cacheKey) {
		// TODO Auto-generated method stub

	}

	@Override
	public void evictAllCacheValues(String cacheName) throws ObjectNotFoundException {
		Locale.setDefault(new Locale("pt", "BR"));

		log.info("Finding cache: " + cacheName + " at " + formater.format(ZonedDateTime.now().minusHours(1)) + "...");
		boolean containsCache = cacheManager.getCacheNames().parallelStream().anyMatch(ch -> ch.equals(cacheName));

		if (containsCache) {
			log.info("Cleaning cache: " + cacheName + " at " + formater.format(TimeHandler.getZonedDateTime()) + "...");
			Cache cache = cacheManager.getCache(cacheName);
			cache.clear();
			log.info("Cache: " + cacheName + " at " + formater.format(ZonedDateTime.now()) + " cleaned successfully!");
			return;
		}
		log.info("Cache: " + cacheName + " at " + formater.format(TimeHandler.getZonedDateTime()) + " does not exist!");
		throw new ObjectNotFoundException("Cache " + cacheName + " does not exists!");

	}

	@Override
	public Collection<String> caches() {
		// TODO Auto-generated method stub
		return cacheManager.getCacheNames();
	}

	@Override
	public void cleanAllCaches() {
		log.info("Cleaning all caches" + " at " + formater.format(TimeHandler.getZonedDateTime()) + "...");
		cacheManager.getCacheNames().forEach(name -> cacheManager.getCache(name).clear());
		log.info("All caches have been cleaned" + " at " + formater.format(ZonedDateTime.now().minusHours(1)) + "...");
	}

}
