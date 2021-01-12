package com.faraway.fwportal.service.springdatajpa;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.faraway.fwportal.model.Carga;
import com.faraway.fwportal.repositories.CargaRepository;
import com.faraway.fwportal.service.CargaCrdService;

@Service
public class CargaSDJpaService implements CargaCrdService {

	private final CargaRepository cargaRepository;

	public CargaSDJpaService(CargaRepository cargaRepository) {
		super();
		this.cargaRepository = cargaRepository;
	}

	@Override
	public Set<Carga> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Carga findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Carga save(Carga object) {
		// TODO Auto-generated method stub
		return cargaRepository.save(object);
	}

	@Override
	public void delete(Carga object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean existsKeyOnMap(String key) {
		// TODO Auto-generated method stub
		return false;
	}

}
