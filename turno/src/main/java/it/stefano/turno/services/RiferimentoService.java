package it.stefano.turno.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.stefano.turno.entitys.Riferimento;
import it.stefano.turno.repositorys.RiferimentoRepository;

@Service
public class RiferimentoService {
RiferimentoRepository riferimentoRepository;
	
	@Autowired
	public RiferimentoService(RiferimentoRepository riferimentoRepository) {
		this.riferimentoRepository = riferimentoRepository;
		
	}

	 public List<Riferimento> listaRiferimenti() {
	        return (List<Riferimento>) riferimentoRepository.findAll();
	    }

	public void aggiungiRiferimento(Riferimento riferimento) {
		  riferimentoRepository.save(riferimento);
		
	}

	public Riferimento leggiRiferimentoById(Long idRiferimento) {
		return riferimentoRepository.findById(idRiferimento).orElseThrow(() -> new IllegalArgumentException("Id non presente"));
    }
	

	public void rimuoviRiferimentoById(Long idRiferimento) {
		riferimentoRepository.deleteById(idRiferimento);
		
	}
}
