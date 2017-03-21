package md;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the dijagnoze_simptomi database table.
 * 
 */
@Embeddable
public class DijagnozeSimptomiPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_dijagnoze")
	private int idDijagnoze;

	@Column(name="id_simptoma")
	private int idSimptoma;

	public DijagnozeSimptomiPK() {
	}
	public int getIdDijagnoze() {
		return this.idDijagnoze;
	}
	public void setIdDijagnoze(int idDijagnoze) {
		this.idDijagnoze = idDijagnoze;
	}
	public int getIdSimptoma() {
		return this.idSimptoma;
	}
	public void setIdSimptoma(int idSimptoma) {
		this.idSimptoma = idSimptoma;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DijagnozeSimptomiPK)) {
			return false;
		}
		DijagnozeSimptomiPK castOther = (DijagnozeSimptomiPK)other;
		return 
			(this.idDijagnoze == castOther.idDijagnoze)
			&& (this.idSimptoma == castOther.idSimptoma);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idDijagnoze;
		hash = hash * prime + this.idSimptoma;
		
		return hash;
	}
}