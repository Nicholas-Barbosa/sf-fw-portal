package com.faraway.fwportal.brokerdataloader.conhecimento;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.stereotype.Component;

import com.faraway.fwportal.boostrap.dataloader.cidade.CidadeDataLoader;
import com.faraway.fwportal.boostrap.dataloader.conhecimento.ConhecimentoDataLoader;
import com.faraway.fwportal.boostrap.dataloader.entidade.transportadora.TransportadoraDataLoader;
import com.faraway.fwportal.dto.sefaz.distcte.callback.layout.proccte.CteProc;
import com.faraway.fwportal.model.domain.Cidade;
import com.faraway.fwportal.model.domain.Conhecimento;
import com.faraway.fwportal.model.domain.Empresa;
import com.faraway.fwportal.model.domain.Transportadora;

@Component
public class DistCteBrokerDataLoader implements ConhecimentoBrokerDataLoader {

	private final ConhecimentoDataLoader conhecimentoDataLoader;

	private final CidadeDataLoader cidadeDataLoader;

	private final TransportadoraDataLoader transportadoraDataLoader;

	public DistCteBrokerDataLoader(ConhecimentoDataLoader conhecimentoDataLoader, CidadeDataLoader cidadeDataLoader,
			TransportadoraDataLoader transportadoraDataLoader) {
		super();
		this.conhecimentoDataLoader = conhecimentoDataLoader;
		this.cidadeDataLoader = cidadeDataLoader;
		this.transportadoraDataLoader = transportadoraDataLoader;
	}

	@Override
	public Conhecimento brokerLoad(Object object) {
		CteProc cteProc = (CteProc) object;
		if (!conhecimentoDataLoader.checkIfCteExists(cteProc.getChave())) {
			ExecutorService executor = null;
			try {
				executor = Executors.newFixedThreadPool(3);
				Future<Cidade> cidadeInicioFtr = executor
						.submit(() -> takeCidade(cteProc.getCidadeInArrayWithNomeAndCodigoAndUf()));

				Future<Cidade> cidadeDestinoFtr = executor
						.submit(() -> takeCidade(cteProc.getCidadeDestinoInArrayWithNomeAndCodigoAndUf()));

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
