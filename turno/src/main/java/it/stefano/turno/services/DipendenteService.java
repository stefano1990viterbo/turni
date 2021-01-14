package it.stefano.turno.services;

import java.util.List;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.stefano.turno.entitys.Dipendente;
import it.stefano.turno.repositorys.DipendenteRepository;

@Service
public class DipendenteService {
	Logger logger = LoggerFactory.getLogger(MezzoService.class);
	DipendenteRepository dipendenteRepository;

	@Autowired
	public DipendenteService(DipendenteRepository dipendenteRepository) {
		this.dipendenteRepository = dipendenteRepository;

	}

	public List<Dipendente> listaDipendenti() {
		return (List<Dipendente>) dipendenteRepository.findAll();
	}

	public Dipendente aggiungiDipendente(Dipendente dipendente) {

		try {
			dipendenteRepository.save(dipendente);
			logger.info("**** è stato inserito il dipendente: " + dipendente.getId() + " " + dipendente.getCognome()
					+ " " + dipendente.getNome());
			return dipendente;
		} catch (HibernateException e) {
			logger.error("**** non è stato possibile inserire il dipendente: " + dipendente.getId() + " "
					+ dipendente.getCognome() + " " + dipendente.getNome());
			throw e;
		}

	}

	public Dipendente leggiDipendenteById(Long idDipendente) {
		return dipendenteRepository.findById(idDipendente)
				.orElseThrow(() -> new IllegalArgumentException("Id non presente"));
	}

	public void rimuoviDipendenteById(Long idDipendente) {
		dipendenteRepository.deleteById(idDipendente);

	}
}
