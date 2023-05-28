package com.accountregistration.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.accountregistration.Entity.Admin;
import com.accountregistration.Entity.UserModel;
import com.accountregistration.Service.AdminService;
import com.accountregistration.Service.UserService;

@Controller
public class AdminPageController {
	
	private final AdminService service;
	private final UserService userService;
	
	@Autowired
	public AdminPageController(AdminService service, UserService userService) {
		this.service = service;
		this.userService = userService;
	}

	
	@RequestMapping(value = {"/","/login","/home"})
	public String home() {
		return "login";
	}
	
	@RequestMapping("adminlogin")
	public String adminLogin() {
		return "adminlogin";
	}
	
	@RequestMapping("adminloginpost")
	public String adminLoginPost(Admin admin, Model model) {
		Admin admin2 = service.getAdmin(admin.getName());
		if(admin2 != null) {
			System.out.println(admin.getPassword());
			System.out.println(admin2.getPassword());
			
			if(admin.getPassword().equals(admin2.getPassword())) {
				System.out.println("login successful");
				List<UserModel> users = userService.getUsers();
				model.addAttribute("users", users);
				return "admin";
			}
			else {
				System.out.println("Account exist. password wrong");
				model.addAttribute("message", "Wrong password");
				return "adminlogin";
			}
		}
		else {
			System.out.println("Account doesn't exist");
			model.addAttribute("message", "Account with the name `"+admin.getName()+"` doesn't exist");
			return "adminlogin";
		}
	}
	
	@RequestMapping("adminregister")
	public String adminRegister() {
		return "adminregister";
	}
	
	@RequestMapping("adminregisterpost")
	public String adminRegisterPost(Admin admin, Model model) {
		if(service.adminPresent(admin.getName())) {
			String errorString= "The name '"+admin.getName()+"' is already taken";
			model.addAttribute("errormessage", errorString);
			
			System.out.println("The name is already taken");
			return "adminregister";
		}
		else {
			service.postAdmin(admin);
			System.out.println("account created successfully");
			return "adminlogin";
		}
	}
}
