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