package it.stefano.turno.repositorys;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.stefano.turno.entitys.Dipendente;

@Repository
public interface DipendenteRepository extends CrudRepository<Dipendente, Long> {

//TO Do 
	// non so come cazzo fare
	// List<Turno> findByListaEquipaggio(List<Dipendente> equipaggio);
}
