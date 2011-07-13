package br.com.algarmidia.controllers;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.algarmidia.models.User;
import br.com.algarmidia.repositories.UserRepository;
import br.com.algarmidia.test.WithEntityManager;
import br.com.algarmidia.util.TestConstants;
import br.com.algarmidia.util.UserBuilder;
import br.com.caelum.vraptor.Validator;

public class UserControllerTest extends WithEntityManager {

	private static UserController usuarioController;

	@Autowired
	private static Validator validator;

	private static UserRepository usuarioRepository;
	
	@BeforeClass
	public static void setUp() {
		usuarioRepository = mock(UserRepository.class);
		usuarioController = new UserController(null, usuarioRepository, null);
	}

	@Test
	public void fakeTest() {
		assertNotNull("put something real.", new UserController(null, null, null));
 	}

	@Test
	public void createFakeUser() {
		usuarioController.create(newTestFakeUser());
	}

	private User newTestUser() {
		UserBuilder usuarioBuilder = new UserBuilder();
		return usuarioBuilder.withName(TestConstants.USER_NAME)
			.withPassword(TestConstants.USER_PASSWORD)
			.withEmail(TestConstants.USER_EMAIL)
			.create();
	}

	private User newTestFakeUser() {
		return new UserBuilder().create();
	}
	
}
