package md.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dijagnoze_simptomi database table.
 * 
 */
@Entity
@Table(name="dijagnoze_simptomi")
@NamedQuery(name="DijagnozeSimptomi.findAll", query="SELECT d FROM DijagnozeSimptomi d")
public class DijagnozeSimptomi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Dijagnoze
	@ManyToOne
	@JoinColumn(name="id_dijagnoze")
	private Dijagnoze dijagnoze;

	//bi-directional many-to-one association to Simptomi
	@ManyToOne
	@JoinColumn(name="id_simptoma")
	private Simptomi simptomi;

	public DijagnozeSimptomi() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id1, int id2) {
		this.id = Integer.parseInt(Integer.toString(id1) + Integer.toString(id2));
	}

	public Dijagnoze getDijagnoze() {
		return this.dijagnoze;
	}

	public void setDijagnoze(Dijagnoze dijagnoze) {
		this.dijagnoze = dijagnoze;
	}

	public Simptomi getSimptomi() {
		return this.simptomi;
	}

	public void setSimptomi(Simptomi simptomi) {
		this.simptomi = simptomi;
	}

}