package it.stefano.turno.repositorys;

import org.springframework.data.repository.CrudRepository;

import it.stefano.turno.entitys.Turno;

public interface TurnoRepository  extends CrudRepository<Turno, Long> {

}
