package com.hotel.jupiter;

import java.util.ArrayList;
import java.util.List;

import com.hotel.jupiter.model.Admin;
import com.hotel.jupiter.model.Customer;
import com.hotel.jupiter.model.DrinkItem;
import com.hotel.jupiter.model.Employee;
import com.hotel.jupiter.model.FoodItem;
import com.hotel.jupiter.model.Room;
import com.hotel.jupiter.model.RoomState;
import com.hotel.jupiter.model.Reservation;

public class AllData {

	public static final String ROLE_GENERAL_MANAGER = "GeneralManager";
	public static final String ROLE_EXECUTIVE_CHEF = "GeneralManager";
	public static final String ROLE_RECEPTIONIST = "Receptionist";
	public static final String ROLE_BUSBOY = "Busboy";

	public static ArrayList<Customer> customerList = new ArrayList<Customer>();
	public static ArrayList<Room> roomList = new ArrayList<Room>();
	public static ArrayList<FoodItem> foodList = new ArrayList<FoodItem>();
	public static ArrayList<DrinkItem> drinkList = new ArrayList<DrinkItem>();
	public static ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
	public static ArrayList<Admin> adminList = new ArrayList<Admin>();
	public static ArrayList<Employee> employeeList = new ArrayList<Employee>();
		
	public static void setUp() {
		roomList.addAll(generateRoomList());
		customerList.addAll(generateCustomers());
		foodList.addAll(generateFoodItems());
		drinkList.addAll(generateDrinkItems());
		adminList.addAll(addAdmin());
		employeeList.addAll(generateEmployees());
	}


	private static List<Admin> addAdmin() {
		List<Admin> list = new ArrayList();
		Admin ad = new Admin();
		ad.setId(12345);
		ad.setAge(30);
		ad.setContact("4140001111");
		ad.setName("Admin1");
		ad.setEmail("admin");
		ad.setPassword("admin");
		list.add(ad);
		

		return list;
	}
	
	private static List<Employee> generateEmployees(){
		List<Employee> emp = new ArrayList();
		Employee e1 = new Employee();
		e1.setName("Sarah Connor");
		e1.setAge(45);
		e1.setRole(ROLE_RECEPTIONIST);
		e1.setContact("3216549871");
		e1.setEmail("sarah.connor@jupiter.com");
		e1.setPassword("sarah123");
		e1.setEmpID(0);
		emp.add(e1);
		Employee e2 = new Employee();
		e2.setName("Thomas Lee");
		e2.setAge(25);
		e2.setRole(ROLE_BUSBOY);
		e2.setContact("4216549871");
		e2.setEmail("thomas.lee@jupiter.com");
		e2.setPassword("thomas123");
		e2.setEmpID(1);
		emp.add(e2);
		Employee e3 = new Employee();
		e3.setName("Courtney Rett");
		e3.setAge(35);
		e3.setRole(ROLE_RECEPTIONIST);
		e3.setContact("3316549871");
		e3.setEmail("courtney.rett@jupiter.com");
		e3.setPassword("courtney123");
		e3.setEmpID(2);
		emp.add(e3);
		return emp;
		
	}



	private static List<DrinkItem> generateDrinkItems() {
		List<DrinkItem> list = new ArrayList<>();
		DrinkItem f1 = new DrinkItem();
		f1.setMealName("Red Blend Wine");
		f1.setMealId(0);
		f1.setMealDescription("This dry red wine has a round flavor and bold finish.");
		f1.setAlcoholic(true);
		f1.setDrinkAlcoholPercent(5.0);
		list.add(f1);
		
		DrinkItem d2 = new DrinkItem();
		d2.setMealName("Iced Tea");
		d2.setMealId(1);
		d2.setMealDescription("Refreshing iced tea served cold with a slice of lemon.");
		d2.setAlcoholic(false);
		d2.setDrinkAlcoholPercent(0.0);
		list.add(d2);

		DrinkItem d3 = new DrinkItem();
		d3.setMealName("Mango Lassi");
		d3.setMealId(2);
		d3.setMealDescription("A creamy yogurt-based drink blended with ripe mangoes and spices.");
		d3.setAlcoholic(false);
		d3.setDrinkAlcoholPercent(0.0);
		list.add(d3);
		return list;
	}


	private static List<FoodItem> generateFoodItems() {
		List<FoodItem> list = new ArrayList<>();
		FoodItem f1 = new FoodItem();
		f1.setFoodItemType("appetizer");
		f1.setMealName("samosa");
		f1.setMealId(0);
		f1.setMealDescription("A folded pastry stuffed with potato, peas and spices");
		list.add(f1);
		FoodItem f2 = new FoodItem();
		f2.setFoodItemType("main course");
		f2.setMealName("Chicken Curry");
		f2.setMealId(1);
		f2.setMealDescription("Tender chicken pieces cooked in a rich and flavorful curry sauce.");
		list.add(f2);
		FoodItem f3 = new FoodItem();
		f3.setFoodItemType("dessert");
		f3.setMealName("Chocolate Cake");
		f3.setMealId(2);
		f3.setMealDescription("A decadent chocolate cake with layers of rich chocolate frosting.");
		list.add(f3);
		return list;
	}


	private static List<Customer> generateCustomers() {
		List<Customer> list = new ArrayList<>();
		Customer c1 = new Customer();
		c1.setAge(20);
		c1.setContact("55566669999");
		c1.setCustomerId(AllData.customerList.size());
		c1.setEmail("kk");
		c1.setName("kiran");
		c1.setPassword("kk");
		list.add(c1);
		return list;
	}


	private static ArrayList<Room> generateRoomList() {
		ArrayList<Room> list = new ArrayList<Room>();
		Room r1 = new Room();
		r1.setIsAvailable(true);
		r1.setRoomDescription("This studio is very unique located in the heart of the action on a very quiet and charming street" );
		r1.setRoomId(0);
		r1.setRoomNumber("500");
		r1.setRoomOccupancy(3);
		r1.setRoomPrice(100.00);
		r1.setRoomTitle("Cozy Studio");
		r1.setState(RoomState.AVAILABLE.getCurrentValue());
		list.add(r1);
		Room r2 = new Room();
		r2.setIsAvailable(true);
		r2.setRoomDescription("Great space to get away." );
		r2.setRoomId(1);
		r2.setRoomNumber("500");
		r2.setRoomOccupancy(4);
		r2.setRoomPrice(100.00);
		r2.setRoomTitle("One bedroom delux with a double bed ");
		r2.setState(RoomState.AVAILABLE.getCurrentValue());
		list.add(r2);
		return list;
	}

}
