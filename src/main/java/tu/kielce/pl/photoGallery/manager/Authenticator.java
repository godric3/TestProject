package tu.kielce.pl.photoGallery.manager;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import tu.kielce.pl.photoGallery.dao.UserDAO;
import tu.kielce.pl.photoGallery.exception.EntityNotFound;
import tu.kielce.pl.photoGallery.model.User;

@Stateless
public class Authenticator {

	@EJB
	UserDAO userDAO;
	public boolean validateToken(String token) {
		if((token==null)||(token.isEmpty())){
			return false;
		}
		String []tmp=token.split("\\|");
		try{
			User user = userDAO.getByUsername(tmp[0]);
			if(token.equals(user.getToken())){
				return true;
			}
		}catch(EntityNotFound e){
			return false;
		}
		return false;
	}

}
