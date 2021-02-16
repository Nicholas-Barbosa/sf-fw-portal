package com.faraway.fwportal.brokerdataloader.conhecimento;

import com.faraway.fwportal.brokerdataloader.BrokerDataLoader;
import com.faraway.fwportal.model.domain.Conhecimento;

public interface ConhecimentoBrokerDataLoader extends BrokerDataLoader<Conhecimento> {

	boolean checkIfCteExists(String key);
}
