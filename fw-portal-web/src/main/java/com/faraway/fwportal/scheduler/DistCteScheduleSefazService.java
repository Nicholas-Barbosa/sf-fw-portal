package com.faraway.fwportal.scheduler;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.faraway.fwportal.boostrap.builder.entidade.empresa.EmpresaBuilder;
import com.faraway.fwportal.service.SefazService;
import com.faraway.fwportal.time.TimeHandler;

@Component
public class DistCteScheduleSefazService implements ScheduleService {

	private static final Logger log = LoggerFactory.getLogger(DistCteScheduleSefazService.class);

	private final SefazService distCteService;

	private final EmpresaBuilder empresaBuilder;

	public DistCteScheduleSefazService(SefazService distCteService, EmpresaBuilder empresaBuilder) {
		super();
		this.distCteService = distCteService;
		this.empresaBuilder = empresaBuilder;
	}

	/*
	 * Como em concurrency API do java se, fixedDelay cria e executa uma nova task
	 * apos um initial delay e so criar a proxima task, quando a ultima terminar e
	 * atingir o delay dado.
	 */
	@Scheduled(fixedDelay = 600000)
	@Override
	public void executeTask() {
		log.info("Thread started to read conhecimentos! " + DateTimeFormatter
				.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.MEDIUM).format(TimeHandler.getLocalDateTime()));
		// distCteService.findAndSave();
		String logradouro = "Cidade do Guaruja";
		String numero = "124";
		String cep = "12121221";
		String bairro = "Vila Cascatinha";
		String pais = "Brasil";
		System.out
				.println(empresaBuilder.setEndereco(logradouro, numero, cep, bairro, pais, "Sao Vicente", "PR", "2222")
						.buildObject("Nome", "Cnpj", "xxx", "2121212"));

	}

}
