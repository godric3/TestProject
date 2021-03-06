package tu.kielce.pl.photoGallery.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
		@NamedQuery(name = "Image.findTagsWithImageCount", query = "SELECT ti.tag, COUNT(ti.tag) FROM TagImage ti GROUP BY ti.tag") })
public class TagImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "imageId")
	private Image image;

	@ManyToOne
	@JoinColumn(name = "tagName")
	private Tag tag;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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
