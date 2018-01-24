package tu.kielce.pl.photoGallery.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tu.kielce.pl.photoGallery.exception.EntityNotFound;


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

	public T find(int entityId) throws EntityNotFound{
		T tmp=(T) entityManager.find(getClassType(), entityId);
		if(tmp==null){
			throw new EntityNotFound();
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
