package tu.kielce.pl.photoGallery.manager;

import javax.ejb.Stateless;
import javax.ws.rs.NotFoundException;

import tu.kielce.pl.photoGallery.model.User;

@Stateless
public class UserManager {

	public User registerUser(String login, String password) {
		// TODO Auto-generated method stub
		User user=new User();
		user.setUsername(login);
		user.setPassword(password);
		return user;
	}

	public User loginUser(String login, String password) throws NotFoundException{
		// TODO Auto-generated method stub
		User user=new User();
		user.setUsername(login);
		user.setPassword(password);
		user.setToken(login+"|"+password);
		return user;
	}

}
