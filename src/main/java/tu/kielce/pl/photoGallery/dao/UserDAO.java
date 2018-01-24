package tu.kielce.pl.photoGallery.dao;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import tu.kielce.pl.photoGallery.exception.EntityNotFound;
import tu.kielce.pl.photoGallery.model.User;

@Stateless
public class UserDAO extends GenericDAO<User> {

	@Override
	protected Class<?> getClassType() {
		return User.class;
	}

	public User getByUsername(String username) throws EntityNotFound {
		Query q = entityManager.createNamedQuery("User.findByUsername", User.class);
		q.setParameter("username", username);
		User user = null;
		try {
			user = (User) q.getSingleResult();
		} catch (NoResultException e) {
			throw new EntityNotFound();
		}
		return user;
	}
}