@Entity
@NamedQueries({ @NamedQuery(name = "Image.findAll", query = "SELECT i FROM Image i"),
		@NamedQuery(name = "Image.findBySize", query = "SELECT i FROM Image i WHERE i.size >= :minSize AND i.size <= :maxSize"),
		@NamedQuery(name = "Image.findByExtension", query = "SELECT i FROM Image i WHERE i.extension = :extension"),
		@NamedQuery(name = "Image.findByTag", query = "SELECT i FROM Image i, TagImage ti WHERE ti.tag = :tag AND ti.image = i"),
		@NamedQuery(name = "Image.findByUser", query = "SELECT i FROM Image i WHERE i.user = :user"),
		@NamedQuery(name = "Image.findByCategory", query = "SELECT i FROM Image i WHERE i.category = :category"),
		@NamedQuery(name = "Image.findAllNames", query = "SELECT concat(i.url,'.',i.extension) FROM Image i") })
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
	...
}