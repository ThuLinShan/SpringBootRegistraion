package com.accountregistration.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accountregistration.Entity.UserModel;
import com.accountregistration.Service.UserService;


//for testing purpose in developing process
@RestController
@RequestMapping("restuser")
public class UserRestController {

	private final UserService service;
	
	@Autowired
	public UserRestController(UserService service) {
		this.service = service;
	}
	
	@GetMapping(path = "{email}")
	public UserModel getUser(@PathVariable("email") String email) {
		return service.getUser(email);
	}
	
	@GetMapping("all")
	public List<UserModel> getUsers(){
		System.out.println(service.getUsers());
		return service.getUsers();
	}
	
	@PostMapping
	public void postUser(@RequestBody UserModel user) {
		service.postUser(user);
	}
	
	@GetMapping(path = "/admin/delete/{id}")
	public String deleteUser(@PathVariable("id") Long id) {
		service.deleteUser(id);
		return "Account deleted successfully.";
	}
	
	@PutMapping(path = "{id}")
	public void updateUser(@PathVariable("id") Long id,
							@RequestParam (required = false) String firstName,
							@RequestParam (required = false) String lastName,
							@RequestParam (required = false) String email,
							@RequestParam (required = false) String password) 
	{
		service.updateUser(id, firstName, lastName, email, password);
	}
}
