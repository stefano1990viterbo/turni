package it.stefano.turno.services;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.stefano.turno.entitys.Turno;
import it.stefano.turno.repositorys.MezzoRepository;
import it.stefano.turno.repositorys.TurnoRepository;

@Service
public class TurnoService {
	 Logger logger = LoggerFactory.getLogger(TurnoService.class);

	TurnoRepository turnoRepository;
	MezzoRepository mezzoRepository;

	@Autowired
	public TurnoService(TurnoRepository turnoRepository, MezzoRepository mezzoRepository) {
		this.turnoRepository = turnoRepository;
		this.mezzoRepository = mezzoRepository;

	}

	public List<Turno> listaTurni() {
		return (List<Turno>) turnoRepository.findAll();
	}

	public void aggiungiTurno(Turno turno) {

//		boolean controlloMezzo = false;
//		/*
//		 * devo controllare se esiste un turno con gli stessi orari a cui corrisponde lo
//		 * stesso mezzo
//		 */
//		// controllo tutti i turni di quel mezzo
//		List<Turno> listaTurniByMezzo = turnoRepository.findByMezzo(turno.getMezzo().getTarga());
//
//		// filtro per orario dei turni di quel mezzo, comparandoli con i turni che
//		// voglio inserire.
//		// se mi torna true, significa che non ha trovato nessuna corrispondenza e
//		// quindi può essere inserito.
//
//		Predicate<Turno> orarioMezzoCongruo = t -> turno.getIstanteFine().isAfter(t.getIstanteFine())
//				|| turno.getIstanteFine().isBefore(t.getIstanteInizio())
//						&& turno.getIstanteInizio().isBefore(t.getIstanteInizio())
//				|| turno.getIstanteInizio().isBefore(t.getIstanteFine());
//
//		controlloMezzo = listaTurniByMezzo.stream().filter(orarioMezzoCongruo).count() == 0L;

		
		if(orarioMezzoCongruo(turno)) {
		turnoRepository.save(turno);
		}
		else {
			logger.error("MI DISPIACE IL MEZZO E' OCCUPATO IN QUEL MOMENTO");
		}
		

	}

	
	public boolean orarioMezzoCongruo(Turno turno) {

		List<Turno> listaTurniByMezzo= new ArrayList<>();
		try {
		listaTurniByMezzo = turnoRepository.findByMezzo(turno.getMezzo().getTarga());
		}
		catch (Exception e) {
			logger.error("QUALCOSA E' ANDATO MALE... FORSE IL MEZZO NON C'E'");
		}
		
		Predicate<Turno> orarioMezzoCongruo = t -> (turno.getIstanteInizio().isBefore(t.getIstanteInizio()))
				&& (turno.getIstanteFine().isBefore(t.getIstanteFine()))
				||  (turno.getIstanteInizio().isAfter(t.getIstanteInizio()))
				&& (turno.getIstanteFine().isAfter(t.getIstanteFine()));
		
		
		return listaTurniByMezzo.stream().filter(orarioMezzoCongruo).count() == 0L;
	}
	
	
	public Turno leggiTurnoById(Long idTurno) {
		return turnoRepository.findById(idTurno).orElseThrow(() -> new IllegalArgumentException("Id non presente"));
	}

	public void rimuoviTurnoById(Long idTurno) {
		turnoRepository.deleteById(idTurno);

	}
}