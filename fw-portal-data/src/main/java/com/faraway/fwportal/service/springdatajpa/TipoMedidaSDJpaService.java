package com.faraway.fwportal.service.springdatajpa;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.faraway.fwportal.model.domain.TipoMedida;
import com.faraway.fwportal.repositories.TipoMedidaRepository;
import com.faraway.fwportal.service.TipoMedidaCrdService;

@Service
public class TipoMedidaSDJpaService implements TipoMedidaCrdService {

	private final TipoMedidaRepository tipoMedidaRepository;

	private final Map<String, TipoMedida> map = new ConcurrentHashMap<>();

	public TipoMedidaSDJpaService(TipoMedidaRepository tipoMedidaRepository) {
		super();
		this.tipoMedidaRepository = tipoMedidaRepository;
	}

	@Override
	public Set<TipoMedida> findAll() {
		Set<TipoMedida> tipos = new HashSet<>();
		tipoMedidaRepository.findAll().forEach(tp -> tipos.add(tp));
		return tipos;
	}

	@Override
	public TipoMedida findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoMedida save(TipoMedida object) {
		// TODO Auto-generated method stub
		return existsKeyOnMap(object.getNome()) ? map.get(object.getNome()) : saveAndPutOnMap(object);
	}

	@Override
	public void delete(TipoMedida object) {
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

	private TipoMedida saveAndPutOnMap(TipoMedida object) {
		map.put(object.getNome(), object);
		return tipoMedidaRepository.save(object);
	}
}
