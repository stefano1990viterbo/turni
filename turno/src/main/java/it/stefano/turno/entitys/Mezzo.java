package it.stefano.turno.entitys;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Mezzo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String codice;
	private String targa;
	private String enteAppartenenza;
	@OneToMany(cascade = { CascadeType.ALL })
	@JoinColumn(name = "TARGA")
	private List<Riferimento> riferimenti;
	@Enumerated(EnumType.STRING)
	private TipoMezzo tipoMezzo;
	@CreationTimestamp
	private LocalDateTime dtInsertimento;
	@UpdateTimestamp
	private LocalDateTime dtUpdate;

	public Mezzo() {

	}

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
