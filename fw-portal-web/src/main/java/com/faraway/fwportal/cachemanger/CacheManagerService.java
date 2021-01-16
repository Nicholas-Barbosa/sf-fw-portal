package com.faraway.fwportal.cachemanger;

import java.util.Collection;

public interface CacheManagerService {

	void evictSingleCacheValue(String cacheName, String cacheKey);

	void evictAllCacheValues(String cacheName);

	Collection<String>caches();
}
