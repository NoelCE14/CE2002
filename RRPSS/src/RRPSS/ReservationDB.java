package RRPSS;

import java.util.*;

import java.io.*;
import java.time.LocalDateTime;

/**
 * Reservation databse use to store, retrieve and update reservation List in place
 * Stores the reservation records into MemberDB.txt in the databse folder
 * @author Zhang Xuanye
 * @version 1.0
 * @since 2021-11-11
 */
public class ReservationDB {

    /**
     * List of reservation in this database.
     */
    private ArrayList<Reservation> revList = new ArrayList<>();

    /**
	 * Initialize Reservation Database and get reservation records from ReservationDB.txt
     * To store the records of reservation in ReservationDB.txt
     * To updata the records of reservation in the txt
     * @throws IOException If an input or output exception occurred.
     */
    public ReservationDB() throws IOException{
        getFromReservationFile();
    }

    /**
	 * Reads the ReservationDB.txt file and updates revervationList with the records in the txt file.
     * @throws IOException If an input or output exception occurred.
	 */
    public void getFromReservationFile() throws IOException{
        // URL path = ReservationDB.class.getResource("ReservationDB.txt");
        File f = new File("database"+ File.separator + "ReservationDB.txt");
        BufferedReader reader = new BufferedReader(new FileReader(f));
        String line = "";
        while ((line = reader.readLine()) != null){
            String[] str = line.split(",");
            if (str.length <= 1)
                continue;
            Staff s = new Staff(str[4], str[5], str[6], str[7]);
            Reservation r = new Reservation(str[0], str[1], str[2], str[3], s, str[8], str[9]);

            this.revList.add(r);

        }
        reader.close();
    }

    /**
	 * Writes the reservationList to ReservationDB.txt file
     * @throws IOException If an input or output exception occurred.
	 */
    public void updateToReservationFile() throws IOException{
        // URL path = ReservationDB.class.getResource("ReservationDB.txt");
        File f = new File("database"+ File.separator + "ReservationDB.txt");
        PrintWriter writer = new PrintWriter(f);
        writer.println("timeOfReservation | customerName | phoneNumber | pax | name | gender | employeeID | jobTitle | tableNumber | tableSize");
        for (Reservation r : revList){
            String info = r.getTimeofReservation().toString() + "," + r.getcustomerName() + "," + r.getphoneNumber() + "," + r.getpax();
            info +=  "," + r.getStaff().getName() + "," + r.getStaff().getGender() + "," + r.getStaff().getEmployeeID() + "," + r.getStaff().getJobTitle();
            info +=  "," + r.getTableNumber() + "," + r.getTableSize();
            writer.println(info);
        }
        writer.close();
    }

    /**
	 * Add a reservation into reservationList with the details of the reservation
     * Check this reservation whether already in the list
	 * @param timeOfReservation	The reserved time of reservation 
     * @param customerName	The name of the customer who request the reservation
     * @param phoneNumber	The phone number of the customer
     * @param pax	The number of customers to be serve
     * @param staff	The staff object who make the reservation for the customer
     * @param tableSize	The size of table to be reserved
     * @param tableNumber	The table number to be reserved
	 */
    public void addReservation(LocalDateTime timeOfReservation, String customerName, String phoneNumber, int pax, Staff staff, int tableSize, int tableNumber){
        Reservation r = null;
        if ((r = searchReservation(customerName)) != null){
            System.out.println("Customer " + r.getcustomerName() + " has already made a reservation.");
            r.displayReservationDetails();
            return;
        }
        r = new Reservation(timeOfReservation, customerName, phoneNumber, pax, staff, tableSize, tableNumber);
        this.revList.add(r);
        this.revList.sort(new Comparator<Reservation>() {
			
				@Override
				public int compare(Reservation o1, Reservation o2) {
					return o1.getTimeofReservation().compareTo(o2.getTimeofReservation());
			}
        });
        System.out.println("Successfully reserved for " + r.getcustomerName() + " at "+r.getTimeofReservation());
    }

    /**
	 * Searches the customer name in memberList.
	 * @param name	The name of customer for the reservation to be searched for
	 * @return		The reservation object	
	 */
    public Reservation searchReservation(String name){
        for (Reservation r : revList){
            if (r.getcustomerName().equals(name)){
                if (r.checkExpiry() == true){
                    r.displayReservationDetails();
                    System.out.println("Your reservation has expired. System will remove the reservation automatically.");
                    revList.remove(r);
                    return null;
                }
                return r;
            }
        }

        return null;
    }
    
    /**
	 * Confirm a reservation when customer come to eat and remove it
	 * @param name	The name of customer for the reservation
	 */
    public void confirmReservation(String name) {
    	for (Reservation r : revList){
            if (r.getcustomerName().equals(name)){
            	revList.remove(r);
            	return;
            }
        }
    }
    
    /**
	 * Remove a reservation from reservation List
	 * @param name	The name of customer for the reservation
     * @param input	Scanner to take request
	 */
    public int removeReservation(String name, Scanner input){
        
        Reservation r = searchReservation(name);
        if (r == null){
            System.out.println("Reservation not found.");
            return -1;
        }
        else{
        	System.out.println("==========================================  Reservation Details ==========================================");
    		System.out.printf("%-20s %-20s %-20s %s\n", "Name(Phone Number)", "Table Number(Pax)", "Time","Staff Name");
            r.displayReservationDetails();
            System.out.println("");
            System.out.printf("Confirm cancellation? (Y/N): ");
            input.nextLine();
            String i = input.next();
            if (i.equalsIgnoreCase("Y")){
                revList.remove(r);
                System.out.println("Reservation cancelled successfully!");
                return r.getTableNumber();
            }
            else{
                return -1;
            }
        }

        
    }

    /**
	 * print all the reservation records in reservation List
     * remove the expired one day reservation
	 */
    public void printReservationList(){
        System.out.println("==========================================  Reservation Details ==========================================");
		System.out.printf("%-11s %-20s %-25s %-16s %s\n", "Status","Name(Phone Number)", "Table Number(Pax)", "Time","Staff Name");
        ArrayList<Reservation> expList = new ArrayList<>();
        for (Reservation r : revList){            
            if (r.checkExpiryOneDay()){
                System.out.printf("Expired     ");
                expList.add(r);
            }
            else{
                System.out.printf("Valid       ");
            }
            r.displayReservationDetails();            
        }

        for (Reservation r : expList){
            revList.remove(r);
        }
        System.out.println("\nAutomatically removed all expired reservation. ");
    }

    /**
	 * getter for revList
     * @return revList
	 */
    public ArrayList<Reservation> getRevList(){
        return this.revList;
    }

    
}
