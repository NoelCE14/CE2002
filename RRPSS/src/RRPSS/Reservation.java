package RRPSS;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Contains the info of reservation 
 * stored in the reservation database.
 * @author Zhang Xuanye
 * @version 1.0
 * @since 2021-11-11
 */
public class Reservation {

    /**
	 * set a const expire time to 15 mins
	 */
    private final static int EXPIRETIME = 15;

    /**
	 * The time of reservation
	 */
    private LocalDateTime timeOfReservation;

    /**
	 * The name of customer
	 */
    private String customerName;

    /**
	 * The phoneNumber of customer 
	 */
    private String phoneNumber;

    /**
	 * The number of customers to be serve
	 */
    private int pax;

    /**
	 * The staff object who make the reservation for the customer
	 */
    private Staff staff;

    /**
	 * The size of table to be reserved
	 */
    private int tableSize;

    /**
	 * The table number to be reserved
	 */
    private int tableNumber;
    
    /**
     * The DateTimeFormatter for date and time.
     */
    private DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("yyyy-MM-dd | HH:mm");

    /**
	 * Create a new reservation with the given para.
	 * @param timeOfReservation	The reserved time of reservation 
     * @param customerName	The name of the customer who request the reservation
     * @param phoneNumber	The phone number of the customer
     * @param pax	The number of customers to be serve
     * @param staff	The staff object who make the reservation for the customer
     * @param tableSize	The size of table to be reserved
     * @param tableNumber	The table number to be reserved
	 */
    public Reservation (LocalDateTime timeOfReservation, String customerName, String phoneNumber, int pax, Staff staff, int tableSize, int tableNumber){
        this.timeOfReservation = timeOfReservation;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.pax = pax;
        this.staff = staff;
        this.tableNumber = tableNumber;
        this.tableSize = tableSize;
    }

    /**
	 * Create a new reservation with the given para.
     * change timeOfReservation from string to LocalDateTime, pax from string to int, tableNumber from string to int, tableSize from string to int,
	 * @param timeOfReservation	The reserved time of reservation 
     * @param customerName	The name of the customer who request the reservation
     * @param phoneNumber	The phone number of the customer
     * @param pax	The number of customers to be serve
     * @param staff	The staff object who make the reservation for the customer
     * @param tableSize	The size of table to be reserved
     * @param tableNumber	The table number to be reserved
	 */
    public Reservation (String timeOfReservation, String customerName, String phoneNumber, String pax, Staff staff, String tableSize, String tableNumber){
        this.timeOfReservation = LocalDateTime.parse(timeOfReservation);
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.pax = Integer.parseInt(pax);
        this.staff = staff;
        this.tableNumber = Integer.parseInt(tableNumber);
        this.tableSize = Integer.parseInt(tableSize);
    }

    /**
	 * print the reservation details 
	 */
    public void displayReservationDetails(){
    	System.out.printf("%-25s %-14s %-22s %s\n",this.customerName+"("+this.phoneNumber+")",this.tableNumber+"("+this.pax+")",this.timeOfReservation.format(formatterTime),this.staff.getName());
    }

    /**
	 * check whether the reservation expire or not
     * @return whether the reservation expire or not
	 */
    public boolean checkExpiry(){
        LocalDateTime t = timeOfReservation.plusMinutes(EXPIRETIME);
        LocalDateTime currentTime = LocalDateTime.now();
        if (t.compareTo(currentTime) < 0){
            return true;
        }
        else
            return false;
    }

    /**
	 * check whether the reservation expire one day or not
     * @return whether the reservation expire one day or not
	 */
    public boolean checkExpiryOneDay(){
        LocalDateTime t = timeOfReservation.plusDays(1);
        LocalDateTime currentTime = LocalDateTime.now();
        if (t.compareTo(currentTime) < 0){
            return true;
        }
        else
            return false;
    }

    /**
	 * getter for reservation Time
     * @return timeOfReservation
	 */
    public LocalDateTime getTimeofReservation(){
        return this.timeOfReservation;
    }

    /**
	 * setter for reservation Time
	 */
    public void setTimeofReservation(LocalDateTime timeOfReservation){
        this.timeOfReservation = timeOfReservation;
    }

    /**
	 * getter for the name of customer
     * @return customerName
	 */
    public String getcustomerName(){
        return this.customerName;
    }

    /**
	 * setter for the name of customer
	 */
    public void setcustomerName(String customerName){
        this.customerName = customerName;
    }

    /**
	 * getter for the phonenumebr of the customer
     * @return phoneNumber
	 */
    public String getphoneNumber(){
        return this.phoneNumber;
    }

    /**
	 * setter for the phonenumebr of the customer
	 */
    public void setphoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    /**
	 * getter for The number of customers to be serve
     * @return pax
	 */
    public int getpax(){
        return this.pax;
    }

    /**
	 * setter for The number of customers to be serve
	 */
    public void setpax(int pax){
        this.pax = pax;
    }

    /**
	 * getter for The staff object who make the reservation for the customer
     * @return staff
	 */
    public Staff getStaff(){
        return this.staff;
    }

    /**
	 * setter for The staff object who make the reservation for the customer
	 */
    public void setStaff(Staff staff){
        this.staff = staff;
    }

    /**
	 * getter for The table number to be reserved
     * @return tableNumber
	 */
    public int getTableNumber(){
        return this.tableNumber;
    }

    /**
	 * getter for The table number to be reserved
	 */
    public void setTableNumber(int tableNumber){
        this.tableNumber = tableNumber;
    }

    /**
	 * getter for The table number to be reserved
     * @return tableSzie
	 */
    public int getTableSize(){
        return this.tableNumber;
    }

    /**
	 * setter for The table number to be reserved
	 */
    public void setTableSize(int tableSize){
        this.tableSize = tableSize;
    }
    

    

}
