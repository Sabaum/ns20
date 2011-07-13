package br.com.algarmidia.repositories;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.algarmidia.models.User;
import br.com.algarmidia.util.TestConstants;
import br.com.algarmidia.util.UserBuilder;

@ContextConfiguration(locations = { "classpath*:netsabe-test-ctx.xml",
		"classpath*:netsabe-entity-manager-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager="transactionManager")
@Transactional(readOnly=true)
public class UserRepositoryImplTest {//extends WithEntityManager {

	@Autowired
	private UserRepository usuarioRepository;

    //@Before
    public void setUp() {
    	//usuarioRepository =  new UserRepositoryImpl(factory.createEntityManager());
        //createTransactionAndRollbackAfterTest();
    }

    @Test
    public void fakeTest() {
  		assertNotNull("put something real.", new UserRepositoryImpl(null));
  	}

    @Test
    @Rollback(true)
    public void createUserTest() {
    	User user = newTestUser();
    	usuarioRepository.create(user);
    	Assert.assertNotNull(user.getId());
    }

	@Test
	public void findUserByIdTest() {
		User user = usuarioRepository.find(TestConstants.USER_ID);
		Assert.assertNotNull(user);
		Assert.assertEquals(user.getName(), TestConstants.USER_NAME);
    }

	@Test
	public void findAllUsersTest() {
		List<User> users = usuarioRepository.findAll();
		org.springframework.util.Assert.notEmpty(users);
	}

	@Test
	@Rollback(true)
	public void destroyUserTest() {
		User user = usuarioRepository.find(TestConstants.USER_ID);
		usuarioRepository.destroy(user);
		Assert.assertNull(usuarioRepository.find(TestConstants.USER_ID));
	}

	private User newTestUser() {
		UserBuilder usuarioBuilder = new UserBuilder();
		return usuarioBuilder.withName(TestConstants.USER_NAME)
			.withPassword(TestConstants.USER_PASSWORD)
			.withEmail(TestConstants.USER_EMAIL)
			.create();
	}

}

