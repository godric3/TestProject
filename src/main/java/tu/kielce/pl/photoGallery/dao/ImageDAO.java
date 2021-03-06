package tu.kielce.pl.photoGallery.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import tu.kielce.pl.photoGallery.exception.EntityNotFound;
import tu.kielce.pl.photoGallery.model.Category;
import tu.kielce.pl.photoGallery.model.Image;
import tu.kielce.pl.photoGallery.model.Tag;
import tu.kielce.pl.photoGallery.model.User;

@Stateless
public class ImageDAO extends GenericDAO<Image> {

	@Override
	protected Class<?> getClassType() {
		return Image.class;
	}

	public Image getByName(String url, String extension) throws EntityNotFound {
		Query q = entityManager.createNamedQuery("Image.findByName", Image.class);
		q.setParameter("url", url);
		q.setParameter("extension", extension);
		Image image;
		try {
			image = (Image) q.getSingleResult();
		} catch (NoResultException e) {
			throw new EntityNotFound();
		}
		return image;
	}

	public List<String> getAllNames() {
		Query q = entityManager.createNamedQuery("Image.findAllNames", String.class);
		List<String> imageNames = q.getResultList();
		return imageNames;
	}

	public List<String> getBySize(int minSize, int maxSize) {
		Query q = entityManager.createNamedQuery("Image.findBySize", String.class);
		q.setParameter("minSize", minSize);
		q.setParameter("maxSize", maxSize);
		List<String> imageNames = q.getResultList();
		return imageNames;
	}

	public List<String> getByExtension(String extension) {
		Query q = entityManager.createNamedQuery("Image.findByExtension", String.class);
		q.setParameter("extension", extension);
		List<String> imageNames = q.getResultList();
		return imageNames;
	}

	public List<String> getByTag(Tag tag) {
		Query q = entityManager.createNamedQuery("Image.findByTag", String.class);
		q.setParameter("tag", tag);
		List<String> imageNames = q.getResultList();
		return imageNames;
	}

	public List<String> getByMultipleTags(List<Tag> tags) {
		Query q = entityManager.createNamedQuery("Image.findByMultipleTags", String.class);
		q.setParameter("tags", tags);
		List<String> imageNames = q.getResultList();
		return imageNames;
	}

	public List<String> getByUser(User user) {
		Query q = entityManager.createNamedQuery("Image.findByUser", String.class);
		q.setParameter("user", user);
		List<String> imageNames = q.getResultList();
		return imageNames;
	}

	public List<String> getByCategory(Category category) {
		Query q = entityManager.createNamedQuery("Image.findByCategory", String.class);
		q.setParameter("category", category);
		List<String> imageNames = q.getResultList();
		return imageNames;
	}

}