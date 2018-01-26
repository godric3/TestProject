package tu.kielce.pl.photoGallery.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import tu.kielce.pl.photoGallery.exception.EntityNotFound;
import tu.kielce.pl.photoGallery.model.Category;
import tu.kielce.pl.photoGallery.model.TagImage;

@Stateless
public class TagImageDAO extends GenericDAO<TagImage> {

	@Override
	protected Class<?> getClassType() {
		return TagImage.class;
	}

	public List<Object[]> getTagsWithImageCount() throws EntityNotFound {
		Query q = entityManager.createNamedQuery("TagImage.findTagsWithImageCount", Category.class);
		List<Object[]> tagsWithImageCount = null;
		try {
			tagsWithImageCount = q.getResultList();
		} catch (NoResultException e) {
			throw new EntityNotFound();
		}
		return tagsWithImageCount;
	}

}
