package com.accountregistration.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.accountregistration.Entity.Admin;
import com.accountregistration.Respository.AdminRepository;

@Service
public class AdminService {

	private final AdminRepository repository;
	
	public AdminService(AdminRepository repository) {
		this.repository  = repository;
	}
	
	public Admin getAdmin(String name) {
		Admin admin = repository.findAdminByName(name)
				.orElse(null);
		if(admin != null) {
			return admin;
		}
		else {
			return null;
		}
	}
	
	public List<Admin> getAdmins(){
		return repository.findAll();
	}
	
	public boolean adminPresent(String name) {
		Optional<Admin> adminOptional = repository.findAdminByName(name);
		if(adminOptional.isPresent()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void postAdmin(Admin admin) {

		Optional<Admin> adminOptional = repository.findAdminByName(admin.getName());
		if(!adminOptional.isPresent()) {
			repository.save(admin);
		}
		else {
			System.out.println("The name is already taken");
		}
	}
}
