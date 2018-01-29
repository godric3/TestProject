@Stateless
public class UserManager {

	@EJB
	UserDAO userDAO;

	public User registerUser(String login, String password) throws EntityAlreadyExist {
		User user = null;
		try {
			user = userDAO.getByUsername(login);
			throw new EntityAlreadyExist();
		} catch (EntityNotFound e) {
			System.out.println(e.getMessage());
			user = new User();
			user.setUsername(login);
			user.setPassword(generateHash(password));
			user = userDAO.create(user);
		}
		return user;
	}

	public User loginUser(String login, String password) throws WrongPassword, EntityNotFound {
		User user = null;
		user = userDAO.getByUsername(login);
		if (generateHash(password).equals(user.getPassword())) {
			user.setToken(login + "|" + password);
		} else {
			throw new WrongPassword();
		}
		return user;
	}

}