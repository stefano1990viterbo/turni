package it.stefano.turno.DTOs;

import java.time.LocalDateTime;
import java.util.List;


import it.stefano.turno.entitys.Dipendente;
import it.stefano.turno.entitys.Mezzo;
import it.stefano.turno.entitys.TipoTurno;

public class TurnoDTO {
	private Long id;
	private Mezzo mezzo;
	private List<Dipendente> equipaggio;
	private TipoTurno tipoTurno;
	private String postazione;
	private LocalDateTime istanteFineEffettivo;
	
	
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
	public LocalDateTime getIstanteFineEffettivo() {
		return istanteFineEffettivo;
	}
	public void setIstanteFineEffettivo(LocalDateTime istanteFineEffettivo) {
		this.istanteFineEffettivo = istanteFineEffettivo;
	}
	
	
}
