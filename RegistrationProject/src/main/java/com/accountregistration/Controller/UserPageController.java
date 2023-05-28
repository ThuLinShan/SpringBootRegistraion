package com.accountregistration.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.accountregistration.Entity.UserModel;
import com.accountregistration.Service.UserService;

@Controller
public class UserPageController {
	
	private final UserService service;
	@Autowired
	public UserPageController(UserService service) {
		this.service = service;
	}

	@RequestMapping("/userlogin")
	public String userLogin() {
		return "userlogin";
	}
	
	@RequestMapping("/userloginpost")
	public String userLoginPost(UserModel user, Model model) {
		UserModel user2= service.getUser(user.getEmail());
		if(user2 != null) {
			System.out.println(user.getPassword());
			System.out.println(user2.getPassword());
			
			if(user.getPassword().equals(user2.getPassword())) {
				System.out.println("login successful");
				model.addAttribute("message", "Hi, "+user2.getFirstName()+" "+user2.getLastName());
				return "user";
			}
			else {
				System.out.println("Account exist. password wrong");
				model.addAttribute("message", "Wrong password");
				return "userlogin";
			}
		}
		else {
			System.out.println("Account doesn't exist");
			model.addAttribute("message", "Account with the email `"+user.getEmail()+" doesn't exist");
			return "userlogin";
		}
	}
	
	@RequestMapping("/userregister")
	public String userregister() {
		return "userregister";
	}
	
	@RequestMapping("/userregisterpost")
	public String userregisterpost(UserModel user, Model model) {
		if(service.emailPresent(user.getEmail())) {
			String errorString= "The email '"+user.getEmail()+"' is already taken";
			model.addAttribute("errormessage", errorString);
			
			System.out.println("The email is already taken");
			return "userregister";
		}
		else {
			service.postUser(user);
			System.out.println("account created successfully");
			return "userlogin";
		}
	}
}
