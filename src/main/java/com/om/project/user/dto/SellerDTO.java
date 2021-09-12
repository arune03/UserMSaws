package com.om.project.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
//import javax.validation.constraints.Size;
import javax.validation.constraints.Size;

import com.om.project.user.entity.Seller;

public class SellerDTO {

	String sellerid;
	
	String name;
	
	String email;
	
	
	String phoneno;
	
	String password;

	Boolean isactive;

	public String getSellerid() {
		return sellerid;
	}

	public void setSellerid(String sellerid) {
		this.sellerid = sellerid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

	@Override
	public String toString() {
		return "SellerDTO [sellerid=" + sellerid + ", name=" + name + ", email=" + email + ", phoneno=" + phoneno
				+ ", password=" + password + ", isactive=" + isactive + "]";
	}

	// Converts Entity into DTO
	public static SellerDTO valueOf(Seller seller) {
		SellerDTO sellerDTO = new SellerDTO();
		sellerDTO.setSellerid(seller.getSellerid());
		sellerDTO.setName(seller.getName());
		sellerDTO.setEmail(seller.getEmail());
		sellerDTO.setPhoneno(seller.getPhoneno());
		sellerDTO.setPassword(seller.getPassword());
		sellerDTO.setIsactive(seller.getIsactive());
		return sellerDTO;
	}

	public Seller generateSeller() {
		Seller seller = new Seller();
		seller.setSellerid(this.getSellerid());
		seller.setName(this.getName());
		seller.setPhoneno(this.getPhoneno());
		seller.setEmail(this.getEmail());
		seller.setPassword(this.getPassword());
		seller.setIsactive(this.getIsactive());
		return seller;
	}
}
