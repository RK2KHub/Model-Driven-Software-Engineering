package com.hotel.jupiter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.hotel.jupiter.model.Account;
import com.hotel.jupiter.model.Admin;
import com.hotel.jupiter.model.Customer;

class AccountTest {

	@Test
	void testSignUp() {
		Customer c1 = new Customer();
		c1.setAge(20);
		c1.setContact("55566669999");
		c1.setCustomerId(10);
		c1.setEmail("kkierfijmfrjim");
		c1.setName("priya");
		c1.setPassword("kk");
        assertFalse(AllData.customerList.contains(c1));
        Account a = new Account();
        a.signUp(c1);
        assertTrue(AllData.customerList.contains(c1));
	}

	@Test
	void testLoginValidCustomer() {
		Customer c1 = new Customer();
		c1.setAge(20);
		c1.setContact("55566669999");
		c1.setCustomerId(2);
		c1.setEmail("kk");
		c1.setName("priya");
		c1.setPassword("kk");
		c1.signUp(c1);
        Account a = new Account();
        int id = a.login(c1.getEmail(),c1.getPassword());
        assertEquals(id, c1.getCustomerId().intValue());
	}

	@Test
	void testLoginInValidCustomer() {
        Account a = new Account();
        int id = a.login("random-email","random-password");
        assertEquals(id, -1);
	}
	
	@Test
	void testLoginValidAdmin() {
		Admin admin = new Admin();
		admin.setId(11111);
		admin.setEmail("admin");
		admin.setName("admin");
		admin.setPassword("admin");
		int adminLoginFlag = -30;
        int id = admin.login(admin.getEmail(),admin.getPassword());
        System.out.println(id);
        System.out.println(adminLoginFlag);
        assertEquals(id, adminLoginFlag);
	}


}
