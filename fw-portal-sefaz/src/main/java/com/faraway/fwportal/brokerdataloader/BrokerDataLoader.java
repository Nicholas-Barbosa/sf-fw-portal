package com.faraway.fwportal.brokerdataloader;

public interface BrokerDataLoader<T> {

	T brokerLoad(Object o);
}
