package tu.kielce.pl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TestEJB {
	@PersistenceContext
	private EntityManager entityManager;

	public void persist(TestEntity testEntity) {
		entityManager.persist(testEntity);
		
	}

	public TestEntity find(Class<TestEntity> class1, int id) {
		return entityManager.find(class1, id);
	}
}
