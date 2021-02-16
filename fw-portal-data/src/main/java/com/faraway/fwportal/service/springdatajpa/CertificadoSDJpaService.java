package com.faraway.fwportal.service.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.faraway.fwportal.model.domain.Certificado;
import com.faraway.fwportal.repositories.CertificadoRepository;
import com.faraway.fwportal.service.CertificadoCrdService;

@Service
public class CertificadoSDJpaService implements CertificadoCrdService {

	private final CertificadoRepository certificadoRepository;

	public CertificadoSDJpaService(CertificadoRepository certificadoRepository) {
		super();
		this.certificadoRepository = certificadoRepository;
	}

	@Override
	public Set<Certificado> findAll() {
		Set<Certificado> certificados = new HashSet<>();
		certificadoRepository.findAll().forEach(c -> certificados.add(c));
		return certificados;
	}

	@Override
	public Certificado findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Certificado save(Certificado object) {
		// TODO Auto-generated method stub
		return certificadoRepository.save(object);
	}

	@Override
	public void delete(Certificado object) {
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
