package com.hotel.jupiter.model;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.hotel.jupiter.AllData;

public class GeneralManager extends Employee {
	private List<Reservation> reservations = new ArrayList<>();

	public GeneralManager() {
		this.role = "General_Manager";

		Random random = new Random(); // Create an instance of Random

		this.empID = random.nextInt(); // Assigns a random int value to empID
	}

	/************* Setters and Getters ***************************/
	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	/***********************************************************/

	public void viewAllReservations() {

		Reservation r = new Reservation();

		ReservationReport rr = new ReservationReport();

		r.setGeneralManager(this);

		r.setReservationReport(rr);

		rr.setReservation(r);

		List<Reservation> allReservations = r.getReservationReport().getReservationReport();

		// display all reservations
		if(allReservations.isEmpty()) {
			
			System.out.println("=========================================");
			System.out.println("         No Reservations Yet             ");
			System.out.println("=========================================");
			System.out.println("");
			
		} else {
		
			System.out.println("=========================================");
			System.out.println("       Lists Of All Reservations         ");
			System.out.println("=========================================");
			System.out.println("");
		
			int i = 1;
			for (Reservation reservation : allReservations) {
		
				// Convert milliseconds to LocalDate
				LocalDate date = Instant.ofEpochMilli(reservation.getReservationDate()).atZone(ZoneId.systemDefault())
						.toLocalDate();
		
				// Define the date format
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
				// Format the LocalDate object using the formatter
				String formattedDate = date.format(formatter);
		
				System.out.println("|-" + i);
				System.out.println("|- Reservation Date: " + formattedDate);
				System.out.println("|- Maximum Period: " + reservation.getMaxPeriod());
				System.out.println("");
		
				i++;
			}
			
		}

		return;
	}

	public void cancelReservation() {
		// display all reservations
		Reservation r = new Reservation();

		ReservationReport rr = new ReservationReport();

		r.setGeneralManager(this);

		r.setReservationReport(rr);

		rr.setReservation(r);

		List<Reservation> allReservations = r.getReservationReport().getReservationReport();

		// display all reservations
		if(allReservations.isEmpty()) {
			
			System.out.println("=========================================");
			System.out.println("           No Reservations               ");
			System.out.println("=========================================");
			System.out.println("");
			
		} else {
		
			System.out.println("=========================================");
			System.out.println("          Select A Reservation           ");
			System.out.println("=========================================");
			System.out.println("");
	
			for (int i = 1; i <= allReservations.size(); i++) {
				Reservation reservation = allReservations.get(i - 1);
	
				// Convert milliseconds to LocalDate
				LocalDate date = Instant.ofEpochMilli(reservation.getReservationDate()).atZone(ZoneId.systemDefault())
						.toLocalDate();
	
				// Define the date format
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
				// Format the LocalDate object using the formatter
				String formattedDate = date.format(formatter);
	
				System.out.println("|-" + i);
				System.out.println("|- Reservation Date: " + formattedDate);
				System.out.println("|- Maximum Period: " + reservation.getMaxPeriod());
				System.out.println("");
			}
	
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
	
			System.out.println("|- Enter A Number Option:");
			System.out.println("");
	
			int userInput = 0;
	
			try {
				userInput = Integer.parseInt(scanner.nextLine()); // Wait for user input
	
			} catch (NumberFormatException e) {
	
				System.out.println("Invalid input. Please enter a valid integer.");
				System.out.println("");
			}
	
			while (true) {
	
				// Check if userInput is a valid index in reservations list
				if (userInput > 0 && userInput <= allReservations.size()) {
	
					break; // Valid input; exit loop
				} else {
	
					System.out.println("|- Invalid, Enter A Valid Number Option:");
					System.out.println("");
	
					try {
						userInput = Integer.parseInt(scanner.nextLine()); // Wait for user input
	
					} catch (NumberFormatException e) {
	
						System.out.println("Invalid input. Please enter a valid integer.");
						System.out.println("");
					}
				}
			}
	
			// retrieve reservation
			Reservation reservation = allReservations.get(userInput - 1);
	
			// set default message
			String message = "Reservation could not be cancelled";
	
			boolean isCancelled = reservation.getCheckinManagement().cancelBooking();
	
			if (isCancelled) {
	
				// remove association between customer and reservation, since cancelled
				reservation.setCustomer(null);
	
				reservation = null;
	
				// set success message
				message = "Reservation cancelled successully";
			}
	
			// display message
			System.out.println("|- " + message);
			System.out.println("");
		
		}
				
		return;	
	}

	/**
	 * 
	 * @param empID
	 */
	public void deleteEmployee(int empID) {
		// TODO - implement Admin.deleteEmployee
		Employee emp = AllData.employeeList.stream().filter(r1 -> r1.getEmpID() == empID).findFirst().orElse(null);
		if (emp != null) {
			int v = AllData.employeeList.indexOf(emp);
			AllData.employeeList.remove(v);
		} else {
			System.out.println("This ID does not exist!!");
		}

	}

	/**
	 * 
	 * @param empID
	 */
	public void updateEmployee(int empID, Employee e) {
		// TODO - implement Admin.updateEmployee
		Employee e2 = AllData.employeeList.stream().filter(emp -> emp.getEmpID() == empID).findFirst().orElse(null);
		if (e2 != null) {
			e.setEmpID(empID);
			int v = AllData.roomList.indexOf(e2);
			AllData.employeeList.remove(v);
			AllData.employeeList.add(e);

		} else {
			System.out.println("This ID does not exist!!");
		}

	}

	/**
	 * 
	 * @param employee
	 */
	public void addEmployee(Employee employee) {
		List<Employee> list = AllData.employeeList.stream().filter(x -> x.getEmpID() == employee.getEmpID())
				.collect(Collectors.toList());
		if (list.isEmpty()) {
			AllData.employeeList.add(employee);
		} else {
			System.out.println("Employee ID already exists");
		}

	}

	public void viewEmployee() {
		List<Employee> empList = AllData.employeeList.stream().collect(Collectors.toList());
		for (int i = 1; i <= empList.size(); i++) {
			Employee e = empList.get(i - 1);
			System.out.println("|--------------------------------");
			System.out.println("|---------  ID:" + e.getEmpID() + "  ---------------");
			System.out.println("|-- Name:" + e.getName());
			System.out.println("|-- Age:" + e.getAge());
			System.out.println("|-- Role:" + e.getRole());
			System.out.println("|-- Contact:" + e.getContact());
			System.out.println("|-- Email:" + e.getEmail());
			System.out.println("|--------------------------------");
			System.out.println();
		}
	}

	public void viewProfile(Integer id) {
		Employee c = AllData.employeeList.get(id.intValue());
		System.out.println("Name	: " + c.getName());
		System.out.println("Email	: " + c.getEmail());
		System.out.println("Age	: " + c.getAge());
		System.out.println("Contact : " + c.getContact());

	}

	public void viewRooms() {
		Room r = new Room();
		r.viewRoom();
	}

	public void viewCustomerDetails() {
		List<Customer> custList = AllData.customerList.stream().collect(Collectors.toList());
		System.out.println("CUSTOMER DETAILS");
		System.out.println("----------------------------------------");
		System.out.println("----------------------------------------");
		for (int i = 1; i <= custList.size(); i++) {
			Customer c = custList.get(i - 1);
			System.out.println("Customer " + i);
			System.out.println("----------------------------------------");
			System.out.println("Name	: " + c.getName());
			System.out.println("Email	: " + c.getEmail());
			System.out.println("Age	: " + c.getAge());
			System.out.println("Contact : " + c.getContact());
			System.out.println("----------------------------------------");
			System.out.println("----------------------------------------");

		}
	}

}