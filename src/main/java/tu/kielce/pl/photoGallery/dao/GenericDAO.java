package tu.kielce.pl.photoGallery.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.NotFoundException;


public abstract class GenericDAO<T> {
	@PersistenceContext
	protected EntityManager entityManager;
	
	public T create(T entity){
		entityManager.persist(entity);
		return entity;
	}

	public void remove(T entity) {
		entityManager.remove(entity);
	}

	public void update(T entity) {
		entityManager.merge(entity);
	}

	public T find(int entityId) throws NotFoundException{ //TODO: maybe change to our own exception if more will be needed
		T tmp=(T) entityManager.find(getClassType(), entityId);
		if(tmp==null){
			throw new NotFoundException();
		}
		return tmp; 
	}
	public List<T> getAll(){
		Query q = entityManager.createNamedQuery(getClassType().getSimpleName()+".findAll", getClassType());
		List<T> resultList = q.getResultList();
		return resultList;
	}
	protected abstract Class<?> getClassType();
}
