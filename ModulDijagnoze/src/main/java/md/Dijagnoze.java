package md;

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
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String naziv;

	private String opis;

	private int postotak;

	//bi-directional many-to-one association to DijagnozeSimptomi
	@OneToMany(mappedBy="dijagnoze")
	private List<DijagnozeSimptomi> dijagnozeSimptomis;

	public Dijagnoze() {
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

	public List<DijagnozeSimptomi> getDijagnozeSimptomis() {
		return this.dijagnozeSimptomis;
	}

	public void setDijagnozeSimptomis(List<DijagnozeSimptomi> dijagnozeSimptomis) {
		this.dijagnozeSimptomis = dijagnozeSimptomis;
	}

	public DijagnozeSimptomi addDijagnozeSimptomi(DijagnozeSimptomi dijagnozeSimptomi) {
		getDijagnozeSimptomis().add(dijagnozeSimptomi);
		dijagnozeSimptomi.setDijagnoze(this);

		return dijagnozeSimptomi;
	}

	public DijagnozeSimptomi removeDijagnozeSimptomi(DijagnozeSimptomi dijagnozeSimptomi) {
		getDijagnozeSimptomis().remove(dijagnozeSimptomi);
		dijagnozeSimptomi.setDijagnoze(null);

		return dijagnozeSimptomi;
	}

}