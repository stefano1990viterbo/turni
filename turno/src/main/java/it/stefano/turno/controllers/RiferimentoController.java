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

import it.stefano.turno.DTOs.RiferimentoDTO;
import it.stefano.turno.entitys.Riferimento;
import it.stefano.turno.services.RiferimentoService;


@RestController
@RequestMapping("/riferimento")
public class RiferimentoController {

	ModelMapper modelMapper;

	RiferimentoService riferimentoService;

	@Autowired
	public RiferimentoController(RiferimentoService riferimentoService, ModelMapper modelMapper) {
		this.riferimentoService = riferimentoService;
		this.modelMapper = modelMapper;
	}

	@GetMapping("/")
	public List<RiferimentoDTO> listaRiferimenti() {
		return riferimentoService.listaRiferimenti().stream().map(this::convertToDTO).collect(Collectors.toList());

	}

	@PostMapping("/")
	public void aggiungiRiferimento(@RequestBody @Valid RiferimentoDTO riferimentoDTO) {
		riferimentoService.aggiungiRiferimento(convertToEntity(riferimentoDTO));
	}

	@GetMapping("/{idRiferimento}")
	public RiferimentoDTO dettaglioRiferimento(@PathVariable Long idRiferimento) {
		Riferimento riferimento = riferimentoService.leggiRiferimentoById(idRiferimento);
		return convertToDTO(riferimento);
	}

	@DeleteMapping("/{idRiferimento}")
	public void rimuoviRiferimento(@PathVariable Long idRiferimento) {
		riferimentoService.rimuoviRiferimentoById(idRiferimento);
	}



	private RiferimentoDTO convertToDTO(Riferimento riferimento) {
		RiferimentoDTO riferimentoDTO;
		riferimentoDTO = modelMapper.map(riferimento, RiferimentoDTO.class);
		return riferimentoDTO;
	}

	private Riferimento convertToEntity(RiferimentoDTO riferimentoDTO) {
		return modelMapper.map(riferimentoDTO, Riferimento.class);
	}
}
