package ba.unsa.etf;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the pacijenti_korisnici database table.
 * 
 */
@Embeddable
public class PacijentiKorisniciPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_pacijenta", insertable=false, updatable=false)
	private int idPacijenta;

	@Column(name="id_korisnika", insertable=false, updatable=false)
	private int idKorisnika;

	public PacijentiKorisniciPK() {
	}
	public int getIdPacijenta() {
		return this.idPacijenta;
	}
	public void setIdPacijenta(int idPacijenta) {
		this.idPacijenta = idPacijenta;
	}
	public int getIdKorisnika() {
		return this.idKorisnika;
	}
	public void setIdKorisnika(int idKorisnika) {
		this.idKorisnika = idKorisnika;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PacijentiKorisniciPK)) {
			return false;
		}
		PacijentiKorisniciPK castOther = (PacijentiKorisniciPK)other;
		return 
			(this.idPacijenta == castOther.idPacijenta)
			&& (this.idKorisnika == castOther.idKorisnika);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPacijenta;
		hash = hash * prime + this.idKorisnika;
		
		return hash;
	}
}