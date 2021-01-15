package it.stefano.turno.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.stefano.turno.DTOs.TurnoDTO;
import it.stefano.turno.entitys.Dipendente;
import it.stefano.turno.entitys.Mezzo;
import it.stefano.turno.entitys.Turno;
import it.stefano.turno.services.DipendenteService;
import it.stefano.turno.services.MezzoService;
import it.stefano.turno.services.TurnoService;

@RestController
@RequestMapping("/turni")
public class TurnoController {

	ModelMapper modelMapper;

	TurnoService turnoService;

//	Turno turno;

	MezzoService mezzoService;

	DipendenteService dipendenteService;

	@Autowired
	public TurnoController(TurnoService turnoService, ModelMapper modelMapper, MezzoService mezzoService,
			DipendenteService dipendenteService) {
		this.turnoService = turnoService;
		this.modelMapper = modelMapper;
		this.mezzoService = mezzoService;
		this.dipendenteService = dipendenteService;
	}

	@GetMapping("/")
	public List<TurnoDTO> listaTurni() {
		return turnoService.listaTurni().stream().map(this::convertToDTO).collect(Collectors.toList());

	}

	@PostMapping("/")
	public void aggiungiTurno(@RequestBody @Valid TurnoDTO turnoDTO) {
		turnoService.aggiungiTurno(convertToEntity(turnoDTO));
	}

	@GetMapping("/{idTurno}")
	public TurnoDTO dettaglioTurno(@PathVariable Long idTurno) {
		Turno turno = turnoService.leggiTurnoById(idTurno);
		return convertToDTO(turno);
	}

	@DeleteMapping("/{idTurno}")
	public void rimuoviTurno(@PathVariable Long idTurno) {
		turnoService.rimuoviTurnoById(idTurno);
	}

	private TurnoDTO convertToDTO(Turno turno) {
		TurnoDTO turnoDTO;
		turnoDTO = modelMapper.map(turno, TurnoDTO.class);
		return turnoDTO;
	}

	private Turno convertToEntity(TurnoDTO turnoDTO) {
		Turno turnoDaAggiungere = modelMapper.map(turnoDTO, Turno.class);

		Mezzo mezzoDaAggiungere = mezzoService.leggiMezzoByTarga(turnoDTO.getMezzo());
		List<Dipendente> equipaggioDaAggiungere = new ArrayList<Dipendente>();
		for (Long d : turnoDTO.getEquipaggio()) {
			Dipendente dipendenteDaAggiungere = dipendenteService.leggiDipendenteById(d);
			equipaggioDaAggiungere.add(dipendenteDaAggiungere);
		}
		turnoDaAggiungere.setEquipaggio(equipaggioDaAggiungere);
		turnoDaAggiungere.setMezzo(mezzoDaAggiungere);

		return turnoDaAggiungere;
	}
}