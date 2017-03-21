package ba.unsa.etf;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the dijagnoze_lijekovi database table.
 * 
 */
@Embeddable
public class DijagnozeLijekoviPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_dijagnoze", insertable=false, updatable=false)
	private int idDijagnoze;

	@Column(name="id_lijeka", insertable=false, updatable=false)
	private int idLijeka;

	public DijagnozeLijekoviPK() {
	}
	public int getIdDijagnoze() {
		return this.idDijagnoze;
	}
	public void setIdDijagnoze(int idDijagnoze) {
		this.idDijagnoze = idDijagnoze;
	}
	public int getIdLijeka() {
		return this.idLijeka;
	}
	public void setIdLijeka(int idLijeka) {
		this.idLijeka = idLijeka;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DijagnozeLijekoviPK)) {
			return false;
		}
		DijagnozeLijekoviPK castOther = (DijagnozeLijekoviPK)other;
		return 
			(this.idDijagnoze == castOther.idDijagnoze)
			&& (this.idLijeka == castOther.idLijeka);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idDijagnoze;
		hash = hash * prime + this.idLijeka;
		
		return hash;
	}
}