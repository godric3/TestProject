package tu.kielce.pl.photoGallery.dao;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.ws.rs.NotFoundException;

import tu.kielce.pl.photoGallery.exception.EntityNotFound;
import tu.kielce.pl.photoGallery.model.Category;
import tu.kielce.pl.photoGallery.model.User;

@Stateless
public class CategoryDAO extends GenericDAO<Category> {

	@Override
	protected Class<?> getClassType() {
		return Category.class;
	}

	public Category getByName(String name) throws EntityNotFound {
		Query q = entityManager.createNamedQuery("Category.findByName", Category.class);
		q.setParameter("name", name);
		Category category = null;
		try {
			category = (Category) q.getSingleResult();
		} catch (NoResultException e) {
			throw new EntityNotFound();
		}
		return category;
	}

}
