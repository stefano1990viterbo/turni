package it.stefano.turno.controllers;

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
import it.stefano.turno.entitys.Turno;
import it.stefano.turno.services.TurnoService;


@RestController
@RequestMapping("/turni")
public class TurnoController {

	ModelMapper modelMapper;

	TurnoService turnoService;

	@Autowired
	public TurnoController(TurnoService turnoService, ModelMapper modelMapper) {
		this.turnoService = turnoService;
		this.modelMapper = modelMapper;
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
		return modelMapper.map(turnoDTO, Turno.class);
	}
}