package com.faraway.fwportal.service.springdatajpa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.faraway.fwportal.exception.ObjectNotFoundException;
import com.faraway.fwportal.model.domain.Conhecimento;
import com.faraway.fwportal.repositories.ConhecimentoRepository;
import com.faraway.fwportal.service.ConhecimentoCrdService;
import com.faraway.fwportal.time.TimeHandler;

@Service
public class ConhecimentoSDJpaService implements ConhecimentoCrdService {

	private final ConhecimentoRepository conhecimentoRepository;

	private static final Logger log = LoggerFactory.getLogger(ConhecimentoSDJpaService.class);

	public ConhecimentoSDJpaService(ConhecimentoRepository conhecimentoRepository) {
		super();
		this.conhecimentoRepository = conhecimentoRepository;
	}

	private static final Map<String, Conhecimento> map = new ConcurrentHashMap<>();

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
		log.info("Checking if #" + key + " exists on map...");

		if (map.isEmpty()) {
			log.info("Map is empty.");
			map.putAll(this.findAll().parallelStream().map(Conhecimento::new)
					.collect(Collectors.toConcurrentMap(k -> k.getChave(), v -> v)));

		}
		log.info("#" + key + " checked.");
		return map.containsKey(key);
	}

	@Cacheable("conhecimentoFindByChave")
	@Override
	public Optional<Conhecimento> findByChave(String chave) throws ObjectNotFoundException {
		log.info("Searching conhecimento by key...");
		if (map.isEmpty() || !existsKeyOnMap(chave)) {
			return findAndPutOnMap(chave);

		}
		log.info("Conhecimento found!");
		return Optional.of(map.get(chave));

	}

	@Cacheable("conhecimentoFindByNotasChave")
	@Override
	public Set<Conhecimento> findByNota(String chaveNota) throws ObjectNotFoundException {
		log.info("Searching conhecimento by nota...");
		Set<Conhecimento> conhecimentos = conhecimentoRepository.findByNotasChave(chaveNota);
		if (!conhecimentos.isEmpty()) {
			log.info("Conhecimento by nota found.");
			return conhecimentoRepository.findByNotasChave(chaveNota);
		}
		log.info("Conhecimento by nota not found!");
		throw new ObjectNotFoundException("Object that contains invoice key # " + chaveNota + " not found!");
	}

	@Override
	public Page<Conhecimento> findAllPage(Pageable page) {
		// TODO Auto-generated method stub
		LocalDate now = TimeHandler.getLocalDate();
		LocalDate begin = now.withDayOfMonth(1).minusMonths(3);
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
		log.info("Finding conhecimentos between " + begin.format(formatter) + " - " + now.format(formatter) + "...");
		return conhecimentoRepository.findByEmissaoBetween(begin, now, page);
	}

	@Override
	public Page<Conhecimento> findByEmitenteThreeMonths(String cnpj, Pageable page) {
		// TODO Auto-generated method stub
		LocalDate now = TimeHandler.getLocalDate();
		LocalDate begin = now.withDayOfMonth(1).minusMonths(3);
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
		log.info("Finding conhecimentos by emitente between " + begin.format(formatter) + " - " + now.format(formatter)
				+ "...");

		return conhecimentoRepository.findByEmissaoBetweenAndEmitenteCnpj(begin, now, cnpj, page);
	}

	@Cacheable("conhecimentoFindByRemetente")
	@Override
	public Page<Conhecimento> findByRemetenteThreeMonths(String cnpj, Pageable page) {
		LocalDate now = TimeHandler.getLocalDate();
		LocalDate begin = now.withDayOfMonth(1).minusMonths(5);
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
		log.info("Finding conhecimentos by remetente between " + begin.format(formatter) + " - " + now.format(formatter)
				+ "...");

		return conhecimentoRepository.findByEmissaoBetweenAndRemetenteCnpj(begin, now, cnpj, page);

	}

	private Optional<Conhecimento> findAndPutOnMap(String chave) {
		Optional<Conhecimento> response = conhecimentoRepository.findByChave(chave);
		response.ifPresentOrElse(c -> map.put(c.getChave(), c),
				() -> this.throwObjectException("Object with key #" + chave + " not found on hash table!"));
		log.info("Conhecimento found!");
		return response;
	}

	private void throwObjectException(String msg) {
		log.info("Conhecimento by key not found!");
		throw new NoSuchElementException(msg);
	}

}
