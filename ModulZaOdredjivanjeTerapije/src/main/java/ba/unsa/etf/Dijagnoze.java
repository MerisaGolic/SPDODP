package ba.unsa.etf;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the dijagnoze database table.
 * 
 */
@Entity
@NamedQuery(name="Dijagnoze.findAll", query="SELECT d FROM Dijagnoze d")
public class Dijagnoze implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int koristi;

	private String naziv;

	private String opis;

	private int postotak;

	//bi-directional many-to-one association to DijagnozeLijekovi
	@OneToMany(mappedBy="dijagnoze")
	private List<DijagnozeLijekovi> dijagnozeLijekovis;

	public Dijagnoze() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getKoristi() {
		return this.koristi;
	}

	public void setKoristi(int koristi) {
		this.koristi = koristi;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public int getPostotak() {
		return this.postotak;
	}

	public void setPostotak(int postotak) {
		this.postotak = postotak;
	}

	public List<DijagnozeLijekovi> getDijagnozeLijekovis() {
		return this.dijagnozeLijekovis;
	}

	public void setDijagnozeLijekovis(List<DijagnozeLijekovi> dijagnozeLijekovis) {
		this.dijagnozeLijekovis = dijagnozeLijekovis;
	}

	public DijagnozeLijekovi addDijagnozeLijekovi(DijagnozeLijekovi dijagnozeLijekovi) {
		getDijagnozeLijekovis().add(dijagnozeLijekovi);
		dijagnozeLijekovi.setDijagnoze(this);

		return dijagnozeLijekovi;
	}

	public DijagnozeLijekovi removeDijagnozeLijekovi(DijagnozeLijekovi dijagnozeLijekovi) {
		getDijagnozeLijekovis().remove(dijagnozeLijekovi);
		dijagnozeLijekovi.setDijagnoze(null);

		return dijagnozeLijekovi;
	}

}