package com.hotel.jupiter; 

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.hotel.jupiter.model.*;

public class ReservationTest {

    @Test
    public void testCreateReservation() {
        // Test creating a new reservation
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
        customer.setEmail("ed2@mail.com");
        customer.setName("ed2");
        customer.setPassword("123");
        
        AllData.customerList.add(customer);
        
        Reservation reservation = new Reservation();
        
        customer.setReservation(reservation);
        customer.getReservation().setCustomer(customer);
        
        assertNotNull(customer.getReservation().createReservation(room.getRoomId()));
        assertEquals(customer, reservation.getCustomer());
        assertFalse(reservation.getRooms().contains(room));
    }
}

