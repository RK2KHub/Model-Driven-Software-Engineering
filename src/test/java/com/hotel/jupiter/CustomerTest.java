package com.hotel.jupiter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.hotel.jupiter.model.Account;
import com.hotel.jupiter.model.Customer;

class CustomerTest {

	@Test
    public void testViewProfile() {
		Customer c1 = new Customer();
		c1.setAge(20);
		c1.setContact("55566669999");
		c1.setCustomerId(AllData.customerList.size());
		c1.setEmail("kk");
		c1.setName("kiran");
		c1.setPassword("kk");
        AllData.customerList.add(c1);
        c1.viewProfile(0);
    }

    @Test
    public void testUpdateProfile() {
		Customer c1 = new Customer();
		c1.setAge(20);
		c1.setContact("55566669999");
		c1.setCustomerId(AllData.customerList.size());
		c1.setEmail("kk");
		c1.setName("priya");
		c1.setPassword("kk");
        AllData.customerList.add(c1);
		Customer c2 = new Customer();
		c2.setAge(20);
		c2.setContact("2222222222222222");
		c2.setCustomerId(c1.getCustomerId());
		c2.setEmail("kk");
		c2.setName("Ragapriya");
		c2.setPassword("kk");
        c2.updateProfile(c2.getCustomerId(), c2);
        int i = c2.getCustomerId().intValue();
        assertEquals(c2.getName(), AllData.customerList.get(i).getName());
        assertEquals(c2.getEmail(), AllData.customerList.get(i).getEmail());
        assertEquals(c2.getAge(), AllData.customerList.get(i).getAge());
        assertEquals(c2.getContact(), AllData.customerList.get(i).getContact());
    }
    
	@Test
	void testSignUp() {
		Customer c1 = new Customer();
		c1.setAge(20);
		c1.setContact("55566669999");
		c1.setCustomerId(2);
		c1.setEmail("kk");
		c1.setName("priya");
		c1.setPassword("kk");
        assertFalse(AllData.customerList.contains(c1));
        AllData.customerList.add(c1);
        Account a = new Account();
        a.signUp(c1);
        assertTrue(AllData.customerList.contains(c1));
	}



}
