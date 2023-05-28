package com.accountregistration.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class Admin {

	@Id
	@SequenceGenerator(
			name = "idGenerator",
			allocationSize = 1,
			sequenceName =  "idGenerator"
			)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "idGenerator"
			)
	private Long id;
	private String name;
	private String password;
	
	
	public Admin(Long id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}
	public Admin() {
		super();
	}
	public Admin(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
