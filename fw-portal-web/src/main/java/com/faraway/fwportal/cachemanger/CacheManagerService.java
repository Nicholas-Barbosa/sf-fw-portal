package com.faraway.fwportal.cachemanger;

public interface CacheManagerService {

	void evictSingleCacheValue(String cacheName, String cacheKey);

	void evictAllCacheValues(String cacheName);
}
