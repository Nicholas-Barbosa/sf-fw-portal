package com.faraway.fwportal.scheduler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.faraway.fwportal.service.ConhecimentoCrdService;
import com.faraway.fwportal.service.SefazService;

@Component
public class DistCteScheduleSefazService implements ScheduleSefazService {

	private static final Logger log = LoggerFactory.getLogger(DistCteScheduleSefazService.class);

	private final SefazService distCteService;

	private final ConhecimentoCrdService conhecimentoCrudService;

	public DistCteScheduleSefazService(SefazService distCteService, ConhecimentoCrdService conhecimentoCrudService) {
		super();
		this.distCteService = distCteService;
		this.conhecimentoCrudService = conhecimentoCrudService;
	}

	/*
	 * Como em concurrency API do java se, fixedDelay cria e executa uma nova task
	 * apos um initial delay e so criar a proxima task, quando a ultima terminar e
	 * atingir o delay dado.
	 */
	@Scheduled(fixedDelay = 60000)
	@Override
	public void executeTask() {
		log.info("Iniciando execucoes: " + DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.MEDIUM)
				.format(LocalDateTime.now()));
		// distCteService.findAndSave();
	}

}
