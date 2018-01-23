package tu.kielce.pl.photoGallery.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.ws.rs.NotFoundException;

import tu.kielce.pl.photoGallery.model.Image;
import tu.kielce.pl.photoGallery.model.Tag;
import tu.kielce.pl.photoGallery.model.User;

@Stateless
public class ImageDAO extends GenericDAO<Image> {

	@Override
	protected Class<?> getClassType() {
		return Image.class;
	}

	public Image getByName(String name) throws NotFoundException {
		Query q = entityManager.createNamedQuery("Image.findByName", Image.class);
		q.setParameter("name", name);
		Image image = (Image) q.getSingleResult();
		if (image != null)
			return image;
		else
			throw new NotFoundException();
	}

	public List<Image> getBySize(int size) {
		Query q = entityManager.createNamedQuery("Image.findBySize", Image.class);
		q.setParameter("size", size);
		List<Image> images = q.getResultList();
		return images;
	}

	public List<Image> getByExtension(String extension) throws NotFoundException {
		Query q = entityManager.createNamedQuery("Image.findByExtension", Image.class);
		q.setParameter("extension", extension);
		List<Image> images = q.getResultList();
		return images;
	}

	public List<Image> getByTag(Tag tag) throws NotFoundException {
		Query q = entityManager.createNamedQuery("Image.findByTag", Image.class);
		q.setParameter("tag", tag);
		List<Image> images = q.getResultList();
		return images;
	}

	public List<Image> getByUser(User user) throws NotFoundException {
		Query q = entityManager.createNamedQuery("Image.findByUser", Image.class);
		q.setParameter("user", user);
		List<Image> images = q.getResultList();
		return images;
	}

}