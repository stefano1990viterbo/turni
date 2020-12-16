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

import it.stefano.turno.DTOs.MezzoDTO;
import it.stefano.turno.entitys.Mezzo;
import it.stefano.turno.services.MezzoService;

@RestController
@RequestMapping("/mezzo")
public class MezzoController {

	ModelMapper modelMapper;

	MezzoService mezzoService;

	@Autowired
	public MezzoController(MezzoService mezzoService, ModelMapper modelMapper) {
		this.mezzoService = mezzoService;
		this.modelMapper = modelMapper;
	}

	@GetMapping("/")
	public List<MezzoDTO> listaMezzi() {
		return mezzoService.listaMezzi().stream().map(this::convertToDTO).collect(Collectors.toList());

	}

	@PostMapping("/")
	public void aggiungiMezzo(@RequestBody @Valid MezzoDTO mezzoDTO) {

		mezzoService.aggiungiMezzo(convertToEntity(mezzoDTO));
	}

	@GetMapping("/{targa}")
	public MezzoDTO dettaglioMezzo(@PathVariable String targa) {
		Mezzo mezzo = mezzoService.leggiMezzoByTarga(targa);
		return convertToDTO(mezzo);
	}

	@DeleteMapping("/{targa}")
	public void rimuoviMezzo(@PathVariable String targa) {
		mezzoService.rimuoviMezzoByTarga(targa);
	}

	private MezzoDTO convertToDTO(Mezzo mezzo) {
		MezzoDTO mezzoDTO;
		mezzoDTO = modelMapper.map(mezzo, MezzoDTO.class);
		return mezzoDTO;
	}

	private Mezzo convertToEntity(MezzoDTO mezzoDTO) {

		Mezzo mezzo;
		mezzo = modelMapper.map(mezzoDTO, Mezzo.class);

		return mezzo;
	}
}
