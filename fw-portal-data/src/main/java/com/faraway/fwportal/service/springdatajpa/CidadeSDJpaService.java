package com.faraway.fwportal.service.springdatajpa;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.faraway.fwportal.model.Cidade;
import com.faraway.fwportal.repositories.CidadeRepository;
import com.faraway.fwportal.service.CidadeCrdService;

@Service
public class CidadeSDJpaService implements CidadeCrdService {

	private final ConcurrentMap<String, Cidade> map = new ConcurrentHashMap<>();

	private final CidadeRepository cidadeRepository;

	public CidadeSDJpaService(CidadeRepository cidadeRepository) {
		super();
		this.cidadeRepository = cidadeRepository;
	}

	@Override
	public Set<Cidade> findAll() {
		Set<Cidade> cidades = new HashSet<>();
		cidadeRepository.findAll().forEach(ct -> cidades.add(ct));
		return cidades;
	}

	@Override
	public Cidade findById(Long id) {
		// TODO Auto-generated method stub
		return cidadeRepository.findById(id).orElse(null);
	}

	@Override
	public Cidade save(Cidade object) {
		return existsKeyOnMap(object.getCodigoIbge()) ? map.get(object.getCodigoIbge()) : saveAndPutOnMap(object);
	}

	@Override
	public void delete(Cidade object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public synchronized boolean existsKeyOnMap(String key) {
		if (map.isEmpty()) {
			map.putAll(findAll().parallelStream().collect(Collectors.toConcurrentMap(k -> k.getCodigoIbge(), v -> v)));
		}
		return map.containsKey(key);
	}

	private Cidade saveAndPutOnMap(Cidade object) {
		map.put(object.getCodigoIbge(), object);
		return cidadeRepository.save(object);
	}

}
