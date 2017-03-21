package ba.unsa.etf;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dijagnoze_lijekovi database table.
 * 
 */
@Entity
@Table(name="dijagnoze_lijekovi")
@NamedQuery(name="DijagnozeLijekovi.findAll", query="SELECT d FROM DijagnozeLijekovi d")
public class DijagnozeLijekovi implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DijagnozeLijekoviPK id;

	//bi-directional many-to-one association to Lijekovi
	@ManyToOne
	@JoinColumn(name="id_lijeka", insertable=false, updatable=false)
	private Lijekovi lijekovi;

	//bi-directional many-to-one association to Dijagnoze
	@ManyToOne
	@JoinColumn(name="id_dijagnoze", insertable=false, updatable=false)
	private Dijagnoze dijagnoze;

	public DijagnozeLijekovi() {
	}

	public DijagnozeLijekoviPK getId() {
		return this.id;
	}

	public void setId(DijagnozeLijekoviPK id) {
		this.id = id;
	}

	public Lijekovi getLijekovi() {
		return this.lijekovi;
	}

	public void setLijekovi(Lijekovi lijekovi) {
		this.lijekovi = lijekovi;
	}

	public Dijagnoze getDijagnoze() {
		return this.dijagnoze;
	}

	public void setDijagnoze(Dijagnoze dijagnoze) {
		this.dijagnoze = dijagnoze;
	}

}