package tu.kielce.pl.photoGallery.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({ @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c"),
		@NamedQuery(name = "Category.findByName", query = "SELECT c FROM Category c WHERE c.name = :name"),
		@NamedQuery(name = "Category.findAllWithImageCount", query = "SELECT i.category, COUNT(i.category) FROM Image i GROUP BY i.category") })
public class Category {
	@Id
	private String name;
	@OneToMany(mappedBy = "category")
	private List<Image> images;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}
}
