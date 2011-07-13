package br.com.algarmidia.repositories;

import javax.persistence.EntityManager;

import br.com.algarmidia.models.User;

@org.springframework.stereotype.Repository
public class UserRepositoryImpl extends Repository<User, Long> implements UserRepository {

	public UserRepositoryImpl(){}

	UserRepositoryImpl(EntityManager entityManager) {
		super(entityManager);
	}
}
