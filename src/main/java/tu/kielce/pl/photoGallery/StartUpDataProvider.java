package tu.kielce.pl.photoGallery;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import tu.kielce.pl.photoGallery.dao.TagDAO;
import tu.kielce.pl.photoGallery.exception.EntityAlreadyExist;
import tu.kielce.pl.photoGallery.manager.UserManager;
import tu.kielce.pl.photoGallery.model.Tag;

@Startup
@Singleton
public class StartUpDataProvider {

	@EJB
	TagDAO tagDAO;

	@EJB
	UserManager userManager;

	@PostConstruct
	public void init() {
		Tag tag = new Tag();
		tag.setName("dogs");
		tagDAO.create(tag);
		tag = new Tag();
		tag.setName("cats");
		tagDAO.create(tag);

		try {
			userManager.registerUser("admin", "admin");
		} catch (EntityAlreadyExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
