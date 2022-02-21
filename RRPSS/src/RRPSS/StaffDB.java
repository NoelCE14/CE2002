package RRPSS;

import java.io.*;
import java.util.*;

/**
 * Staff database used to retrieve, update, store and remove staff in the company.
 * Stores information of the staff into StaffDB.txt file in the database folder.
 * Prints the sale records according to the specified date.
 * @author Chua Chong Yih
 * @version 1.0
 * @since 2021-11-03
 */
public class StaffDB {

	/**
	 * List of all staff in the company.
	 */
	private ArrayList<Staff> staffList = new ArrayList<Staff>();

	/**
	 * Creates a new Staff database and creates a new StaffDB.txt file in the database folder 
	 * to store the information of all staff in the company.
	 * Updates the Staff list with the information in the text file.
	 * @throws IOException If an input or output exception occurred.
	 */
	public StaffDB() throws IOException{
        File f = new File("database" + File.separator + "StaffDB.txt");
		if (f.createNewFile()) 
			System.out.println("New StaffDB file created.");

		getFromStaffFile();
	}

	/**
	 * Reads the StaffDB.txt file and updates this database with the information in the text file.
	 * @throws IOException If an input or output exception occurred.
	 */
	public void getFromStaffFile() throws IOException{
		File f = new File("database" + File.separator + "StaffDB.txt");
		BufferedReader br = new BufferedReader(new FileReader(f));
		String st = null;
		br.readLine();
		while ((st = br.readLine()) != null){
			String[] str = st.split(",");
			staffList.add(new Staff(str[0], str[1], str[2], str[3]));
		}
		br.close();
    }

	/**
	 * Writes the list of Staff in this database into the StaffDB.txt file.
	 * @throws IOException If an input or output exception occurred.
	 */
	public void updateToStaffFile() throws IOException{
		File f = new File("database" + File.separator + "StaffDB.txt");
		PrintWriter writer = new PrintWriter(f);
		writer.println("Staff Name | Gender | Employee ID | Job Title");
		for (Staff staff : staffList){
			writer.println(staff.getName() + "," + staff.getGender().toString()+ "," + staff.getEmployeeID() + "," + staff.getJobTitle());
		}
		writer.close();
    }

	/**
	 * Searches this database for an entry with the corresponding staff ID.
	 * @param staffID the staff ID of the staff to be searched for.
	 * @return the instance of the matching staff.
	 */
	public Staff searchStaff(int staffID) {
		
		for (Staff staff : staffList) 
			if (staff.getEmployeeID() == staffID)
				return staff;

		return null;
	}

	/**
	 * Creates a new staff using the parameters and adds the new staff into this database.
	 * @param name 			the name of the staff to be added.
	 * @param gender 		the gender of the staff to be added.
	 * @param employeeID 	the employee ID of the staff to be added.
	 * @param jobTitle 		the job title of the staff to be added.
	 * @throws IOException If an input or output exception occurred.
	 */
	public void addStaff(String name, Gender gender, int employeeID, String jobTitle) throws IOException{
		

		Staff newStaff = new Staff(name, gender, employeeID, jobTitle);
		staffList.add(newStaff);

		System.out.println(name + "(" + employeeID + ") " + "successfully added!");

	}

	/**
	 * Removes a staff from the database using the parameter given. 
	 * Displays an error message if staff is not found.
	 * @param staffID the staff ID of the staff to be removed.
	 * @throws IOException If an input or output exception occurred.
	 */
	public void removeStaff(int staffID) throws IOException{
		
		Staff staff = searchStaff(staffID);

		if (staff != null) {
			staffList.remove(staff);
			System.out.println("Staff removed!");
			return;
		}

		System.out.println("Staff not found!");
		
	}

	/**
	 * Updates the staff in the database with the employeeID given. 
	 * Prompts user for name, staff ID, job title to update the entry.
	 * @param employeeID the employee ID of the staff to be updated.
	 * @param sc the scanner instance for input operations.
	 * @throws IOException If an input or output exception occurred.
	 */
	public void updateStaff(int employeeID, Scanner sc) throws IOException{
		
		Staff staff = searchStaff(employeeID);
		
		if (staff != null) {
			System.out.println(staff.getName()+" Staff ID: "+ staff.getEmployeeID()+"\n===============================");
			System.out.println("(1) Name");
			System.out.println("(2) Staff ID");
			System.out.println("(3) Job Title");
			System.out.printf("Choose option (Input any other key to exit): ");
			int input = sc.nextInt();
			if(input==1) {
				System.out.printf("Input new name: ");
				sc.nextLine();
				staff.setName(sc.nextLine());
			}
			else if(input == 2) {
				System.out.printf("Input new Staff ID: ");
				while(!sc.hasNextInt()) sc.next();
				staff.setEmployeeID(sc.nextInt());
			}
			else if(input==3) {
				System.out.printf("Input new Job Title: ");
				sc.nextLine();
				staff.setJobTitle(sc.nextLine());
			}
			else {return;}
			System.out.println("Staff Updated!");
			return;
		}

		System.out.println("Staff not found!");
	}

	/**
	 * Prints the list of staff in the database.
	 */
	public void printStaffList() {
		if (staffList.size() == 0) {
			System.out.println("Staff database is empty!");
			return;
		}

		for (Staff staff : staffList) {
			staff.displayStaffDetails();
		}
	}
	

}