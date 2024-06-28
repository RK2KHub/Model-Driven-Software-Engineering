package com.hotel.jupiter;

import java.util.Scanner;

import com.hotel.jupiter.model.Account;
import com.hotel.jupiter.model.Admin;
import com.hotel.jupiter.model.Customer;
import com.hotel.jupiter.model.DrinkItem;
import com.hotel.jupiter.model.Employee;
import com.hotel.jupiter.model.ExecutiveChef;
import com.hotel.jupiter.model.FoodItem;
import com.hotel.jupiter.model.GeneralManager;
import com.hotel.jupiter.model.OrderMenuItem;
import com.hotel.jupiter.model.Room;

public class JupiterHotels {

	public static void main(String args[]) {
		System.out.println("=============================");
		System.out.println("  Welcome to Jupiter Hotels  ");
		System.out.println("=============================");
		Scanner s = new Scanner(System.in);
		AllData.setUp();
		while (true) {
			System.out.println("\n---------Choose----------");
			System.out.print("1. Login \n2. Sign up\n--> ");
			int i = Integer.parseInt(s.nextLine());
			switch (i) {
			case 1:
				System.out.print("Enter username: ");
				String user = s.nextLine();
				System.out.print("Enter password: ");
				String pass = s.nextLine();
				Account a = new Account();
				int id = a.login(user,pass);
				if(id == -30) {
					System.out.println("==> Login Successful <==");
					adminLogin(id);
				}
				else if ( id == -20) {
					System.out.println("==> Login Successful <==");
					gmLogin(id);
				}
				else if (id == -10) {
					System.out.println("==> Login Successful <==");
					ecLogin(id);
				}
				else if (id != -1) {
					System.out.println("==> Login Successful <==");
					successfullyLoggedin(id);
				} else
					System.out.println("--Login Unsuccessful--");
				break;
				
			case 2:
				System.out.println("Please provide the following details:");
				Customer c = new Customer();
				System.out.println("Name");
				c.setName(s.nextLine());
				System.out.println("Email (username): ");
				c.setEmail(s.nextLine());
				System.out.println("Password: ");
				c.setPassword(s.nextLine());
				c.setCustomerId(AllData.customerList.size());
				System.out.println("Age: ");
				c.setAge(Integer.parseInt(s.nextLine()));
				System.out.println("Contact number: ");
				c.setContact(s.nextLine());
				Account a2 = new Account();
				a2.signUp(c);
				break;
			}
		}
	}
	

	private static void ecLogin(int id) {
		// TODO Auto-generated method stub
		while (true) {
			System.out.println("=============================");
			System.out.println("  Welcome to Jupiter Hotels  ");
			System.out.println("  1. View Received Orders  ");
			System.out.println("  2. Prepare Order");
			System.out.println("  3. View food items  ");
			System.out.println("  4. Add food items ");
			System.out.println("  5. Update food items  ");
			System.out.println("  6. Delete food items  ");
			System.out.println("  7. View drink items  ");
			System.out.println("  8. Add drink items ");
			System.out.println("  9. Update drink items  ");
			System.out.println("  10. Delete drink items  ");
			System.out.println("  11. View ");
			System.out.println("  12. Logout  ");
			System.out.println("=============================");
			Scanner s = new Scanner(System.in);
			boolean flag = false;
			System.out.println("Your choice: ");
			String i = s.nextLine();
			ExecutiveChef ec = new ExecutiveChef();
			switch (i) {
			
				case "1":
					ec.viewReceivedOrders();
					break;
					
				case "2":
					ec.prepareOrder();
					break;
					
				case "3":
					ec.viewAllFoodItems();
					break;
					
				case "4":
					FoodItem item = new FoodItem();
					System.out.println("Food Name: ");
					item.setMealName(s.nextLine());
					System.out.println("Food Type (Appetizer / Main / Dessert) : ");
					item.setFoodItemType(s.nextLine());
					System.out.println("Description: ");
					item.setMealDescription(s.nextLine());
					item.setMealId(AllData.foodList.get(AllData.foodList.size()-1).getMealId()+1);
					ec.createFoodItem(item);
					break;
				
				case "5":
					ec.viewAllFoodItems();
					System.out.println("Please enter ID of food item to update: ");
					int foodId = s.nextInt();
					ec.updateFoodItem(foodId);
					break;	
					
				case "6":
					ec.viewAllFoodItems();
					System.out.println("Please enter ID of food item to delete: ");
					int foodID = s.nextInt();
					ec.deleteFoodItem(foodID);
					break;
					
				case "7":
					ec.viewAllDrinkItems();
					break;
					
				case "8":
					DrinkItem ditem = new DrinkItem();
					System.out.println("Drink Name: ");
					ditem.setMealName(s.nextLine());
					System.out.println("Drink Description");
					ditem.setMealDescription(s.nextLine());
					System.out.println("Is Alcoholic (true /false) :");
					ditem.setAlcoholic(s.nextBoolean());
					System.out.println("Alcohol percentage (0 if non-alcoholic) :");
					ditem.setDrinkAlcoholPercent(s.nextDouble());
					ditem.setMealId(AllData.drinkList.get(AllData.drinkList.size()-1).getMealId()+1);
					ec.createDrinkItem(ditem);
					break;
				
				case "9":
					ec.viewAllDrinkItems();
					System.out.println("Please enter ID of drink item to update: ");
					int drinkId = s.nextInt();
					ec.updateDrinkItem(drinkId);
					break;	
					
				case "10":
					ec.viewAllDrinkItems();
					System.out.println("Please enter ID of drink item to update: ");
					int drinkID = s.nextInt();
					ec.deleteDrinkItem(drinkID);
					break;
					
				case "11":
					ec.viewAllCustomerOrders();
					break;
					
				case "12":
					flag = true;
					break;
				
			} if(flag == true) {
				System.out.println("Successfully Loggedout");
				break;
			}
		}
	}

	private static void gmLogin(int id) {
		// TODO Auto-generated method stub
		GeneralManager gm = new GeneralManager();
		while (true) {
			System.out.println("=============================");
			System.out.println("  Welcome to Jupiter Hotels  ");
			System.out.println("  1. View Profile  ");
			System.out.println("  2. Update Profile  ");
			System.out.println("  3. View Employees List  ");
			System.out.println("  4. Add Employee ");
			System.out.println("  5. Update Employess ");
			System.out.println("  6. Delete Employee");
			System.out.println("  7. View all Reservation ");
			System.out.println("  8. Cancel Reservation  ");
			System.out.println("  9. View Customers  ");
			System.out.println("  10. View All rooms  ");
			System.out.println("  11. Logout  ");
			System.out.println("=============================");
			Scanner s = new Scanner(System.in);
			boolean flag = false;
			System.out.println("Your choice: ");
			String i = s.nextLine();
			switch (i) {
			
			case "1":
				gm.viewProfile(id);
				break;
			
			case "2":
				System.out.println("Please provide the following details:");
				Admin c = new Admin();
				System.out.println("Name");
				c.setName(s.nextLine());
				System.out.println("Email (username): ");
				c.setEmail(s.nextLine());
				System.out.println("Age: ");
				c.setAge(Integer.parseInt(s.nextLine()));
				System.out.println("Contact number: ");
				c.setContact(s.nextLine());
				c.updateProfile(id, c);
				System.out.println("Profile updated succesfully");
				break;
				
			case "3": 
				gm.viewEmployee();
				break;
				
			case "4":
				System.out.println("Please enter following the details to add new employee");
				Employee e = new Employee();
				System.out.println("Name");
				e.setName(s.nextLine());
				System.out.println("Age: ");
				e.setAge(Integer.parseInt(s.nextLine()));
				System.out.println("Contact number: ");
				e.setContact(s.nextLine());
				System.out.println("Role:");
				e.setRole(s.nextLine());
				System.out.println("Email (username): ");
				e.setEmail(s.nextLine());
				System.out.println("Password: ");
				e.setPassword(s.nextLine());
				e.setEmpID(AllData.employeeList.size()-1);
				gm.addEmployee(e);
				System.out.println("Employee added sucessfully!!");
				break;
				
			case "5":
				gm.viewEmployee();
				System.out.println("Please provide the id of the employee you want to update:");
				int empId = s.nextInt();
				System.out.println("Please enter following the details to add new employee");
				Employee e1 = new Employee();
				System.out.println("Name");
				e1.setName(s.nextLine());
				System.out.println("Age: ");
				e1.setAge(Integer.parseInt(s.nextLine()));
				System.out.println("Contact number: ");
				e1.setContact(s.nextLine());
				System.out.println("Role:");
				e1.setRole(s.nextLine());
				System.out.println("Email (username): ");
				e1.setEmail(s.nextLine());
				System.out.println("Password: ");
				gm.updateEmployee(empId, e1);
				System.out.println("Employee updated sucessfully!!");
				break;
				
			case "6":
				gm.viewEmployee();
				System.out.println("Please provide the id of the employee you want to delete:");
				int empID = s.nextInt();
				gm.deleteEmployee(empID);
				System.out.println("Employee deleted sucessfully!!");
				break;
				
			case "7":
				gm.viewAllReservations();
				break;	
				
			case "8":
				gm.cancelReservation();
				break;	
				
			case "9":
				gm.viewCustomerDetails();
				break;	
				
			case "10":
				gm.viewRooms();
				break;
				
			case "11":
				flag = true;
				break;
				
			} if(flag == true) {
				System.out.println("Successfully Loggedout");
				break;
			}
		}
		
		
	}

	private static void adminLogin(int id) {
		// TODO Auto-generated method stub
		Admin ad = new Admin();
		while (true) {
			System.out.println("=============================");
			System.out.println("  Welcome to Jupiter Hotels  ");
			System.out.println("  1. View Profile  ");
			System.out.println("  2. Update Profile  ");
			System.out.println("  3. View Rooms  ");
			System.out.println("  4. Add Room ");
			System.out.println("  5. Update Room  ");
			System.out.println("  6. Delete Room  ");
			System.out.println("  7. View Employees List  ");
			System.out.println("  8. Add Employee ");
			System.out.println("  9. Update Employess ");
			System.out.println("  10. Delete Employee");
			System.out.println("  11. Create General Manager  ");
			System.out.println("  12. Create Executive Chef");
			System.out.println("  13. Logout  ");
			System.out.println("=============================");
			Scanner s = new Scanner(System.in);
			boolean flag = false;
			System.out.println("Your choice: ");
			String i = s.nextLine();
			switch (i) {
			
			case "1":
				ad.viewProfile(id);
				break;
			
			case "2":
				System.out.println("Please provide the following details:");
				Admin c = new Admin();
				System.out.println("Name");
				c.setName(s.nextLine());
				System.out.println("Email (username): ");
				c.setEmail(s.nextLine());
				System.out.println("Age: ");
				c.setAge(Integer.parseInt(s.nextLine()));
				System.out.println("Contact number: ");
				c.setContact(s.nextLine());
				c.updateProfile(id, c);
				System.out.println("PRofile updated succesfully");
				break;
			
			case "3":
				ad.viewRooms();
				break;
				
			case "4":
				Room r2 = new Room();
				System.out.println("Please provide the following details to create a new room:");
				System.out.println("Room Title:" );
				r2.setRoomTitle(s.nextLine());
				System.out.println("Room Description:" );
				r2.setRoomDescription(s.nextLine());
				System.out.println("Room Occupancy:" );
				r2.setRoomOccupancy(s.nextInt());
				System.out.println("Room Number:" );
				r2.setRoomNumber(s.nextLine());
				System.out.println("Room Rent:" );
				r2.setRoomPrice(s.nextDouble());
				r2.setIsAvailable(true);
				r2.setRoomId(AllData.roomList.get(AllData.roomList.size()-1).getRoomId()+1);
				ad.createRoom(r2);
				System.out.println("Room created sucessfully!!");
				break;
				
			case "5":
				Room r3 = new Room();
				r3.viewRoom();
				System.out.println("Please provide the id of the room you want to update:");
				int roomId = s.nextInt();
				System.out.println("Please provide the following details to update the room:");
				System.out.println("Room Title:" );
				r3.setRoomTitle(s.nextLine());
				System.out.println("Room Description:" );
				r3.setRoomDescription(s.nextLine());
				System.out.println("Room Occupancy:" );
				r3.setRoomOccupancy(s.nextInt());
				System.out.println("Room Number:" );
				r3.setRoomNumber(s.nextLine());
				System.out.println("Room Rent:" );
				r3.setRoomPrice(s.nextDouble());
				System.out.println("IsAvailable: true/false");
				r3.setIsAvailable(s.nextBoolean());
				ad.updateRoom(roomId, r3);
				System.out.println("Room updated sucessfully!!");
				break;	
				
			case "6":
				ad.viewRooms();
				System.out.println("Please enter the room ID of the room you want to delete:");
				int roomID = s.nextInt();
				ad.deleteRoom(roomID);
				System.out.println("Room deleted sucessfully!!");
				break;
				
			case "7": 
				ad.viewEmployee();
				break;
				
			case "8":
				System.out.println("Please enter following the details to add new employee");
				Employee e = new Employee();
				System.out.println("Name");
				e.setName(s.nextLine());
				System.out.println("Age: ");
				e.setAge(Integer.parseInt(s.nextLine()));
				System.out.println("Contact number: ");
				e.setContact(s.nextLine());
				System.out.println("Role:");
				e.setRole(s.nextLine());
				System.out.println("Email (username): ");
				e.setEmail(s.nextLine());
				System.out.println("Password: ");
				e.setPassword(s.nextLine());
				e.setEmpID(AllData.employeeList.size()-1);
				ad.addEmployee(e);
				System.out.println("Employee added sucessfully!!");
				break;
				
			case "9":
				ad.viewEmployee();
				System.out.println("Please provide the id of the employee you want to update:");
				int empId = s.nextInt();
				System.out.println("Please enter following the details to add new employee");
				Employee e1 = new Employee();
				System.out.println("Name");
				e1.setName(s.nextLine());
				System.out.println("Age: ");
				e1.setAge(Integer.parseInt(s.nextLine()));
				System.out.println("Contact number: ");
				e1.setContact(s.nextLine());
				System.out.println("Role:");
				e1.setRole(s.nextLine());
				System.out.println("Email (username): ");
				e1.setEmail(s.nextLine());
				System.out.println("Password: ");
				ad.updateEmployee(empId, e1);
				System.out.println("Employee updated sucessfully!!");
				break;
				
			case "10":
				ad.viewEmployee();
				System.out.println("Please provide the id of the employee you want to delete:");
				int empID = s.nextInt();
				ad.deleteEmployee(empID);
				System.out.println("Employee deleted sucessfully!!");
				break;
				
			case "11":
				Admin admin = new Admin();
				Employee e11 = new Employee();
				System.out.println("Name");
				e11.setName(s.nextLine());
				System.out.println("Age: ");
				e11.setAge(Integer.parseInt(s.nextLine()));
				System.out.println("Contact number: ");
				e11.setContact(s.nextLine());
				e11.setRole(AllData.ROLE_GENERAL_MANAGER);
				e11.setEmail("gm");
				e11.setPassword("gm");
				e11.setEmpID(AllData.employeeList.size()-1);
				admin.assignGeneralManager(e11);
				break;
				
			case "12":
				Admin admin1 = new Admin();
				Employee e111 = new Employee();
				System.out.println("Name");
				e111.setName(s.nextLine());
				System.out.println("Age: ");
				e111.setAge(Integer.parseInt(s.nextLine()));
				System.out.println("Contact number: ");
				e111.setContact(s.nextLine());
				e111.setRole(AllData.ROLE_EXECUTIVE_CHEF);
				e111.setEmail("ec");
				e111.setPassword("ec");
				e111.setEmpID(AllData.employeeList.size()-1);
				admin1.assignExecutiveChef(e111);
				break;
				
			case "13":
				flag = true;
				break;
				
			} if(flag == true) {
				System.out.println("Successfully Loggedout");
				break;
			}
		}
		
	}



	private static void successfullyLoggedin(int id) {
		while (true) {
			System.out.println("=============================");
			System.out.println("  Welcome to Jupiter Hotels  ");
			System.out.println("  1. View Profile  ");
			System.out.println("  2. Update Profile  ");
			System.out.println("  3. View Rooms  ");
			System.out.println("  4. Reservation Section ");
			System.out.println("  5. Log Out  ");
			System.out.println("  6. Order Food  ");
			System.out.println("=============================");
			Scanner s = new Scanner(System.in);
			boolean flag = false;
			System.out.println("Your choice: ");
			String i = s.nextLine();
			switch (i) {
			case "1":
				Customer c1 = new Customer();
				c1.viewProfile(id);
				break;
			case "2":
				System.out.println("Please provide the following details:");
				Customer c = new Customer();
				System.out.println("Name");
				c.setName(s.nextLine());
				System.out.println("Email (username): ");
				c.setEmail(s.nextLine());
				System.out.println("Age: ");
				c.setAge(Integer.parseInt(s.nextLine()));
				System.out.println("Contact number: ");
				c.setContact(s.nextLine());
				c.updateProfile(id, c);
				break;
			case "3":
				Customer c2 = new Customer();
				c2.viewRooms();
				break;

			case "4":
				reservationSection(id);
				break;
			case "5":
				flag = true;
				break;
			case "6":
				foodArea(id);
				break;
				
			}
			if(flag==true) {
				System.out.println("Successfully Loggedout");
				break;
			}
		}

	}
	

	private static void foodArea(Integer id) {
		System.out.println("=============================");
		System.out.println("  Welcome to Jupiter Hotels  ");
		System.out.println("=============================");
		System.out.println("Food Options:");
		Scanner s = new Scanner(System.in);
		OrderMenuItem order = new OrderMenuItem();
		System.out.println("|- 1. View Food  ");
		System.out.println("|- 2. Order Food  ");
		System.out.println("|- 3. View Drink  ");
		System.out.println("|- 4. Order Drink  ");
		System.out.println("|- 5. View Orders  ");
		System.out.println("|- 6. Cancel Order  ");
		System.out.println("|--> ");
		int b = Integer.parseInt(s.nextLine());
		switch(b) {
		case 1:
			order.viewFoodItems();
			break;
		case 2:
			order.orderFoodItems(id);
			break;
		case 3:
			order.viewDrinkItems();
			break;
		case 4:
			order.orderDrinkItems(id);
			break;
		case 5:
			Customer cu = AllData.customerList.get(id);
			cu.viewOrders(cu.getOrder());
			break;
		case 6:
			order.cancelOrder(id);
			break;
		}
	}
	
	
	private static void reservationSection(int id) {
		boolean navigateBack = false;
		
		while(!navigateBack) {
			
			Customer c = AllData.customerList.stream()
	        		.filter(cu -> cu.getCustomerId() == id).findFirst()
	                .orElse(null);
			
			System.out.println("=============================");
			System.out.println("  Welcome to Jupiter Hotels  ");
			System.out.println("=============================");
			System.out.println("");
			System.out.println("Select An Option:");
			System.out.println("|- 1. Make Reservation    ");
			System.out.println("|- 2. Cancel Reservation  ");
			System.out.println("|- 3. Check In            ");
			System.out.println("|- 4. Check Out           ");
			System.out.println("|- 5. Make Payment        ");
			System.out.println("|- 6. Return to Main Menu ");
			System.out.println("|--> ");
			
			@SuppressWarnings("resource")
			Scanner s = new Scanner(System.in);
			int b = Integer.parseInt(s.nextLine());
			
			switch(b) {
			case 1:
				c.makeReservation();
				break;
			case 2:
				c.cancelReservation();
				break;
			case 3:
				c.checkIn();
				break;
			case 4:
				c.checkOut();
				break;
			case 5:
				c.makePayment();
				break;
			case 6:
				navigateBack = true;
				break;
			}
			
		}
		
	}
}
