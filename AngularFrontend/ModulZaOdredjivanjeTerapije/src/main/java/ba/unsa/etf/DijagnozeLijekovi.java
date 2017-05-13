package ba.unsa.etf;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dijagnoze_lijekovi database table.
 * 
 */
@Entity
@Table(name="dijagnoze_lijekovi")
@NamedQuery(name="DijagnozeLijekovi.findAll", query="SELECT d FROM DijagnozeLijekovi d")
public class DijagnozeLijekovi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private double eritrociti;

	private double leukociti;

	private double secer;

	private double trombociti;

	//bi-directional many-to-one association to Dijagnoze
	@ManyToOne
	@JoinColumn(name="id_dijagnoze")
	private Dijagnoze dijagnoze;

	//bi-directional many-to-one association to Lijekovi
	@ManyToOne
	@JoinColumn(name="id_lijeka")
	private Lijekovi lijekovi;

	public DijagnozeLijekovi() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int idLijeka, int idDijagnoze) {
		String komb;
		komb = Integer.toString(idLijeka) + Integer.toString(idDijagnoze);
		this.id = Integer.parseInt(komb);
	}

	public double getEritrociti() {
		return this.eritrociti;
	}

	public void setEritrociti(double eritrociti) {
		this.eritrociti = eritrociti;
	}

	public double getLeukociti() {
		return this.leukociti;
	}

	public void setLeukociti(double leukociti) {
		this.leukociti = leukociti;
	}

	public double getSecer() {
		return this.secer;
	}

	public void setSecer(double secer) {
		this.secer = secer;
	}

	public double getTrombociti() {
		return this.trombociti;
	}

	public void setTrombociti(double trombociti) {
		this.trombociti = trombociti;
	}

	public Dijagnoze getDijagnoze() {
		return this.dijagnoze;
	}

	public void setDijagnoze(Dijagnoze dijagnoze) {
		this.dijagnoze = dijagnoze;
	}

	public Lijekovi getLijekovi() {
		return this.lijekovi;
	}

	public void setLijekovi(Lijekovi lijekovi) {
		this.lijekovi = lijekovi;
	}

}