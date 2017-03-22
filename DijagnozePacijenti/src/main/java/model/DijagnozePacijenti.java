package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dijagnoze_pacijenti database table.
 * 
 */
@Entity
@Table(name="dijagnoze_pacijenti")
@NamedQuery(name="DijagnozePacijenti.findAll", query="SELECT d FROM DijagnozePacijenti d")
public class DijagnozePacijenti implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DijagnozePacijentiPK id;

	//bi-directional many-to-one association to Dijagnoze
	@ManyToOne
	@JoinColumn(name="id_dijagnoze", insertable=false, updatable=false)
	private Dijagnoze dijagnoze;

	//bi-directional many-to-one association to Pacijenti
	@ManyToOne
	@JoinColumn(name="id_pacijenta", insertable=false, updatable=false)
	private Pacijenti pacijenti;

	public DijagnozePacijenti() {
	}

	public DijagnozePacijentiPK getId() {
		return this.id;
	}

	public void setId(DijagnozePacijentiPK id) {
		this.id = id;
	}

	public Dijagnoze getDijagnoze() {
		return this.dijagnoze;
	}

	public void setDijagnoze(Dijagnoze dijagnoze) {
		this.dijagnoze = dijagnoze;
	}

	public Pacijenti getPacijenti() {
		return this.pacijenti;
	}

	public void setPacijenti(Pacijenti pacijenti) {
		this.pacijenti = pacijenti;
	}

}