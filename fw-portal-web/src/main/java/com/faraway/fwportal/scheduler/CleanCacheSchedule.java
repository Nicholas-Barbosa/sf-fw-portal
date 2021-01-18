package com.faraway.fwportal.scheduler;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import com.faraway.fwportal.cachemanger.CacheManagerService;

public class CleanCacheSchedule implements ScheduleService {

	private final CacheManagerService cacheManagerService;

	private static final Logger log = LoggerFactory.getLogger(CleanCacheSchedule.class);

	public CleanCacheSchedule(CacheManagerService cacheManagerService) {
		super();
		this.cacheManagerService = cacheManagerService;
	}

	@Override
	@Scheduled(cron = "0 0 0 * * *")
	public void executeTask() {
		DateTimeFormatter formater = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.FULL);
		log.info("Thread started to clean cache! " + formater.format(ZonedDateTime.now()));
		try {
			cacheManagerService.evictAllCacheValues("findAll");
		} catch (Exception e) {
			log.info("Exception in thread while cleanning cache ", e);
		}

	}

}
