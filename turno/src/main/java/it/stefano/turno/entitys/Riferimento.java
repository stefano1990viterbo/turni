package it.stefano.turno.entitys;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
public class Riferimento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String numeroTelefono;
	@CreationTimestamp
	private LocalDateTime dtInsertimento;
	@UpdateTimestamp
	private LocalDateTime dtUpdate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumeroTelefono() {
		return numeroTelefono;
	}
	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}
	public LocalDateTime getDtInsertimento() {
		return dtInsertimento;
	}
	public void setDtInsertimento(LocalDateTime dtInsertimento) {
		this.dtInsertimento = dtInsertimento;
	}
	public LocalDateTime getDtUpdate() {
		return dtUpdate;
	}
	public void setDtUpdate(LocalDateTime dtUpdate) {
		this.dtUpdate = dtUpdate;
	}

}
