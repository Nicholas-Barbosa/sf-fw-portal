package com.faraway.fwportal.service.springdatajpa;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.faraway.fwportal.model.Taxa;
import com.faraway.fwportal.repositories.TaxaRepository;
import com.faraway.fwportal.service.TaxaCrdService;

@Service
public class TaxaSDJpaService implements TaxaCrdService {

	private final Map<String, Taxa> map = new ConcurrentHashMap<>();

	private final TaxaRepository taxaRepository;

	public TaxaSDJpaService(TaxaRepository taxaRepository) {
		super();
		this.taxaRepository = taxaRepository;
	}

	@Override
	public Set<Taxa> findAll() {
		Set<Taxa> taxas = new HashSet<>();
		taxaRepository.findAll().forEach(t -> taxas.add(t));
		return taxas;
	}

	@Override
	public Taxa findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Taxa save(Taxa object) {
		// TODO Auto-generated method stub
		return existsKeyOnMap(object.getNome()) ? map.get(object.getNome()) : saveAndPutOnMap(object);
	}

	@Override
	public void delete(Taxa object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public synchronized boolean existsKeyOnMap(String key) {
		if (map.isEmpty()) {
			map.putAll(this.findAll().parallelStream().collect(Collectors.toConcurrentMap(k -> k.getNome(), v -> v)));
		}
		return map.containsKey(key);
	}

	private Taxa saveAndPutOnMap(Taxa taxa) {
		map.put(taxa.getNome(), taxa);
		return taxaRepository.save(taxa);
	}
}
