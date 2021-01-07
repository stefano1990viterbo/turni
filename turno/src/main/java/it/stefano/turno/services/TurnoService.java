package it.stefano.turno.services;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.stefano.turno.entitys.Dipendente;
import it.stefano.turno.entitys.Turno;
import it.stefano.turno.repositorys.DipendenteRepository;
import it.stefano.turno.repositorys.MezzoRepository;
import it.stefano.turno.repositorys.TurnoRepository;

@Service
public class TurnoService {
	static Logger logger = LoggerFactory.getLogger(TurnoService.class);

	TurnoRepository turnoRepository;
	MezzoRepository mezzoRepository;
	DipendenteRepository dipendenteRepository;

	@Autowired
	public TurnoService(TurnoRepository turnoRepository, MezzoRepository mezzoRepository,
			DipendenteRepository dipendenteRepository) {
		this.turnoRepository = turnoRepository;
		this.mezzoRepository = mezzoRepository;
		this.dipendenteRepository = dipendenteRepository;
	}

	public List<Turno> listaTurni() {
		return (List<Turno>) turnoRepository.findAll();
	}

	public Turno aggiungiTurno(Turno turno) {

		// controllo orario mezzo
		// controllo orario equipaggio
		// se i controlli sono ok, aggiungo il turno

		if (aggiungiMezzoAlTurno(turno) && aggiungiDipendenteAlTurno(turno)) {
			turnoRepository.save(turno);
		} else {
			logger.error("CONTROLLARE ORARIO");
		}
		return turno;
	}

	public boolean controlloTurnoMezzo(Turno turno) {

		List<Turno> listaTurniByMezzo = new ArrayList<>();
		try {
			listaTurniByMezzo = turnoRepository.findByMezzo(turno.getMezzo().getTarga());
		} catch (Exception e) {
			logger.error("QUALCOSA E' ANDATO MALE... FORSE IL MEZZO NON C'E'");
		}

		return listaTurniByMezzo.stream().filter(orarioCongruo(turno)).count() == 0L;
	}

	public boolean aggiungiMezzoAlTurno(Turno turno) {
		if (controlloTurnoMezzo(turno)) {
			return true;
		} else {
			logger.error("MI DISPIACE IL MEZZO E' OCCUPATO IN QUEL MOMENTO");
			return false;
		}
	}

	public boolean aggiungiDipendenteAlTurno(Turno turno) {

		boolean occupato = false;
		for (Dipendente d : turno.getEquipaggio()) {
			if (controlloTurnoDipendente(turno, d.getId())) {
				occupato = true;
			} else {
				logger.error(
						"il dipendente " + d.getId() + d.getNome() + d.getCognome() + " è occupato in quell'orario");

			}
		}
		return occupato;

	}

	public boolean controlloTurnoDipendente(Turno turno, Long id) {

		Dipendente dipendenteDaControllare = new Dipendente();
		List<Turno> listaTurniByDipendente = new ArrayList<>();
		try {
			dipendenteDaControllare = dipendenteRepository.findById(id).get();
		} catch (Exception e) {
			logger.error("il dipendente non esiste");
		}
		listaTurniByDipendente = turnoRepository.findByDipendente(dipendenteDaControllare.getId());
		return listaTurniByDipendente.stream().filter(orarioCongruo(turno)).count() == 0L;

	}

	public static Predicate<Turno> orarioCongruo(Turno turno) {
		return t -> (turno.getIstanteInizio().isAfter(t.getIstanteInizio())
				&& turno.getIstanteInizio().isBefore(t.getIstanteFine()))
				|| (turno.getIstanteFine().isAfter(t.getIstanteInizio())
						&& turno.getIstanteFine().isBefore(t.getIstanteFine()));

	}

	public Turno leggiTurnoById(Long idTurno) {
		return turnoRepository.findById(idTurno).orElseThrow(() -> new IllegalArgumentException("Id non presente"));
	}

	public void rimuoviTurnoById(Long idTurno) {
		turnoRepository.deleteById(idTurno);

	}
}