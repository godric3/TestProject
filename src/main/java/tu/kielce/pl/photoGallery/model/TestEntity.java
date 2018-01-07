package tu.kielce.pl.photoGallery.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


/*
 * Example entity stored in database.
 * 
 * Example for joining tables:
 * 
 	class Checkpoint
 	@ManyToOne
	@JoinColumn(name="tripID")
	private Trip trip;
	
	class Trip
	@OneToMany(mappedBy="trip")
	private List<Checkpoint> checkpoints;
 */
@Entity
@NamedQueries({
	@NamedQuery(name="TestEntity.findAll", query="SELECT u FROM TestEntity u"),
	@NamedQuery(name="TestEntity.findByName", query="SELECT u FROM TestEntity u WHERE u.name = :name")
})
public class TestEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Lob
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
