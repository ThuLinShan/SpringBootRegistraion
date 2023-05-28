package com.accountregistration.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accountregistration.Entity.Admin;
import com.accountregistration.Entity.UserModel;
import com.accountregistration.Respository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	private final UserRepository repository;
	
	@Autowired
	public UserService(UserRepository repository) {
		this.repository = repository;
	}
	
	
	//-----------------services------------------------------------
	public UserModel getUser(String email) {
		Optional<UserModel> userOptional = repository.findByEmail(email);
		
		if(userOptional.isPresent()) {
			return userOptional.get();
		}
		else {
			System.out.println("User with email:"+email+" doesn't exist");
			return null;
		}
	}
	
	public List<UserModel> getUsers(){
		return repository.findAll();
	}
	
	public void postUser(UserModel user) {
		Optional<UserModel> userOptional = repository.findByEmail(user.getEmail());
		if(userOptional.isPresent()) {
			System.out.println("The email: "+user.getEmail()+" is already taken");
		}
		else {
			repository.save(user);
		}
	}
	
	public void deleteUser(Long id) {
		Optional<UserModel> userOptional = repository.findById(id);
		
		if(userOptional.isPresent()) {
			repository.deleteById(id);
			System.out.println("User account deleted successfully.");
		}
		else {
			System.out.println("The user with id: "+ id+" doesn't exist.");
		}
	}
	
	@Transactional
	public void updateUser(Long id, String firstName, String LastName, String email, String password) {

		UserModel user = repository.findById(id).orElse(null);
		if(user == null) {
			System.out.println("User with user id: "+ id +" doesn't exist");
		}
		else {
			if(firstName != null && firstName.length()>0) {
				user.setFirstName(firstName);
			}
			if(LastName != null && LastName.length()>0) {
				user.setLastName(LastName);
			}
			if(email != null && email.length()>0) {
				user.setEmail(email);
			}
			if(password != null && password.length()>0) {
				user.setPassword(password);
			}
			System.out.println("User: "+id+" updated successfully." );
		}
	}
	

	public boolean emailPresent(String email) {
		Optional<UserModel> user = repository.findByEmail(email);
		if(user.isPresent()) {
			return true;
		}
		else {
			return false;
		}
	}
}
