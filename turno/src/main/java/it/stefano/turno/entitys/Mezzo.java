package it.stefano.turno.entitys;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Mezzo {

	private String codice;
	@Id
	private String targa;
	private String enteAppartenenza;
	@OneToMany(cascade = { CascadeType.ALL })
	@JoinColumn(name = "TARGA")
	private List<Riferimento> riferimenti;
	private TipoMezzo tipoMezzo;
	@CreationTimestamp
	private LocalDateTime dtInsertimento;
	@UpdateTimestamp
	private LocalDateTime dtUpdate;

	public Mezzo() {

	}

}
