package ba.unsa.etf;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the lijekovi database table.
 * 
 */
@Entity
@NamedQuery(name="Lijekovi.findAll", query="SELECT l FROM Lijekovi l")
public class Lijekovi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String naziv;

	@Column(name="standardna_doza")
	private int standardnaDoza;

	//bi-directional many-to-one association to DijagnozeLijekovi
	@OneToMany(mappedBy="lijekovi")
	private List<DijagnozeLijekovi> dijagnozeLijekovis;

	public Lijekovi() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public int getStandardnaDoza() {
		return this.standardnaDoza;
	}

	public void setStandardnaDoza(int standardnaDoza) {
		this.standardnaDoza = standardnaDoza;
	}

	public List<DijagnozeLijekovi> getDijagnozeLijekovis() {
		return this.dijagnozeLijekovis;
	}

	public void setDijagnozeLijekovis(List<DijagnozeLijekovi> dijagnozeLijekovis) {
		this.dijagnozeLijekovis = dijagnozeLijekovis;
	}

	public DijagnozeLijekovi addDijagnozeLijekovi(DijagnozeLijekovi dijagnozeLijekovi) {
		getDijagnozeLijekovis().add(dijagnozeLijekovi);
		dijagnozeLijekovi.setLijekovi(this);

		return dijagnozeLijekovi;
	}

	public DijagnozeLijekovi removeDijagnozeLijekovi(DijagnozeLijekovi dijagnozeLijekovi) {
		getDijagnozeLijekovis().remove(dijagnozeLijekovi);
		dijagnozeLijekovi.setLijekovi(null);

		return dijagnozeLijekovi;
	}

}