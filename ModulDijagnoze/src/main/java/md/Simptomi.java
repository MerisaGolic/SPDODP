package md;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the simptomi database table.
 * 
 */
@Entity
@NamedQuery(name="Simptomi.findAll", query="SELECT s FROM Simptomi s")
public class Simptomi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String naziv;

	//bi-directional many-to-one association to DijagnozeSimptomi
	@OneToMany(mappedBy="simptomi")
	private List<DijagnozeSimptomi> dijagnozeSimptomis;

	public Simptomi() {
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

	public List<DijagnozeSimptomi> getDijagnozeSimptomis() {
		return this.dijagnozeSimptomis;
	}

	public void setDijagnozeSimptomis(List<DijagnozeSimptomi> dijagnozeSimptomis) {
		this.dijagnozeSimptomis = dijagnozeSimptomis;
	}

	public DijagnozeSimptomi addDijagnozeSimptomi(DijagnozeSimptomi dijagnozeSimptomi) {
		getDijagnozeSimptomis().add(dijagnozeSimptomi);
		dijagnozeSimptomi.setSimptomi(this);

		return dijagnozeSimptomi;
	}

	public DijagnozeSimptomi removeDijagnozeSimptomi(DijagnozeSimptomi dijagnozeSimptomi) {
		getDijagnozeSimptomis().remove(dijagnozeSimptomi);
		dijagnozeSimptomi.setSimptomi(null);

		return dijagnozeSimptomi;
	}

}