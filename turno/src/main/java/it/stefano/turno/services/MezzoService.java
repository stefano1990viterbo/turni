package it.stefano.turno.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.stefano.turno.entitys.Mezzo;
import it.stefano.turno.entitys.Riferimento;
import it.stefano.turno.repositorys.MezzoRepository;
import it.stefano.turno.repositorys.RiferimentoRepository;
@Service
public class MezzoService {

	MezzoRepository mezzoRepository;
	RiferimentoRepository riferimentoRepository;
	@Autowired
	public MezzoService(MezzoRepository mezzoRepository,RiferimentoRepository riferimentoRepository) {
		this.mezzoRepository = mezzoRepository;
		this.riferimentoRepository=riferimentoRepository;
	}

	 public List<Mezzo> listaMezzi() {
	        return (List<Mezzo>) mezzoRepository.findAll();
	    }

	public void aggiungiMezzo(Mezzo mezzo) {
		
		if(mezzo.getRiferimenti()!=null) {
		for(Riferimento r : mezzo.getRiferimenti()) {
			//verificare che non esiste un numero uguale
			riferimentoRepository.save(r);
		}
		}
	
		mezzoRepository.save(mezzo);
		
	}

	public Mezzo leggiMezzoByTarga(String targa) {
		return mezzoRepository.findByTarga(targa);
    }
	

	public void rimuoviMezzoByTarga(String targa) {
		mezzoRepository.deleteByTarga(targa);
		
	}
}
