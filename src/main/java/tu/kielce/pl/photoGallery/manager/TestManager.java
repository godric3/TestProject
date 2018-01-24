package tu.kielce.pl.photoGallery.manager;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import tu.kielce.pl.photoGallery.dao.TestDAO;
import tu.kielce.pl.photoGallery.model.TestEntity;

/*
 * Example manager class.
 * May use multiple DAOs. Should have most of logic. Methods like "registerUser" "addPhotoToGallery"
 * 
 */
@Stateless
public class TestManager {

	@EJB
	TestDAO testDAO;
	
//	public TestEntity getTestEntity(int id){
//		return testDAO.find(id);
//	}

	public void save(TestEntity testEntity) {
		testDAO.create(testEntity);
		
	}

//	public TestEntity find(int id) {
//		return testDAO.find(id);
//	}
}
