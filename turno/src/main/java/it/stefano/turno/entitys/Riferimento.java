package it.stefano.turno.entitys;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Riferimento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
private String numeroTelefono;

public String getNumeroTelefono() {
	return numeroTelefono;
}

public void setNumeroTelefono(String numeroTelefono) {
	this.numeroTelefono = numeroTelefono;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}


}
