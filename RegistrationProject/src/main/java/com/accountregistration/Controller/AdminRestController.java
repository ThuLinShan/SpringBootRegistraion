package com.accountregistration.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accountregistration.Entity.Admin;
import com.accountregistration.Service.AdminService;

@RestController
public class AdminRestController {

	private final AdminService service;
	
	public AdminRestController(AdminService service) {
		this.service = service;
	}
	
	@GetMapping("/adminrest/get")
	public List<Admin> getAdmins(){
		return service.getAdmins();
	}
	
	
}
