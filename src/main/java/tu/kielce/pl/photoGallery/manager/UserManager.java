package tu.kielce.pl.photoGallery.manager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
	
	// http://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
	private String generateHash(String password) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException();
		}
		messageDigest.update(password.getBytes());
		byte[] bytes = messageDigest.digest();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}

}
