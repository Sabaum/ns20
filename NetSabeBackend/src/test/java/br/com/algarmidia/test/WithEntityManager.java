package br.com.algarmidia.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

public class WithEntityManager {

    protected static EntityManagerFactory factory;
    protected EntityManager entityManager;

    private EntityTransaction transaction;
    private boolean rollbackTransaction = false;

    @BeforeClass
    public static void createEntityManagerFactory(){
        factory = Persistence.createEntityManagerFactory("pu-netsabe");
    }

    @Before
    public void createEntityManager(){
        entityManager = factory.createEntityManager();
    }

    @After
    public void rollbackTransaction(){
        if (rollbackTransaction){
            transaction.rollback();
        }
        rollbackTransaction = false;
    }

    public void createTransactionAndRollbackAfterTest(){
        transaction = entityManager.getTransaction();
        transaction.begin();
        rollbackTransaction = true;
    }

}
