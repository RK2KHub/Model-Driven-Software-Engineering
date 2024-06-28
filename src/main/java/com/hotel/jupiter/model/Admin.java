package com.hotel.jupiter.model;

import java.util.List;
import java.util.stream.Collectors;

import com.hotel.jupiter.AllData;


public class Admin extends Account {

	public int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @param e11
	 * @param branchID
	 */
	public void assignGeneralManager(Employee manager) {
		List<Employee> list = AllData.employeeList.stream()
				.filter(x -> x.getRole().equalsIgnoreCase(AllData.ROLE_GENERAL_MANAGER)).collect(Collectors.toList());
		if (list.isEmpty()) {
			AllData.employeeList.add(manager);
		} else {
			System.out.println("General Manager Already exists");
		}
	}

	/**
	 * 
	 * @param chef
	 * @param branchID
	 */
	public void assignExecutiveChef(Employee chef) {
		List<Employee> list = AllData.employeeList.stream()
				.filter(x -> x.getRole().equalsIgnoreCase(AllData.ROLE_EXECUTIVE_CHEF)).collect(Collectors.toList());
		if (list.isEmpty()) {
			AllData.employeeList.add(chef);
		} else {
			System.out.println("Executive Chef Already exists");
		}

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
		Employee tmp = AllData.employeeList.stream().filter(ro -> (ro.getEmpID() == employee.getEmpID())).findFirst()
				.get();
		if (tmp == null) {
			AllData.employeeList.add(employee);
		} else {
			System.out.println("Duplicate Employee id");
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

	public void createRoom(Room r) {
		Room tmp = AllData.roomList.stream().filter(ro -> ro.getRoomId() == r.getRoomId()).findFirst().get();
		if (tmp == null) {
			AllData.roomList.add(r);
		} else {
			System.out.println("Duplicate Room id");
		}
	}

	/**
	 * 
	 * @param roomID
	 */
	public void deleteRoom(Integer roomID) {
		// TODO - implement Admin.deleteRoom
		Room room = AllData.roomList.stream().filter(r1 -> r1.getRoomId() == roomID).findFirst().orElse(null);
		if (room != null) {
			int v = AllData.roomList.indexOf(room);
			AllData.roomList.remove(v);
		} else {
			System.out.println("This ID does not exist!!");
		}

	}

	/**
	 * 
	 * @param roomID
	 */
	public void updateRoom(Integer roomID, Room r) {
		// TODO - implement Admin.updateRoom
		Room roo = AllData.roomList.stream().filter(r1 -> r1.getRoomId() == roomID).findFirst().orElse(null);
		if (roo != null) {
			r.setRoomId(roomID);
			int v = AllData.roomList.indexOf(roo);
			AllData.roomList.remove(v);
			AllData.roomList.add(r);
		} else {
			System.out.println("This ID does not exist!!");
		}

	}

	public void viewRooms() {
		Room r = new Room();
		r.viewRoom();
	}

	/**
	 * 
	 * @param roomID
	 *//*
		 * public void viewRoomDetails(String roomID) { // TODO - implement
		 * Admin.viewRoomDetails throw new UnsupportedOperationException(); }
		 */

	public void viewProfile(Integer id) {
		Admin c = AllData.adminList.get(id.intValue());
		System.out.println("Name	: " + c.getName());
		System.out.println("Email	: " + c.getEmail());
		System.out.println("Age	: " + c.getAge());
		System.out.println("Contact : " + c.getContact());

	}

	public void updateProfile(Integer id, Admin c) {
		Admin info = AllData.adminList.get(id.intValue());
		info.setAge(c.getAge());
		info.setContact(c.getContact());
		info.setName(c.getName());
		info.setEmail(c.getEmail());
		info.setId(id.intValue());
		AllData.adminList.remove(id.intValue());
		AllData.adminList.add(info);

	}

}