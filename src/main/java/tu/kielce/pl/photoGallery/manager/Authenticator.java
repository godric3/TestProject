package tu.kielce.pl.photoGallery.manager;

import java.security.Key;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import tu.kielce.pl.photoGallery.dao.UserDAO;
import tu.kielce.pl.photoGallery.exception.EntityNotFound;
import tu.kielce.pl.photoGallery.model.User;

@Stateless
public class Authenticator {

	@EJB
	UserDAO userDAO;
	static Key key =  (Key) MacProvider.generateKey();
	
	public boolean validateToken(String token) {
		if ((token == null) || (token.isEmpty())) {
			return false;
		}
		try{
			Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
		}catch (Exception e){
			return false;
		}
		return true;
	}
	
	public String getUser(String token) {
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
	}
	
	public void invalidateToken(String token) {
		String[] tmp = token.split("\\|");
		try {
			User user = userDAO.getByUsername(tmp[0]);
			user.setToken("");
			userDAO.update(user);
		} catch (EntityNotFound e) {

		}
	}

	public String generateToken(String login, String password) {
		String compactJws = Jwts.builder()
		  .setSubject(login)
		  .signWith(SignatureAlgorithm.HS512, key)
		  .compact();
		assert Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws).getBody().getSubject().equals(login);
		return compactJws;
	}

}
