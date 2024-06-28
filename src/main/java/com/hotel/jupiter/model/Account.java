package com.hotel.jupiter.model;

import java.util.List;
import java.util.stream.Collectors;

import com.hotel.jupiter.AllData;

public class Account {

	private String name;
	private Integer age;
	private String email;
	private String contact;
	private String password;

	public int login(String user, String pass) {
		if (user.equalsIgnoreCase("admin") && pass.equalsIgnoreCase("admin")) {
			return -30;
		} else if (user.equalsIgnoreCase("gm") && pass.equalsIgnoreCase("gm")) {
			return -20;
		} else if (user.equalsIgnoreCase("ec") && pass.equalsIgnoreCase("ec")) {
			return -10;
		} else {
			List<Customer> customers = AllData.customerList.stream()
					.filter(c -> c.getEmail().equals(user) && c.getPassword().equals(pass))
					.collect(Collectors.toList());
			return customers.isEmpty() ? -1 : customers.get(0).getCustomerId().intValue();
		}
	}

	public void signUp(Customer c) {
		List<Customer> list = AllData.customerList.stream().filter(x -> x.getEmail() == c.getEmail())
				.collect(Collectors.toList());
		if (list.isEmpty()) {
			AllData.customerList.add(c);
			System.out.println("Customer registration successful");
		} else {
			System.out.println("Customer ID already exists");
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
