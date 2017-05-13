package ba.unsa.etf;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the korisnici database table.
 * 
 */
@Entity
@NamedQuery(name="Korisnici.findAll", query="SELECT k FROM Korisnici k")
public class Korisnici implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String password;

	private String username;

	//bi-directional many-to-many association to Pacijenti
	@ManyToMany(mappedBy="korisnicis")
	private List<Pacijenti> pacijentis;

	//bi-directional many-to-one association to PacijentiKorisnici
	@OneToMany(mappedBy="korisnici")
	private List<PacijentiKorisnici> pacijentiKorisnicis;

	public Korisnici() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Pacijenti> getPacijentis() {
		return this.pacijentis;
	}

	public void setPacijentis(List<Pacijenti> pacijentis) {
		this.pacijentis = pacijentis;
	}

	public List<PacijentiKorisnici> getPacijentiKorisnicis() {
		return this.pacijentiKorisnicis;
	}

	public void setPacijentiKorisnicis(List<PacijentiKorisnici> pacijentiKorisnicis) {
		this.pacijentiKorisnicis = pacijentiKorisnicis;
	}

	public PacijentiKorisnici addPacijentiKorisnici(PacijentiKorisnici pacijentiKorisnici) {
		getPacijentiKorisnicis().add(pacijentiKorisnici);
		pacijentiKorisnici.setKorisnici(this);

		return pacijentiKorisnici;
	}

	public PacijentiKorisnici removePacijentiKorisnici(PacijentiKorisnici pacijentiKorisnici) {
		getPacijentiKorisnicis().remove(pacijentiKorisnici);
		pacijentiKorisnici.setKorisnici(null);

		return pacijentiKorisnici;
	}

}