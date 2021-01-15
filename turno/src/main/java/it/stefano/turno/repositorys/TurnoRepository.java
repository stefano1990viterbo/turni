package it.stefano.turno.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.stefano.turno.entitys.Turno;

public interface TurnoRepository extends CrudRepository<Turno, Long> {

	@Query(value = "select * from turno turno where mezzo_id = ?1", nativeQuery = true)
	List<Turno> findByMezzo(Long idMezzo);

	@Query(value = "SELECT * FROM TURNO_EQUIPAGGIO  where equipaggio_id= ?1", nativeQuery = true)
	List<Long> findTurnoByDipendente(Long id);

}
