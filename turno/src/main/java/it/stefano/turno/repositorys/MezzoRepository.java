package it.stefano.turno.repositorys;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.stefano.turno.entitys.Mezzo;

@Repository
public interface MezzoRepository extends CrudRepository<Mezzo, Long> {

//	@Query(value = "select t from turno t where t.targa = ?1", nativeQuery = true)
	Mezzo findByTarga(String targa);

	void deleteByTarga(String targa);

}
