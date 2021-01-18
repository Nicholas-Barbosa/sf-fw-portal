package com.faraway.fwportal.cachemanger;

import java.util.Collection;

import com.faraway.fwportal.exception.ObjectNotFoundException;

public interface CacheManagerService {

	void evictSingleCacheValue(String cacheName, String cacheKey);

	void evictAllCacheValues(String cacheName) throws ObjectNotFoundException;

	Collection<String> caches();
}
