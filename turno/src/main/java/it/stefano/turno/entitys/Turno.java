package it.stefano.turno.entitys;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Turno {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Mezzo mezzo;
	@ManyToMany
	private List<Dipendente> equipaggio;
	@Enumerated(EnumType.STRING)
	private TipoTurno tipoTurno;
	private String postazione;
	private LocalDateTime istanteInizio;
	private LocalDateTime istanteFine;
	private LocalDateTime istanteFineEffettivo;
	@CreationTimestamp
	private LocalDateTime dtInsertimento;
	@UpdateTimestamp
	private LocalDateTime dtUpdate;

	public Turno() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Mezzo getMezzo() {
		return mezzo;
	}

	public void setMezzo(Mezzo mezzo) {
		this.mezzo = mezzo;
	}

	public List<Dipendente> getEquipaggio() {
		return equipaggio;
	}

	public void setEquipaggio(List<Dipendente> equipaggio) {
		this.equipaggio = equipaggio;
	}

	public TipoTurno getTipoTurno() {
		return tipoTurno;
	}

	public void setTipoTurno(TipoTurno tipoTurno) {
		this.tipoTurno = tipoTurno;
	}

	public String getPostazione() {
		return postazione;
	}

	public void setPostazione(String postazione) {
		this.postazione = postazione;
	}

	public LocalDateTime getIstanteInizio() {
		return istanteInizio;
	}

	public void setIstanteInizio(LocalDateTime istanteInizio) {
		this.istanteInizio = istanteInizio;
	}

	public LocalDateTime getIstanteFine() {
		return istanteFine;
	}

	public void setIstanteFine(LocalDateTime istanteFine) {
		this.istanteFine = istanteFine;
	}

	public LocalDateTime getIstanteFineEffettivo() {
		return istanteFineEffettivo;
	}

	public void setIstanteFineEffettivo(LocalDateTime istanteFineEffettivo) {
		this.istanteFineEffettivo = istanteFineEffettivo;
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
