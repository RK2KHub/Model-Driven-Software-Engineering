package com.hotel.jupiter.model;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.hotel.jupiter.AllData;

public class ExecutiveChef extends Employee {

	MealOption mealOption;

	/**
	 * 
	 * @param menuItem
	 */
	public void createFoodItem(FoodItem menuItem) {
		AllData.foodList.add(menuItem);
	}

	/**
	 * 
	 * @param menuItemID
	 */
	public void deleteFoodItem(int menuItemID) {
		// TODO - implement ExecutiveChef.deleteMenuItem
		FoodItem food = AllData.foodList.stream().filter(r1 -> r1.getMealId() == menuItemID).findFirst().orElse(null);
		if (food != null) {
			int v = AllData.foodList.indexOf(food);
			AllData.foodList.remove(v);
		} else {
			System.out.println("This ID does not exist!!");
		}
	}

	/**
	 * 
	 * @param menuItemID
	 */
	public void updateFoodItem(Integer menuItemID) {
		FoodItem food = AllData.foodList.stream().filter(x -> x.getMealId() == menuItemID).findFirst().orElse(null);
		if( food != null) {
			Scanner s = new Scanner(System.in);
			FoodItem item = new FoodItem();
			System.out.println("Food Name: ");
			item.setMealName(s.nextLine());
			System.out.println("Food Type (Appetizer / Main / Dessert) : ");
			item.setFoodItemType(s.nextLine());
			System.out.println("Description: ");
			item.setMealDescription(s.nextLine());
			item.setMealId(menuItemID);
			int id = AllData.foodList.indexOf(food);
			AllData.foodList.remove(id);
			AllData.foodList.add(item);	
			s.close();
		}
	}

	
	public void viewAllFoodItems() {
		List<OrderMenuItem> list = AllData.customerList.stream().flatMap(c -> c.getOrder().stream()).collect(Collectors.toList());
		OrderMenuItem data;
		for (int i = 0; i < list.size(); i++) {
			data = list.get(i);
			System.out.println("|- " + (i+1) + ". " + data.getName());
			System.out.println("|- Qty: " + data.getQuantity());
			System.out.println("|- Total: " + data.getTotal());
			System.out.println("|");
		}
	
	}
	
	public void createDrinkItem(DrinkItem menuItem) {
		AllData.drinkList.add(menuItem);
	}

	/**
	 * 
	 * @param menuItemID
	 */
	public void deleteDrinkItem(int menuItemID) {
		// TODO - implement ExecutiveChef.deleteMenuItem
		DrinkItem drink = AllData.drinkList.stream().filter(r1 -> r1.getMealId() == menuItemID).findFirst().orElse(null);
		if (drink != null) {
			int v = AllData.drinkList.indexOf(drink);
			AllData.drinkList.remove(v);
		} else {
			System.out.println("This ID does not exist!!");
		}
	}

	/**
	 * 
	 * @param menuItemID
	 */
	public void updateDrinkItem(Integer menuItemID) {
		DrinkItem food = AllData.drinkList.stream().filter(x -> x.getMealId() == menuItemID).findFirst().orElse(null);
		if( food != null) {
			Scanner s = new Scanner(System.in);
			DrinkItem item = new DrinkItem();
			System.out.println("Drink Name: ");
			item.setMealName(s.nextLine());
			System.out.println("Drink Description");
			item.setMealDescription(s.nextLine());
			System.out.println("Is Alcoholic (true /false) :");
			item.setAlcoholic(s.nextBoolean());
			System.out.println("Alcohol percentage (0 if non-alcoholic) :");
			item.setDrinkAlcoholPercent(s.nextDouble());
			item.setMealId(menuItemID);
			int id = AllData.drinkList.indexOf(food);
			AllData.drinkList.remove(id);
			AllData.drinkList.add(item);
			s.close();
		}
	}

	
	public void viewAllDrinkItems() {
		OrderMenuItem od = new OrderMenuItem();
		od.viewDrinkItems();;
	}

	public void viewReceivedOrders() {
		List<OrderMenuItem> pendingOrders = getPendingOrders();
		displayOrders(pendingOrders);	
	}

	private void displayOrders(List<OrderMenuItem> list) {
		int i;
		OrderMenuItem data;
		System.out.println("=============================");
		System.out.println("            Orders  ");
		System.out.println("=============================");
		for (i = 1; i <= list.size(); i++) {
			data = list.get(i - 1);
			System.out.println("|- " + i + ". " + data.getName());
			System.out.println("|- Qty: " + data.getQuantity());
			System.out.println("|- Status: " + data.getState());
			System.out.println("|");
		}
		
	}

	public void prepareOrder() {
		List<OrderMenuItem> pendingOrders = getPendingOrders();
		viewReceivedOrders();
		Scanner s = new Scanner(System.in);
		System.out.println("Select order to prepare: ");
		System.out.print("=: ");
		int i = Integer.parseInt(s.nextLine());
		if (i <= pendingOrders.size() && i > 0) {
			OrderMenuItem item = pendingOrders.get(i);
			List<OrderMenuItem> orders = AllData.customerList.get(item.getCustomerId()).getOrder();
			item.setState(OrderState.ORDER_PREPARED.getCurrentValue());
			orders.remove(i - 1);
			orders.set(i - 1, item);
			AllData.customerList.get(item.getCustomerId()).setOrder(orders);
		} else {
			System.out.println("Invalid Input");
		}

	}

	private List<OrderMenuItem> getPendingOrders() {
		return AllData.customerList.stream().flatMap(c -> c.getOrder().stream()).filter(x -> x.getState().equals(OrderState.ORDER_PREPARED.getCurrentValue())).collect(Collectors.toList());
	}

	private List<OrderMenuItem> getAllOrders() {
		return AllData.customerList.stream().flatMap(c -> c.getOrder().stream()).collect(Collectors.toList());
	}

	public void viewAllCustomerOrders() {
		displayOrders(getAllOrders());
	}
	
	
	
	

}