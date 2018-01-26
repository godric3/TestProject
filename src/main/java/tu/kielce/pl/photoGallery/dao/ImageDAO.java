package tu.kielce.pl.photoGallery.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.ws.rs.NotFoundException;

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
		List<String> images = q.getResultList();
		return images;
	}

	public List<Image> getBySize(int minSize, int maxSize) {
		Query q = entityManager.createNamedQuery("Image.findBySize", Image.class);
		q.setParameter("minSize", minSize);
		q.setParameter("maxSize", maxSize);
		List<Image> images = q.getResultList();
		return images;
	}

	public List<Image> getByExtension(String extension) throws EntityNotFound {
		Query q = entityManager.createNamedQuery("Image.findByExtension", Image.class);
		q.setParameter("extension", extension);
		List<Image> images = q.getResultList();
		return images;
	}

	public List<Image> getByTag(Tag tag) throws EntityNotFound {
		Query q = entityManager.createNamedQuery("Image.findByTag", Image.class);
		q.setParameter("tag", tag);
		List<Image> images = q.getResultList();
		return images;
	}

	public List<Image> getByUser(User user) throws EntityNotFound {
		Query q = entityManager.createNamedQuery("Image.findByUser", Image.class);
		q.setParameter("user", user);
		List<Image> images = q.getResultList();
		return images;
	}

	public List<Image> getByCategory(Category category) throws EntityNotFound {
		Query q = entityManager.createNamedQuery("Image.findByCategory", Image.class);
		q.setParameter("category", category);
		List<Image> images = q.getResultList();
		return images;
	}

}