package tu.kielce.pl.photoGallery.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import tu.kielce.pl.photoGallery.exception.EntityNotFound;
import tu.kielce.pl.photoGallery.model.Category;

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

	public Category getByNameOrCreate(String categoryName) {
		Category category;
		try {
			category = getByName(categoryName);
		} catch (EntityNotFound e) {
			Category cat = new Category();
			cat.setName(categoryName);
			category = create(cat);
		}
		return category;
	}

	public List<Object[]> getAllWithImageCount() throws EntityNotFound {
		Query q = entityManager.createNamedQuery("Category.findAllWithImageCount", Category.class);
		List<Object[]> categoriesWithImageCount = null;
		try {
			categoriesWithImageCount = q.getResultList();
		} catch (NoResultException e) {
			throw new EntityNotFound();
		}
		return categoriesWithImageCount;
	}

}
