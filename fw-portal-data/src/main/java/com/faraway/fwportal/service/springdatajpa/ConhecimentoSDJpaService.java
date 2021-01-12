package com.faraway.fwportal.service.springdatajpa;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faraway.fwportal.model.Conhecimento;
import com.faraway.fwportal.repositories.ConhecimentoRepository;
import com.faraway.fwportal.service.ConhecimentoCrdService;

@Service
@Transactional
public class ConhecimentoSDJpaService implements ConhecimentoCrdService {

	private final ConhecimentoRepository conhecimentoRepository;

	private static final Logger log = LoggerFactory.getLogger(ConhecimentoSDJpaService.class);

	public ConhecimentoSDJpaService(ConhecimentoRepository conhecimentoRepository) {
		super();
		this.conhecimentoRepository = conhecimentoRepository;
	}

	private final Map<String, Conhecimento> map = new ConcurrentHashMap<>();

	@Override
	public Set<Conhecimento> findAll() {
		Set<Conhecimento> conhecimentos = new HashSet<>();
		conhecimentoRepository.findAll().forEach(c -> conhecimentos.add(c));
		return conhecimentos;
	}

	@Override
	public Conhecimento findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Conhecimento save(Conhecimento object) {
		// TODO Auto-generated method stub
		return existsKeyOnMap(object.getChave()) ? map.get(object.getChave()) : saveAndPutOnMap(object);
	}

	private Conhecimento saveAndPutOnMap(Conhecimento object) {
		map.put(object.getChave(), object);
		return conhecimentoRepository.save(object);
	}

	@Override
	public void delete(Conhecimento object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public synchronized boolean existsKeyOnMap(String key) {
		if (map.isEmpty()) {
			map.putAll(this.findAll().stream().map(Conhecimento::new)
					.collect(Collectors.toConcurrentMap(k -> k.getChave(), v -> v)));

		}
		return map.containsKey(key);
	}

	@Cacheable("conhecimentoFindByChave")
	@Override
	public Optional<Conhecimento> findByChave(String chave) {
		log.info("Searching conhecimento by key...");
		if (map.isEmpty() || !existsKeyOnMap(chave)) {
			return findAndPutOnMap(chave);
		}
		return Optional.of(map.get(chave));

	}

	private Optional<Conhecimento> findAndPutOnMap(String chave) {
		Optional<Conhecimento> response = conhecimentoRepository.findByChave(chave);
		response.ifPresent(c -> map.put(c.getChave(), c));
		return response;
	}

	@Cacheable("conhecimentoFindByNotasChave")
	@Override
	public Set<Conhecimento> findByNota(String chaveNota) {
		log.info("Searching conhecimento by nota...");
		return conhecimentoRepository.findByNotasChave(chaveNota);
	}

}
