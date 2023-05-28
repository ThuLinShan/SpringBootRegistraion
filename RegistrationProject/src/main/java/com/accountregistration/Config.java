package com.accountregistration;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.accountregistration.Entity.Admin;
import com.accountregistration.Entity.UserModel;
import com.accountregistration.Respository.AdminRepository;
import com.accountregistration.Respository.UserRepository;

@Configuration
public class Config {

	@Bean
	CommandLineRunner runner(AdminRepository adminRepository, UserRepository userRepository) {
		return args->{
			Admin admin1 = new Admin(
					"Admin",
					"Admin123"
					);
			adminRepository.saveAll(List.of(admin1));
			
			UserModel user1  = new UserModel(
					"John", "Arthur", "arthur222@gmail.com", "12345"
					);

			UserModel user2  = new UserModel(
					"John", "Pendragon", "arthur333@gmail.com", "12345"
					);

			UserModel user3  = new UserModel(
					"John", "Bleach", "arthur444@gmail.com", "12345"
					);
			userRepository.saveAll(List.of(user1,user2,user3));
		};
	}
}
