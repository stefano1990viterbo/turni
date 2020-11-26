package it.stefano.turno.entitys;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Mezzo {

	private String codice;
	@Id
	private String targa;
	private String enteAppartenenza;
	@OneToMany
	private List<Riferimento> riferimenti;
	private TipoMezzo tipoMezzo;
	
	
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public String getTarga() {
		return targa;
	}
	public void setTarga(String targa) {
		this.targa = targa;
	}
	public String getEnteAppartenenza() {
		return enteAppartenenza;
	}
	public void setEnteAppartenenza(String enteAppartenenza) {
		this.enteAppartenenza = enteAppartenenza;
	}
	
	public List<Riferimento> getRiferimenti() {
		return riferimenti;
	}
	public void setRiferimenti(List<Riferimento> riferimenti) {
		this.riferimenti = riferimenti;
	}
	public TipoMezzo getTipoMezzo() {
		return tipoMezzo;
	}
	public void setTipoMezzo(TipoMezzo tipoMezzo) {
		this.tipoMezzo = tipoMezzo;
	}
	
	
	
}
