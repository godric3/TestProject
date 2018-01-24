package tu.kielce.pl.photoGallery.manager;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import tu.kielce.pl.photoGallery.dao.UserDAO;
import tu.kielce.pl.photoGallery.exception.EntityAlreadyExist;
import tu.kielce.pl.photoGallery.exception.EntityNotFound;
import tu.kielce.pl.photoGallery.exception.WrongPassword;
import tu.kielce.pl.photoGallery.model.User;

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
			user.setPassword(password);// TODO: HASH!
			user = userDAO.create(user);
		}
		return user;
	}

	public User loginUser(String login, String password) throws WrongPassword, EntityNotFound {
		User user = null;
		user = userDAO.getByUsername(login);
		if (password.equals(user.getPassword())) {// TODO: HASH!
			user.setToken(login + "|" + password);
		} else {
			throw new WrongPassword();// wrong password
		}
		return user;
	}

}
