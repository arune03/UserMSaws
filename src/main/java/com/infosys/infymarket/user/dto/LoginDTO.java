package com.infosys.infymarket.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class LoginDTO {
	
	String email;
	
	String password;
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
	@Override
	public String toString() {
		return "LoginDTO [email=" + email + ", password=" + password + "]";
	}
	
}
