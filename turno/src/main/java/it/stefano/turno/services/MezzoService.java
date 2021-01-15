package it.stefano.turno.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.stefano.turno.entitys.Mezzo;
import it.stefano.turno.repositorys.MezzoRepository;
import it.stefano.turno.repositorys.RiferimentoRepository;

@Service
public class MezzoService {
	Logger logger = LoggerFactory.getLogger(MezzoService.class);
	MezzoRepository mezzoRepository;
	RiferimentoRepository riferimentoRepository;

	@Autowired
	public MezzoService(MezzoRepository mezzoRepository, RiferimentoRepository riferimentoRepository) {
		this.mezzoRepository = mezzoRepository;
		this.riferimentoRepository = riferimentoRepository;
	}

	public List<Mezzo> listaMezzi() {
		return (List<Mezzo>) mezzoRepository.findAll();
	}

	public void aggiungiMezzo(Mezzo mezzo) {

		mezzo.getRiferimenti().stream().forEach(r -> riferimentoRepository.save(r));
		mezzoRepository.save(mezzo);
		logger.info("**** è stato inserito il mezzo con targa: " + mezzo.getTarga());

	}

	public Mezzo leggiMezzoByTarga(String targa) {
		return mezzoRepository.findByTarga(targa);
	}

	public void rimuoviMezzoByTarga(String targa) {
		mezzoRepository.deleteByTarga(targa);

	}
}
