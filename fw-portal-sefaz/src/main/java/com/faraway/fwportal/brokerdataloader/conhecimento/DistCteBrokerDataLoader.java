package com.faraway.fwportal.brokerdataloader.conhecimento;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.stereotype.Component;

import com.faraway.fwportal.boostrap.dataloader.cidade.CidadeDataLoader;
import com.faraway.fwportal.boostrap.dataloader.conhecimento.ConhecimentoDataLoader;
import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.CteProc;
import com.faraway.fwportal.model.Cidade;
import com.faraway.fwportal.model.Conhecimento;

@Component
public class DistCteBrokerDataLoader implements ConhecimentoBrokerDataLoader {

	private final ConhecimentoDataLoader conhecimentoDataLoader;

	private final CidadeDataLoader cidadeDataLoader;

	public DistCteBrokerDataLoader(ConhecimentoDataLoader conhecimentoDataLoader, CidadeDataLoader cidadeDataLoader) {
		super();
		this.conhecimentoDataLoader = conhecimentoDataLoader;
		this.cidadeDataLoader = cidadeDataLoader;
	}

	@Override
	public Conhecimento brokerLoad(Object object) {
		CteProc cteProc = (CteProc) object;
		if (!conhecimentoDataLoader.checkIfCteExists(cteProc.getChave())) {
			ExecutorService executor = null;
			try {
				executor = Executors.newFixedThreadPool(2);
				Future<Cidade> cidadeInicioFtr = executor
						.submit(() -> takeCidade(cteProc.getCidadeInArrayWithNomeAndCodigoAndUf()));

				Future<Cidade> cidadeDestinoFtr = executor
						.submit(() -> takeCidade(cteProc.getCidadeDestinoInArrayWithNomeAndCodigoAndUf()));

				conhecimentoDataLoader.setCidadeAndCidadeDestino(new Cidade(cidadeInicioFtr.get()),
						new Cidade(cidadeDestinoFtr.get()));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				executor.shutdown();
			}

		}
		return null;
	}

	private Cidade takeCidade(String[] propertiesNomeAndCodigoAndUf) {
		return cidadeDataLoader.setNome(propertiesNomeAndCodigoAndUf[0]).setCodigoIbge(propertiesNomeAndCodigoAndUf[1])
				.setUf(propertiesNomeAndCodigoAndUf[2]).load();

	}

	@Override
	public boolean checkIfCteExists(String chave) {
		return conhecimentoDataLoader.checkIfCteExists(chave);
	}
}
