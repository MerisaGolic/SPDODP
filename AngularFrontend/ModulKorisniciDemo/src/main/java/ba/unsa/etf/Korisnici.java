package ba.unsa.etf;

import java.io.Serializable;
import java.sql.Timestamp;

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
	
	@Column(name="promjena_passworda")
	private Timestamp promjenaPassworda;
	
	private String email;

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
	
	public Timestamp getPromjenaPassworda() {
		return this.promjenaPassworda;
	}
	
	public void setPromjenaPassworda(Timestamp promjena) {
		this.promjenaPassworda = promjena;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
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