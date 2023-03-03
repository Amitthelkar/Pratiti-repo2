package com.pratiti.output;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import com.pratiti.entity.Address;

public class CustomerData {
	private String name;
	private String email;
	private LocalDate localdate;
	private String password;
	private MultipartFile profilepic;

	private Address address=new Address();

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

	public LocalDate getLocaldate() {
		return localdate;
	}

	public void setLocaldate(LocalDate localdate) {
		this.localdate = localdate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public MultipartFile getProfilepic() {
		return profilepic;
	}

	public void setProfilepic(MultipartFile profilepic) {
		this.profilepic = profilepic;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	

}
