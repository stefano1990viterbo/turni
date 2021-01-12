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

import it.stefano.turno.DTOs.DipendenteDTO;
import it.stefano.turno.entitys.Dipendente;
import it.stefano.turno.services.DipendenteService;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

	ModelMapper modelMapper;

	DipendenteService dipendenteService;

	@Autowired
	public DipendenteController(DipendenteService dipendenteService, ModelMapper modelMapper) {
		this.dipendenteService = dipendenteService;
		this.modelMapper = modelMapper;
	}

	@GetMapping("/")
	public List<DipendenteDTO> listaDipendenti() {
		return dipendenteService.listaDipendenti().stream().map(this::convertToDTO).collect(Collectors.toList());

	}

	@PostMapping("/")
	public Dipendente aggiungiDipendente(@RequestBody @Valid DipendenteDTO dipendenteDTO) {
		return dipendenteService.aggiungiDipendente(convertToEntity(dipendenteDTO));
	}

	@GetMapping("/{idDipendente}")
	public DipendenteDTO dettaglioDipendente(@PathVariable Long idDipendente) {
		Dipendente dipendente = dipendenteService.leggiDipendenteById(idDipendente);
		return convertToDTO(dipendente);
	}

	@DeleteMapping("/{idDipendente}")
	public void rimuoviDipendente(@PathVariable Long idDipendente) {
		dipendenteService.rimuoviDipendenteById(idDipendente);
	}

	private DipendenteDTO convertToDTO(Dipendente dipendente) {
		DipendenteDTO dipendenteDTO;
		dipendenteDTO = modelMapper.map(dipendente, DipendenteDTO.class);
		return dipendenteDTO;
	}

	private Dipendente convertToEntity(DipendenteDTO dipendenteDTO) {
		return modelMapper.map(dipendenteDTO, Dipendente.class);
	}
}
