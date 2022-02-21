package RRPSS;

import java.util.*;
import java.time.*;

/**
 * Contains the information of order created by the system that is
 * stored in the order database. Each order contains a list of 
 * menu items and promotional items.
 * @author Chua Chong Yih
 * @version 1.0
 * @since 2021-11-03
 */
public class Order {

	/**
	 * List of menu items in this order.
	 */
	private ArrayList<MenuItem> orderList = new ArrayList<MenuItem>();
	
	/**
	 * The table assigned to this order.
	 */
	private int tableNum;

	/**
	 * The order number of this order.
	 */
	private int orderNumber;

	/**
	 * The number of customers in this order.
	 */
	private int pax;

	/**
	 * The staff assigned to this order.
	 */
	private String staffName;

	/**
	 * Membership status of the customer of this order.
	 */
	private boolean member;

	/**
	 * The total price of this order.
	 */
	private double totalPrice;

	/**
	 * The Date and time that this order was created.
	 */
	private LocalDateTime orderDateTime;
	
	/**
	 * Creates a new order with the given parameter. Sets the LocalDateTime
	 * as the time this order was created.
	 * @param staffName 	The name of the staff assigned to this order.
	 * @param member 		The membership status of the customer of this order.
	 * @param pax 			The number of customers in this order.
	 * @param orderNumber 	The order number of this order assigned by the database
	 * @param table 		The table assigned to this order.
	 */
	public Order(String staffName, boolean member, int pax, int orderNumber, int tableNum) {
		this.staffName = staffName;
		this.member = member;
		this.pax = pax;
		this.orderNumber = orderNumber;
		this.tableNum = tableNum;
		orderDateTime = LocalDateTime.now();
	}

	
	/**
	 * Creates a new order with the given parameters in String format
     * to be converted into the corresponding data types.
	 * @param staffName 	The name of the staff assigned to this order.
	 * @param member 		The membership status of the customer of this order.
	 * @param pax 			The number of customers in this order.
	 * @param orderNumber 	The order number of this order assigned by the database
	 * @param table 		The table assigned to this order.
	 */
	public Order(String staffName, String member, String pax, String orderNumber, String tableNum) {
		this.staffName = staffName;
		this.member = Boolean.parseBoolean(member);
		this.pax = Integer.parseInt(pax);
		this.orderNumber = Integer.parseInt(orderNumber);
		this.tableNum = Integer.parseInt(tableNum);
	}

	/**
	 * Adds a new menu item into the orderItem list.
	 * Sorts the orderItem list in order of the name of item.
	 * Adds the price of the menu item to the total price of this order.
	 * @param m The menu item to be added to the orderItem list.
	 */
	public void addOrderItem(MenuItem m) {
		orderList.add(m);

		orderList.sort(new Comparator<MenuItem>() {
			
			@Override
			public int compare(MenuItem o1, MenuItem o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		
		totalPrice += m.getPrice();
	}

	/**
	 * Removes the menu item from the orderItem list.
	 * Deducts the price of the menu item from the price of this order.
	 * @param m The menu item to be removed from the orderItem list.
	 */
	public void removeOrderItem(MenuItem m) {

		if (m != null) {
			int check = findItem(m);
			if(check!=-1) {
				orderList.remove(check);
				totalPrice -= m.getPrice();
				System.out.println("Item removed!");
				return;
			}
		}

		System.out.println("Item not found");

	}
	
	/**
	 * Search the order list for the matching menu item.
	 * @param m the menu item to be searched.
	 * @return the index of the menu item in the order list.
	 */
	private int findItem(MenuItem m) {
		for(int x=0;x<orderList.size();x++) {
			if(orderList.get(x).getName().equals(m.getName()))
				return x;
		}
		return -1;
	}
	

	/**
	 * Finds all unique classes that are in the menu arraylist and stores it in an ArrayList.
	 * @return ArrayList of classes that are in the menu.
	 */
	public ArrayList<Class<?>> getAllClasses() {
        ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
        for (MenuItem menuItem : orderList) {
            if (!classes.contains(menuItem.getClass()))
                classes.add(menuItem.getClass());
        }
		
        return classes;
    }

	/**
	 * Prints the list of menu and promotional items in this order.
	 * Prices listed are inclusive of GST.
	 * Members are entitled to 15% discount from the final price.
	 */
	public void printOrderList() {

		System.out.println("Table Number: " + tableNum);
		System.out.println("\nOrder Details\n=========================================");
		
		ArrayList<Class<?>> menuClasses = new ArrayList<Class<?>>();
		menuClasses = getAllClasses();

		if(orderList.size() != 0) {
			for (Class<?> distinctMenuClass : menuClasses) {
				System.out.println("Ordered " + distinctMenuClass.getSimpleName() + " Items: ");
				System.out.println("Item\tName\t\t\tPrice");
				
				int index = 1;
				for (MenuItem m : orderList) {	
					if (m.getClass() == distinctMenuClass)
						System.out.printf("%-7d %-23s $%.2f\n", index++, m.getName(), m.getPrice());
				}

				System.out.println();
			}
		
		}

		System.out.printf("Total Price (inclusive of GST): $%.2f\n\n", ((totalPrice *1.1)*1.07));
		if (member) {
			System.out.printf("Member Price (inclusive of GST): $%.2f\n\n", (((totalPrice * 1.1)*1.07)) * 0.85);
		}

	}

	/**
	 * Prints the list of menu and promotional items in this order.
	 * List of menu and promotional items are consolidated such that the same
	 * types of items are displayed in a single row with the amount of repeated items.
	 * Prices listed are inclusive of GST.
	 * Members are entitled to 15% discount from the final price.
	 */
	public void printOrderDetails() {
		int counter;

		System.out.println("Table Number: " + tableNum);
		System.out.println("\nOrder Details\n=========================================");
		
		ArrayList<Class<?>> menuClasses = new ArrayList<Class<?>>();
		menuClasses = getAllClasses();

		if(orderList.size() != 0) {
			for (Class<?> distinctMenuClass : menuClasses) {
				counter = 1;
				System.out.println("Ordered " + distinctMenuClass.getSimpleName() + " Items: ");
				System.out.println("Amount\tName\t\t\tPrice");
				
				for (int i = 0; i < orderList.size(); i++) {

					if (orderList.get(i).getClass() == distinctMenuClass) {
						if (i != orderList.size() - 1)
							if (orderList.get(i).equals(orderList.get(i+1))) {
								counter++;
								if (i != orderList.size() - 1)
                                    continue;
							}
						
						System.out.printf("%-7d %-23s $%.2f\n", counter, orderList.get(i).getName(), orderList.get(i).getPrice() * counter);
						counter = 1;
					}
				}

				System.out.println();
			}
		}
		
		System.out.printf("Total Price(inclusive of GST): $%.2f\n", ((totalPrice *1.1)*1.07));
		if (member) {
			System.out.printf("Member Price(inclusive of GST): $%.2f\n\n", (((totalPrice * 1.1)*1.07)) * 0.85);
		}
	}

	/**
	 * Gets the list of menu items in this order.
	 * @return the list of menu items.
	 */
	public ArrayList<MenuItem> getOrderList() {
		return orderList;
	}

	/**
	 * Gets the staff name in charge of this order.
	 * @return the staff name in charge of this order.
	 */
	public String getStaffName() {
		return this.staffName;
	}

	/**
	 * Changes the staff name in charge of this order.
	 * @param staffName the new staff name to be updated.
	 */
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	/**
	 * Gets the date and time that this order was created.
	 * @return the date and time in LocalDateTime format.
	 */
	public LocalDateTime getOrderDateTime() {
		return orderDateTime;
	}

	/**
	 * Changes the date and time that this order was created.
	 * @param dateTime the new date and time for this order.
	 */
	public void setOrderDateTime(LocalDateTime orderDateTime) {
		this.orderDateTime = orderDateTime;
	}

	/**
	 * Gets the membership status of the customer of this order.
	 * @return true if the customer is a member and false otherwise.
	 */
	public boolean getMember() {
		return this.member;
	}

	/**
	 * Changes the membership status of the customer of this order.
	 * @param member the new membership status of the customer of this order.
	 */
	public void setMember(boolean member) {
		this.member = member;
	}

	/**
	 * Gets the table assigned to this order.
	 * @return the table number assigned to this order.
	 */
	public int getTableNum() {
		return this.tableNum;
	}

	/**
	 * Changes the table number assigned to this order.
	 * @param tableNum the new table number assigned to this order.
	 */
	public void setTableNum(int tableNum) {
		this.tableNum = tableNum;
	}

	/**
	 * Gets the number of customers of this order.
	 * @return the number of customers of this order.
	 */
	public int getPax() {
		return this.pax;
	}

	/**
	 * Changes the number of customers of this order.
	 * @param pax the new number of customers of this order.
	 */
	public void setPax(int pax) {
		this.pax = pax;
	}

	/**
	 * Gets the order number of this order.
	 * @return the order number of this order.
	 */
	public int getOrderNumber() {
		return orderNumber;
	}

	/**
	 * Gets the total price of this order without GST.
	 * @return the total price of this order without GST.
	 */
	public double getTotalPrice() {
		return totalPrice;
	}
}