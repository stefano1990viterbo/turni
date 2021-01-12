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
public class Dipendente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cognome;
	private Mansione mansione;
	@CreationTimestamp
	private LocalDateTime dtInsertimento;
	@UpdateTimestamp
	private LocalDateTime dtUpdate;

	public Dipendente() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Mansione getMansione() {
		return mansione;
	}

	public void setMansione(Mansione mansione) {
		this.mansione = mansione;
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
