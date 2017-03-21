package ba.unsa.etf;

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

	//bi-directional many-to-many association to Korisnici
	@ManyToMany
	@JoinTable(
		name="pacijenti_korisnici"
		, joinColumns={
			@JoinColumn(name="id_pacijenta")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_korisnika")
			}
		)
	private List<Korisnici> korisnicis;

	//bi-directional many-to-one association to PacijentiKorisnici
	@OneToMany(mappedBy="pacijenti")
	private List<PacijentiKorisnici> pacijentiKorisnicis;

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

	public List<Korisnici> getKorisnicis() {
		return this.korisnicis;
	}

	public void setKorisnicis(List<Korisnici> korisnicis) {
		this.korisnicis = korisnicis;
	}

	public List<PacijentiKorisnici> getPacijentiKorisnicis() {
		return this.pacijentiKorisnicis;
	}

	public void setPacijentiKorisnicis(List<PacijentiKorisnici> pacijentiKorisnicis) {
		this.pacijentiKorisnicis = pacijentiKorisnicis;
	}

	public PacijentiKorisnici addPacijentiKorisnici(PacijentiKorisnici pacijentiKorisnici) {
		getPacijentiKorisnicis().add(pacijentiKorisnici);
		pacijentiKorisnici.setPacijenti(this);

		return pacijentiKorisnici;
	}

	public PacijentiKorisnici removePacijentiKorisnici(PacijentiKorisnici pacijentiKorisnici) {
		getPacijentiKorisnicis().remove(pacijentiKorisnici);
		pacijentiKorisnici.setPacijenti(null);

		return pacijentiKorisnici;
	}

}