package tu.kielce.pl.photoGallery.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Tag {
	@Id
	private String name;
	@OneToMany(mappedBy = "tag")
	private List<TagImage> tagImages;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TagImage> getTagImages() {
		return tagImages;
	}

	public void setTagImages(List<TagImage> tagImages) {
		this.tagImages = tagImages;
	}
}
