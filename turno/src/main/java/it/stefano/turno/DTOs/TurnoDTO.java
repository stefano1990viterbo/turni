package it.stefano.turno.DTOs;

import java.time.LocalDateTime;
import java.util.List;

import it.stefano.turno.entitys.TipoTurno;

public class TurnoDTO {
	private Long id;
	private String mezzo;
	private List<Long> equipaggio;
	private TipoTurno tipoTurno;
	private String postazione;
	private LocalDateTime istanteInizio;
	private LocalDateTime istanteFine;
	private LocalDateTime istanteFineEffettivo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMezzo() {
		return mezzo;
	}

	public void setMezzo(String mezzo) {
		this.mezzo = mezzo;
	}

	public List<Long> getEquipaggio() {
		return equipaggio;
	}

	public void setEquipaggio(List<Long> equipaggio) {
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

	public LocalDateTime getIstanteFineEffettivo() {
		return istanteFineEffettivo;
	}

	public void setIstanteFineEffettivo(LocalDateTime istanteFineEffettivo) {
		this.istanteFineEffettivo = istanteFineEffettivo;
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

}
