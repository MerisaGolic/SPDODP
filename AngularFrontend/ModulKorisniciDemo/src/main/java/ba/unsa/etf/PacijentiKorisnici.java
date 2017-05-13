package ba.unsa.etf;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the pacijenti_korisnici database table.
 * 
 */
@Entity
@Table(name="pacijenti_korisnici")
@NamedQuery(name="PacijentiKorisnici.findAll", query="SELECT p FROM PacijentiKorisnici p")
public class PacijentiKorisnici implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Korisnici
	@ManyToOne
	@JoinColumn(name="id_korisnika")
	private Korisnici korisnici;

	//bi-directional many-to-one association to Pacijenti
	@ManyToOne
	@JoinColumn(name="id_pacijenta")
	private Pacijenti pacijenti;

	public PacijentiKorisnici() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int idKorisnika, int idPacijenta) {
		String komb;
		komb = Integer.toString(idKorisnika) + Integer.toString(idPacijenta);
		this.id = Integer.parseInt(komb);
	}

	public Korisnici getKorisnici() {
		return this.korisnici;
	}

	public void setKorisnici(Korisnici korisnici) {
		this.korisnici = korisnici;
	}

	public Pacijenti getPacijenti() {
		return this.pacijenti;
	}

	public void setPacijenti(Pacijenti pacijenti) {
		this.pacijenti = pacijenti;
	}

}