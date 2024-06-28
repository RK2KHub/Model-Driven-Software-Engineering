package com.hotel.jupiter.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.hotel.jupiter.AllData;


public class OrderMenuItem {

	private Integer orderId;
	private Integer quantity;
	private String name;
	private Integer total;
	private String state;
	private Integer customerId;

	
	public void viewFoodItems() {
		int i;
		FoodItem data;
		for (i = 1; i <= AllData.foodList.size(); i++) {
			data = AllData.foodList.get(i - 1);
			System.out.println("|- " + i + ". " + data.getMealName());
			System.out.println("|- Type: " + data.getFoodItemType());
			System.out.println("|- Description: " + data.getMealDescription());
			System.out.println("|");
		}
	}

	public void viewDrinkItems() {
		int i;
		DrinkItem data;
		for (i = 1; i <= AllData.drinkList.size(); i++) {
			data = AllData.drinkList.get(i - 1);
			System.out.println("|- " + i + ". " + data.getMealName());
			System.out.println("|- Type: " + (data.isAlcoholic() ? "Alcoholic" : "Non-Alcoholic"));
			System.out.println("|- Description: " + data.getMealDescription());
			System.out.println("|");

		}
	}

	public void orderFoodItems(Integer id) {
		Customer customer = AllData.customerList.stream().filter(c -> c.getCustomerId() == id).findFirst().get();
		boolean status = (customer.getReservation() != null && customer.getReservation().getCheckinManagement() != null)
				? customer.getReservation().getCheckinManagement().getCheckIn()
				: false;
		if (status) {
			List<OrderMenuItem> list = customer.getOrder();
			Scanner s = new Scanner(System.in);
			System.out.println("Please enter your choice of food: ");
			System.out.print("=: ");
			int i = Integer.parseInt(s.nextLine());
			OrderMenuItem od = new OrderMenuItem();
			if (list == null)
				list = new ArrayList<>();
			int size = (list != null) ? list.size() : 0;
			if (i < AllData.foodList.size() && i > 0) {
				FoodItem data = AllData.foodList.get(i - 1);
				od.setOrderId(size);
				od.setQuantity(1);
				od.setTotal(0);
				od.setName(data.getMealName());
				od.setState(OrderState.ORDER_RECEIVED.getCurrentValue());
				od.setCustomerId(id);
				list.add(od);
				AllData.customerList.get(id).setOrder(list);
				System.out.println("Food ordered successfully");
			} else {
				System.out.println("Invalid Input");
			}
		} else {
			System.out.println("Please checkin to order food");
		}
	}

	public void orderDrinkItems(Integer id) {
		Scanner s = new Scanner(System.in);
		System.out.println("Please enter your choice of drink: ");
		System.out.print("=: ");
		int i = Integer.parseInt(s.nextLine());
		List<OrderMenuItem> list = AllData.customerList.get(id).getOrder();
		OrderMenuItem od = new OrderMenuItem();
		if (list == null)
			list = new ArrayList<>();
		int size = (list != null) ? list.size() : 0;
		if (i < AllData.drinkList.size() && i > 0) {
			DrinkItem data = AllData.drinkList.get(i - 1);
			od.setOrderId(size);
			od.setQuantity(1);
			od.setTotal(0);
			od.setName(data.getMealName());
			od.setState(OrderState.ORDER_RECEIVED.getCurrentValue());
			od.setCustomerId(id);
			list.add(od);
			AllData.customerList.get(id).setOrder(list);
			System.out.println("Drink ordered successfully");
		} else {
			System.out.println("Invalid Input");
		}

	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	
	public void cancelOrder(Integer id) {
		Customer cu = AllData.customerList.get(id);
		List<OrderMenuItem> list = cu.getOrder();
		cu.viewOrders(list);
		Scanner s = new Scanner(System.in);
		System.out.println("Please enter your choice of order to cancel: ");
		System.out.print("=: ");
		int i = Integer.parseInt(s.nextLine());
		if(i>=1 && i<=list.size()) {
			OrderMenuItem od = list.get(i-1);
			if(od.getState().equals(OrderState.ORDER_RECEIVED.getCurrentValue())) {
				od.setState(OrderState.ORDER_CANCELLED.getCurrentValue());
			}else {
				System.out.println("Sorry unable to cancel order.");
			}
		}else {
			System.out.println("Invalid choice: choice is out of range");
		}
		
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

}
