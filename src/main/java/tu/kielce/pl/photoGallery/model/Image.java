package tu.kielce.pl.photoGallery.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Image {
	@Id
	private long id;
	private String url;
	private String extension;
	private int size;
	private int width;
	private int height;
	@ManyToOne
	@JoinColumn(name = "categoryName")
	private Category category;
	private String description;
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	@OneToMany(mappedBy = "image")
	private List<TagImage> tagImages;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategoryName(Category category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUserId(User user) {
		this.user = user;
	}

	public List<TagImage> getTagImages() {
		return tagImages;
	}

	public void setTagImages(List<TagImage> tagImages) {
		this.tagImages = tagImages;
	}

}