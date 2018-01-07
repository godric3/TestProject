package tu.kielce.pl.photoGallery.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.NotFoundException;

import tu.kielce.pl.photoGallery.model.TestEntity;

/*
 * Example DAO class, must extend GenericDAO
 * GenericDAO already implement creating, updating, removing, finding by id, findingAll(in this case entity must have namedQuery findALl)
 * This class should only work with EntityManager, methods should be like getWithID/Login, not something like login/register
 * May catch SQLException
 */
@Stateless
public class TestDAO extends GenericDAO<TestEntity> {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	protected Class<?> getClassType() {
		return TestEntity.class;
	}

	public TestEntity getTestEntityWithName(String name) throws NotFoundException{
		Query q = entityManager.createNamedQuery("User.findByLogin", TestEntity.class);
		q.setParameter("name", name);
		// q.getSingleResult() ???
		List<TestEntity> resultList = q.getResultList();
		if ((resultList != null) && (resultList.size() > 0))
			return resultList.get(0);
		else
			throw new NotFoundException();

	}

}
