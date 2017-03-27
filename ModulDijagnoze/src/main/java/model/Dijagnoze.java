package model;

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

	private String postotak;

	//bi-directional many-to-one association to DijagnozePacijenti
	@OneToMany(mappedBy="dijagnoze")
	private List<DijagnozePacijenti> dijagnozePacijentis;

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

	public String getPostotak() {
		return this.postotak;
	}

	public void setPostotak(String postotak) {
		this.postotak = postotak;
	}

	public List<DijagnozePacijenti> getDijagnozePacijentis() {
		return this.dijagnozePacijentis;
	}

	public void setDijagnozePacijentis(List<DijagnozePacijenti> dijagnozePacijentis) {
		this.dijagnozePacijentis = dijagnozePacijentis;
	}

	public DijagnozePacijenti addDijagnozePacijenti(DijagnozePacijenti dijagnozePacijenti) {
		getDijagnozePacijentis().add(dijagnozePacijenti);
		dijagnozePacijenti.setDijagnoze(this);

		return dijagnozePacijenti;
	}

	public DijagnozePacijenti removeDijagnozePacijenti(DijagnozePacijenti dijagnozePacijenti) {
		getDijagnozePacijentis().remove(dijagnozePacijenti);
		dijagnozePacijenti.setDijagnoze(null);

		return dijagnozePacijenti;
	}

}