package it.stefano.turno.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.stefano.turno.entitys.Turno;

public interface TurnoRepository  extends CrudRepository<Turno, Long> {

	
	
	
	@Query(
            value = "select * from post where autore_id = ?1",
            nativeQuery = true)
	List<Turno> findByMezzo(String targa);


}
