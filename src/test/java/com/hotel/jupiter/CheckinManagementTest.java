package com.hotel.jupiter;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.hotel.jupiter.model.*;

public class CheckinManagementTest {

    @Test
    public void testCheckIn() {
        // Test checking in reservation
        Room room = new Room();
        
		room.setIsAvailable(true);
		room.setRoomDescription("This studio is very unique located in the heart of the action on a very quiet and charming street" );
		room.setRoomId(AllData.roomList.size() + 1);
		room.setRoomNumber("500");
		room.setRoomOccupancy(3);
		room.setRoomPrice(100.00);
		room.setRoomTitle("Cozy Studio");
		
		AllData.roomList.add(room);
        
        Customer customer = new Customer();
       
        customer.setAge(20);
        customer.setContact("12345678");
        customer.setCustomerId(AllData.customerList.size() + 1);
        customer.setEmail("ed3@mail.com");
        customer.setName("ed3");
        customer.setPassword("123");
        
        AllData.customerList.add(customer);
        
        Reservation reservation = new Reservation();
        
        customer.setReservation(reservation);
        customer.getReservation().setCustomer(customer);
        
        
		customer.getReservation().createReservation(room.getRoomId());
		
		customer.getReservation().getCheckinManagement().checkIn();
		
        assertTrue(reservation.getCheckinManagement().getCheckIn());
        assertFalse(reservation.getCheckinManagement().getCheckOut());
    }
    
    
    @Test
    public void testCheckOut() {
        // Test checking out reservation
        Room room = new Room();
        
		room.setIsAvailable(true);
		room.setRoomDescription("This studio is very unique located in the heart of the action on a very quiet and charming street" );
		room.setRoomId(AllData.roomList.size() + 1);
		room.setRoomNumber("500");
		room.setRoomOccupancy(3);
		room.setRoomPrice(100.00);
		room.setRoomTitle("Cozy Studio");
		
		AllData.roomList.add(room);
        
        Customer customer = new Customer();
       
        customer.setAge(20);
        customer.setContact("12345678");
        customer.setCustomerId(AllData.customerList.size() + 1);
        customer.setEmail("ed4@mail.com");
        customer.setName("ed4");
        customer.setPassword("123");
        
        AllData.customerList.add(customer);
        
        Reservation reservation = new Reservation();
        
        customer.setReservation(reservation);
        customer.getReservation().setCustomer(customer);
        
        
		customer.getReservation().createReservation(room.getRoomId());
		
		customer.getReservation().getCheckinManagement().checkOut();
		
        assertFalse(reservation.getCheckinManagement().getCheckIn());
        assertTrue(reservation.getCheckinManagement().getCheckOut());
    }
    
    
    @Test
    public void testCancelBooking() {
        // Test cancel reservation
        Room room = new Room();
        
		room.setIsAvailable(true);
		room.setRoomDescription("This studio is very unique located in the heart of the action on a very quiet and charming street" );
		room.setRoomId(AllData.roomList.size() + 1);
		room.setRoomNumber("500");
		room.setRoomOccupancy(3);
		room.setRoomPrice(100.00);
		room.setRoomTitle("Cozy Studio");
		
		AllData.roomList.add(room);
        
        Customer customer = new Customer();
        
        AllData.customerList.add(customer);
       
        customer.setAge(20);
        customer.setContact("12345678");
        customer.setCustomerId(AllData.customerList.size() + 1);
        customer.setEmail("ed5@mail.com");
        customer.setName("ed5");
        customer.setPassword("123");
        
        Reservation reservation = new Reservation();
        
        customer.setReservation(reservation);
        customer.getReservation().setCustomer(customer);
        
		customer.getReservation().createReservation(room.getRoomId());

        assertTrue(customer.getReservation().getCheckinManagement().cancelBooking());
    }
    
    
    @Test
    public void testGenerateBill() {
        // Test generating bill
        Room room = new Room();
        
		room.setIsAvailable(true);
		room.setRoomDescription("This studio is very unique located in the heart of the action on a very quiet and charming street" );
		room.setRoomId(AllData.roomList.size() + 1);
		room.setRoomNumber("500");
		room.setRoomOccupancy(3);
		room.setRoomPrice(100.00);
		room.setRoomTitle("Cozy Studio");
		
		AllData.roomList.add(room);
        
        Customer customer = new Customer();
        
        AllData.customerList.add(customer);
       
        customer.setAge(20);
        customer.setContact("12345678");
        customer.setCustomerId(AllData.customerList.size() + 1);
        customer.setEmail("ed6@mail.com");
        customer.setName("ed6");
        customer.setPassword("123");
        
        Reservation reservation = new Reservation();
        
        customer.setReservation(reservation);
        
        customer.getReservation().setCustomer(customer);
        
		customer.getReservation().createReservation(room.getRoomId());

        assertNotNull(customer.getReservation().getCheckinManagement().generateBill());
    }
}

