package it.stefano.turno.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.stefano.turno.entitys.Turno;
import it.stefano.turno.repositorys.TurnoRepository;

@Service
public class TurnoService {
	
	
	TurnoRepository turnoRepository;
	
	@Autowired
	public TurnoService(TurnoRepository turnoRepository) {
		this.turnoRepository = turnoRepository;
		
	}

	 public List<Turno> listaTurni() {
	        return (List<Turno>) turnoRepository.findAll();
	    }

	public void aggiungiTurno(Turno turno) {
		  turnoRepository.save(turno);
		
	}

	public Turno leggiTurnoById(Long idTurno) {
		return turnoRepository.findById(idTurno).orElseThrow(() -> new IllegalArgumentException("Id non presente"));
    }
	

	public void rimuoviTurnoById(Long idTurno) {
		turnoRepository.deleteById(idTurno);
		
	}
	}