package com.abc.blogger.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Integer user_id;
	
	@Column(name="name")
	@NotNull
	private String name;
	
	@Column(name="company")
	@NotNull
	private String company;

	@Column(name="email", unique=true)
	@NotNull
	private String email;
	
	@Column(name="password")
	@NotNull
	private String password;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Integer user_id, @NotNull String name, @NotNull String company, @NotNull String email,
			@NotNull String password) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.company = company;
		this.email = email;
		this.password = password;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
