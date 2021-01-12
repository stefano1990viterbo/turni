package it.stefano.turno.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.stefano.turno.entitys.Turno;

public interface TurnoRepository extends CrudRepository<Turno, Long> {

	@Query(value = "select * from turno where mezzo_targa = ?1", nativeQuery = true)
	List<Turno> findByMezzo(String targa);

	@Query(value = "select * from turno_equipaggio where equipaggio_id = ?1", nativeQuery = true)
	List<Turno> findByDipendente(Long id);

}
