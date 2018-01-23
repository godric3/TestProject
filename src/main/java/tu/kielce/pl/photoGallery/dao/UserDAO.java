package tu.kielce.pl.photoGallery.dao;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.ws.rs.NotFoundException;

import tu.kielce.pl.photoGallery.model.User;

@Stateless
public class UserDAO extends GenericDAO<User> {

	@Override
	protected Class<?> getClassType() {
		return User.class;
	}

	public User getByUsername(String username) throws NotFoundException {
		Query q = entityManager.createNamedQuery("User.findByUsername", User.class);
		q.setParameter("username", username);
		User user = (User) q.getSingleResult();
		if (user != null)
			return user;
		else
			throw new NotFoundException();
	}
}