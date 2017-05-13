package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the dijagnoze_pacijenti database table.
 * 
 */
@Entity
@Table(name="dijagnoze_pacijenti")
@NamedQuery(name="DijagnozePacijenti.findAll", query="SELECT d FROM DijagnozePacijenti d")
public class DijagnozePacijenti implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="datum_dijagnoze")
	private Date datumDijagnoze;

	//bi-directional many-to-one association to Dijagnoze
	@ManyToOne
	@JoinColumn(name="id_dijagnoze")
	private Dijagnoze dijagnoze;

	//bi-directional many-to-one association to Pacijenti
	@ManyToOne
	@JoinColumn(name="id_pacijenta")
	private Pacijenti pacijenti;

	public DijagnozePacijenti() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int idPacijenta, int idDijagnoze) {
		 String id;
		 id = Integer.toString(idPacijenta) + Integer.toString(idDijagnoze);
		 this.id = Integer.parseInt(id);
	}

	public Date getDatumDijagnoze() {
		return this.datumDijagnoze;
	}

	public void setDatumDijagnoze(Date datumDijagnoze) {
		this.datumDijagnoze = datumDijagnoze;
	}

	public Dijagnoze getDijagnoze() {
		return this.dijagnoze;
	}

	public void setDijagnoze(Dijagnoze dijagnoze) {
		this.dijagnoze = dijagnoze;
	}

	public Pacijenti getPacijenti() {
		return this.pacijenti;
	}

	public void setPacijenti(Pacijenti pacijenti) {
		this.pacijenti = pacijenti;
	}

}