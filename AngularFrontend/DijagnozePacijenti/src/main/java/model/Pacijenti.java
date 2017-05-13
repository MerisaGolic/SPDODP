package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the pacijenti database table.
 * 
 */
@Entity
@NamedQuery(name="Pacijenti.findAll", query="SELECT p FROM Pacijenti p")
public class Pacijenti implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="datum_rodjenja")
	private Date datumRodjenja;

	@Column(name="ime_prezime")
	private String imePrezime;

	private String spol;

	//bi-directional many-to-one association to DijagnozePacijenti
	@OneToMany(mappedBy="pacijenti")
	private List<DijagnozePacijenti> dijagnozePacijentis;

	public Pacijenti() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatumRodjenja() {
		return this.datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public String getImePrezime() {
		return this.imePrezime;
	}

	public void setImePrezime(String imePrezime) {
		this.imePrezime = imePrezime;
	}

	public String getSpol() {
		return this.spol;
	}

	public void setSpol(String spol) {
		this.spol = spol;
	}

	public List<DijagnozePacijenti> getDijagnozePacijentis() {
		return this.dijagnozePacijentis;
	}

	public void setDijagnozePacijentis(List<DijagnozePacijenti> dijagnozePacijentis) {
		this.dijagnozePacijentis = dijagnozePacijentis;
	}

	public DijagnozePacijenti addDijagnozePacijenti(DijagnozePacijenti dijagnozePacijenti) {
		getDijagnozePacijentis().add(dijagnozePacijenti);
		dijagnozePacijenti.setPacijenti(this);

		return dijagnozePacijenti;
	}

	public DijagnozePacijenti removeDijagnozePacijenti(DijagnozePacijenti dijagnozePacijenti) {
		getDijagnozePacijentis().remove(dijagnozePacijenti);
		dijagnozePacijenti.setPacijenti(null);

		return dijagnozePacijenti;
	}

}