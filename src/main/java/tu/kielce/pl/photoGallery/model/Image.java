package tu.kielce.pl.photoGallery.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({ @NamedQuery(name = "Image.findAll", query = "SELECT i FROM Image i"),
		@NamedQuery(name = "Image.findBySize", query = "SELECT concat(i.url,'.',i.extension) FROM Image i WHERE i.size >= :minSize AND i.size <= :maxSize"),
		@NamedQuery(name = "Image.findByExtension", query = "SELECT concat(i.url,'.',i.extension) FROM Image i WHERE i.extension = :extension"),
		@NamedQuery(name = "Image.findByTag", query = "SELECT concat(i.url,'.',i.extension) FROM Image i, TagImage ti WHERE ti.tag = :tag AND ti.image = i"),
		@NamedQuery(name = "Image.findByMultipleTags", query = "SELECT DISTINCT concat(i.url,'.',i.extension) FROM Image i, TagImage ti WHERE ti.tag IN :tags AND ti.image = i"),
		@NamedQuery(name = "Image.findByUser", query = "SELECT concat(i.url,'.',i.extension) FROM Image i WHERE i.user = :user"),
		@NamedQuery(name = "Image.findByCategory", query = "SELECT concat(i.url,'.',i.extension) FROM Image i WHERE i.category = :category"),
		@NamedQuery(name = "Image.findAllNames", query = "SELECT concat(i.url,'.',i.extension) FROM Image i"),
		@NamedQuery(name = "Image.findByName", query = "SELECT i FROM Image i WHERE i.url =:url AND i.extension = :extension") })
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
