package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the dijagnoze_pacijenti database table.
 * 
 */
@Embeddable
public class DijagnozePacijentiPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_dijagnoze")
	private int idDijagnoze;

	@Column(name="id_pacijenta")
	private int idPacijenta;

	@Temporal(TemporalType.DATE)
	@Column(name="datum_dijagnoze")
	private java.util.Date datumDijagnoze;

	public DijagnozePacijentiPK() {
	}
	public int getIdDijagnoze() {
		return this.idDijagnoze;
	}
	public void setIdDijagnoze(int idDijagnoze) {
		this.idDijagnoze = idDijagnoze;
	}
	public int getIdPacijenta() {
		return this.idPacijenta;
	}
	public void setIdPacijenta(int idPacijenta) {
		this.idPacijenta = idPacijenta;
	}
	public java.util.Date getDatumDijagnoze() {
		return this.datumDijagnoze;
	}
	public void setDatumDijagnoze(java.util.Date datumDijagnoze) {
		this.datumDijagnoze = datumDijagnoze;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DijagnozePacijentiPK)) {
			return false;
		}
		DijagnozePacijentiPK castOther = (DijagnozePacijentiPK)other;
		return 
			(this.idDijagnoze == castOther.idDijagnoze)
			&& (this.idPacijenta == castOther.idPacijenta)
			&& this.datumDijagnoze.equals(castOther.datumDijagnoze);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idDijagnoze;
		hash = hash * prime + this.idPacijenta;
		hash = hash * prime + this.datumDijagnoze.hashCode();
		
		return hash;
	}
}