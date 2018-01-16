package tu.kielce.pl.photoGallery.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TagImage {
	@EmbeddedId
	private TagImageId id;

	@ManyToOne
	@JoinColumn(name = "imageId")
	private Image image;

	@ManyToOne
	@JoinColumn(name = "tagName")
	private Tag tag;

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

}
