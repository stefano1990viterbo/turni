package it.stefano.turno.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
		try {
			if (verificoTurnoMezzo(turno) && verificoTurnoEquipaggio(turno)) {
				turnoRepository.save(turno);
			} else {
				logger.error("CONTROLLARE ORARIO");
			}
			return turno;
		} catch (Exception e) {
			logger.error("CONTROLLARE ORARIO");
			throw e;
		}

	}

	public boolean possoInserireIlMezzo(Turno turno) {

		List<Turno> listaTurniByMezzo = new ArrayList<>();
		boolean mezzoDisponibile = false;
		try {
			listaTurniByMezzo = turnoRepository.findByMezzo(turno.getMezzo().getId());

		} catch (NoSuchElementException e) {
			logger.error("QUALCOSA E' ANDATO MALE... FORSE IL MEZZO NON C'E'");
			throw e;
		}

		mezzoDisponibile = controlloDisponibilita(listaTurniByMezzo, turno);
		if (listaTurniByMezzo.isEmpty()) {
			mezzoDisponibile = true;
		}

		return mezzoDisponibile;

	}

	public boolean verificoTurnoMezzo(Turno turno) {
		if (possoInserireIlMezzo(turno)) {
			return true;
		} else {
			logger.error("MI DISPIACE IL MEZZO E' OCCUPATO IN QUEL MOMENTO");
			return false;
		}
	}

	public boolean verificoTurnoEquipaggio(Turno turno) {

		boolean occupato = false;
		for (Dipendente d : turno.getEquipaggio()) {
			if (possoInserireIlDipendente(turno, d.getId())) {
				occupato = true;
			} else {
				logger.error(
						"il dipendente " + d.getId() + d.getNome() + d.getCognome() + " è occupato in quell'orario");

			}
		}
		return occupato;

	}

	public boolean possoInserireIlDipendente(Turno turno, Long id) {

		Dipendente dipendenteDaControllare = new Dipendente();
		List<Turno> listaTurniByDipendente = new ArrayList<>();
		List<Long> listaIdTurniByDipendente = new ArrayList<>();
		List<Long> listaDiControllo = new ArrayList<>();
		boolean dipendenteDiponibile = false;
		try {
			dipendenteDaControllare = dipendenteRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			logger.error("il dipendente non esiste");
			throw e;

		}
		listaIdTurniByDipendente = turnoRepository.findTurnoByDipendente(dipendenteDaControllare.getId());
		for (Long idTurno : listaIdTurniByDipendente) {
			Optional<Turno> turnoDaControllare = turnoRepository.findById(idTurno);
			listaTurniByDipendente.add(turnoDaControllare.get());
		}

		dipendenteDiponibile = controlloDisponibilita(listaTurniByDipendente, turno);

		return dipendenteDiponibile;

	}

	public boolean controlloDisponibilita(List<Turno> listaTurniDaControllare, Turno turno) {
		boolean controllo = false;
		int i = 0;
		for (Turno t : listaTurniDaControllare) {
			if ((t.getIstanteInizio().isBefore(turno.getIstanteInizio())
					&& t.getIstanteFine().isBefore(turno.getIstanteFine()))
					|| (t.getIstanteFine().isAfter(turno.getIstanteInizio())
							&& t.getIstanteFine().isAfter(turno.getIstanteFine()))) {
				i++;
			}
			if (i == listaTurniDaControllare.size()) {
				controllo = true;
			}
		}
		return controllo;
	}

	public Turno leggiTurnoById(Long idTurno) {
		return turnoRepository.findById(idTurno).orElseThrow(() -> new IllegalArgumentException("Id non presente"));
	}

	public void rimuoviTurnoById(Long idTurno) {
		turnoRepository.deleteById(idTurno);

	}
}