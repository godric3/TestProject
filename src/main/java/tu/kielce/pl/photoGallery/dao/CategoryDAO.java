package tu.kielce.pl.photoGallery.dao;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.ws.rs.NotFoundException;

import tu.kielce.pl.photoGallery.model.Category;

@Stateless
public class CategoryDAO extends GenericDAO<Category> {

	@Override
	protected Class<?> getClassType() {
		return Category.class;
	}

	public Category getByName(String name) throws NotFoundException {
		Query q = entityManager.createNamedQuery("Category.findByName", Category.class);
		q.setParameter("name", name);
		Category category = (Category) q.getSingleResult();
		if (category != null)
			return category;
		else
			throw new NotFoundException();
	}

}
