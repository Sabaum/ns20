package br.com.algarmidia.repositories;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public abstract class Repository<T, I extends Serializable> {

	protected EntityManager entityManager;
	protected final Class<T> clazz;

	@SuppressWarnings("unchecked")
	protected Repository(){

		this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	protected Repository(EntityManager entityManager) {
		setEntityManager(entityManager);

		@SuppressWarnings("unchecked")
		Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

		this.clazz = clazz;
	}

	@PersistenceContext(unitName = "pu-netsabe")
	private void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void create(T entity) {
		entityManager.persist(entity);
	}

	public void update(T entity) {
		entityManager.merge(entity);
	}

	public void destroy(T entity) {
		entityManager.remove(entity);
	}

	public T find(I id) {
		return entityManager.find(clazz, id);
	}

	public List<T> findAll() {
		Query query = entityManager.createQuery("from " + clazz.getName());

		@SuppressWarnings("unchecked")
		List<T> resultList = query.getResultList();

		return resultList;
	}
}