package br.com.algarmidia.controllers;

import java.util.List;

import br.com.algarmidia.models.User;
import br.com.algarmidia.repositories.UserRepository;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;

@Resource
public class UserController {

	private final Result result;
	private final UserRepository repository;
	private final Validator validator;

	UserController(Result result, UserRepository repository, Validator validator) {
		this.result = result;
		this.repository = repository;
		this.validator = validator;
	}

	@Get("/usuarios")
	public List<User> index() {
		return repository.findAll();
	}

	@Post("/usuarios")
	public void create(User usuario) {
		if (usuario.getName() == null || usuario.getName().equals("")) {
			validator.add(new ValidationMessage("name.notNull", "error"));
		}
		validator.onErrorUsePageOf(this).newUsuario();
		repository.create(usuario);
		result.redirectTo(this).index();
	}
	
	@Get("/usuarios/new")
	public User newUsuario() {
		return new User();
	}
	
	@Put("/usuarios")
	public void update(User usuario) {
		validator.validate(usuario);
		validator.onErrorUsePageOf(this).edit(usuario);
		repository.update(usuario);
		result.redirectTo(this).index();
	}
	
	@Get("/usuarios/{usuario.id}/edit")
	public User edit(User usuario) {
		return repository.find(usuario.getId());
	}

	@Get("/usuarios/{usuario.id}")
	public User show(User usuario) {
		return repository.find(usuario.getId());
	}

	@Delete("/usuarios/{usuario.id}")
	public void destroy(User usuario) {
		repository.destroy(repository.find(usuario.getId()));
		result.redirectTo(this).index();  
	}
}