package com.faraway.fwportal.service.springdatajpa;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.faraway.fwportal.model.Transportadora;
import com.faraway.fwportal.repositories.TransportadoraRepository;
import com.faraway.fwportal.service.TransportadoraCrdService;

@Service
public class TransportadoraSDJpaService implements TransportadoraCrdService {

	private final TransportadoraRepository transportadoraRepository;

	private final ConcurrentMap<String, Transportadora> map = new ConcurrentHashMap<>();

	public TransportadoraSDJpaService(TransportadoraRepository transportadoraRepository) {
		super();
		this.transportadoraRepository = transportadoraRepository;
	}

	@Override
	public Set<Transportadora> findAll() {
		Set<Transportadora> transportadoras = new HashSet<>();
		transportadoraRepository.findAll().forEach(t -> transportadoras.add(t));
		return transportadoras;
	}

	@Override
	public Transportadora findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transportadora save(Transportadora object) {
		// TODO Auto-generated method stub
		return existsKeyOnMap(object.getCnpj()) ? map.get(object.getCnpj()) : saveAndPutOnMap(object);
	}

	@Override
	public void delete(Transportadora object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public synchronized boolean existsKeyOnMap(String key) {
		if (map.isEmpty()) {
			System.out.println("map empty");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			map.putAll(this.findAll().parallelStream()
					.collect(Collectors.toConcurrentMap(Transportadora::getCnpj, v -> v)));
		}
		return map.containsKey(key);
	}

	private Transportadora saveAndPutOnMap(Transportadora object) {
		map.put(object.getCnpj(), object);
		return transportadoraRepository.save(object);
	}
}
