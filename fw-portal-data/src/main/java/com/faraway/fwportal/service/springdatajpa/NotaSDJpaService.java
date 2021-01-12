package com.faraway.fwportal.service.springdatajpa;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.faraway.fwportal.model.Nota;
import com.faraway.fwportal.repositories.NotaRepository;
import com.faraway.fwportal.service.NotaCrdService;

@Service
public class NotaSDJpaService implements NotaCrdService {

	private final NotaRepository notaRepository;

	private final ConcurrentMap<String, Nota> map = new ConcurrentHashMap<>();

	public NotaSDJpaService(NotaRepository notaRepository) {
		super();
		this.notaRepository = notaRepository;

	}

	@Override
	public Set<Nota> findAll() {
		Set<Nota> notas = new HashSet<Nota>();
		notaRepository.findAll().forEach(n -> notas.add(n));
		return notas;
	}

	@Override
	public Nota findById(Long id) {
		return notaRepository.findById(id).orElse(null);
	}

	@Override
	public Nota save(Nota object) {
		return existsKeyOnMap(object.getChave()) ? map.get(object.getChave()) : saveAndPutOnMap(object);
	}

	@Override
	public void delete(Nota object) {
		notaRepository.delete(object);

	}

	@Override
	public void deleteById(Long id) {
		notaRepository.deleteById(id);

	}

	@Override
	public synchronized boolean existsKeyOnMap(String key) {
		if (map.isEmpty()) {
			map.putAll(this.findAll().parallelStream().collect(Collectors.toConcurrentMap(k -> k.getChave(), v -> v)));
		}
		return map.containsKey(key);
	}

	private Nota saveAndPutOnMap(Nota nota) {
		map.put(nota.getChave(), nota);
		return notaRepository.save(nota);
	}
}
