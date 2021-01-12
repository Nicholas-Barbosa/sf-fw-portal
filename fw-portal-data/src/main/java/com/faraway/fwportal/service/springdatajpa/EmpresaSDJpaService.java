package com.faraway.fwportal.service.springdatajpa;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.faraway.fwportal.model.Empresa;
import com.faraway.fwportal.repositories.EmpresaRepository;
import com.faraway.fwportal.service.EmpresaCrdService;

@Service
public class EmpresaSDJpaService implements EmpresaCrdService {

	private final EmpresaRepository empresaRepository;

	private final ConcurrentMap<String, Empresa> map = new ConcurrentHashMap<>();

	public EmpresaSDJpaService(EmpresaRepository empresaRepository) {
		super();
		this.empresaRepository = empresaRepository;
	}

	@Override
	public Set<Empresa> findAll() {
		Set<Empresa> empresas = new HashSet<>();
		empresaRepository.findAll().forEach(e -> empresas.add(e));
		return empresas;
	}

	@Override
	public Empresa findById(Long id) {
		return empresaRepository.findById(id).orElse(null);
	}

	@Override
	public Empresa save(Empresa object) {
		return existsKeyOnMap(object.getCnpj()) ? map.get(object.getCnpj()) : saveAndPutOnMap(object);
	}

	@Override
	public void delete(Empresa object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public synchronized boolean existsKeyOnMap(String key) {
		if (map.isEmpty()) {
			map.putAll(this.findAll().parallelStream().collect(Collectors.toConcurrentMap(k -> k.getCnpj(), v -> v)));
		}

		return map.containsKey(key);
	}

	private Empresa saveAndPutOnMap(Empresa empresaToSave) {

		map.put(empresaToSave.getCnpj(), empresaToSave);
		return empresaRepository.save(empresaToSave);
	}
}
