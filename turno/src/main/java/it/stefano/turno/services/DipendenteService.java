package it.stefano.turno.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.stefano.turno.entitys.Dipendente;
import it.stefano.turno.repositorys.DipendenteRepository;

@Service
public class DipendenteService {

	DipendenteRepository dipendenteRepository;

	@Autowired
	public DipendenteService(DipendenteRepository dipendenteRepository) {
		this.dipendenteRepository = dipendenteRepository;

	}

	public List<Dipendente> listaDipendenti() {
		return (List<Dipendente>) dipendenteRepository.findAll();
	}

	public Dipendente aggiungiDipendente(Dipendente dipendente) {
		return dipendenteRepository.save(dipendente);

	}

	public Dipendente leggiDipendenteById(Long idDipendente) {
		return dipendenteRepository.findById(idDipendente)
				.orElseThrow(() -> new IllegalArgumentException("Id non presente"));
	}

	public void rimuoviDipendenteById(Long idDipendente) {
		dipendenteRepository.deleteById(idDipendente);

	}
}
