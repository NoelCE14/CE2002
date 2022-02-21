package RRPSS;

import java.util.*;
import java.time.*;
import java.io.*;
import java.time.format.DateTimeFormatter;

/**
 * Sales database used to retrieve, update and store entries of sales made.
 * Stores the sale records into SalesDB.txt file in the database folder.
 * Prints the sale records according to the specified date.
 * @author Chua Chong Yih
 * @version 1.0
 * @since 2021-11-03
 */
public class SalesRevenueDB {

	/**
	 * List of sale records in this database.
	 */
	private static ArrayList<Sales> salesList = new ArrayList<Sales>();

	/**
	 * Format of sale date in this database.
	 */
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	/**
	 * Creates a new Sales database and creates a new SalesDB.txt file in the database folder 
	 * to store the sales entry if one does not exist.
	 * Updates the Sales list with the information in the text file.
	 * @throws IOException If an input or output exception occurred.
	 */
	public SalesRevenueDB() throws IOException{
		File f = new File("database" + File.separator + "SalesDB.txt");
		if (f.createNewFile()) 
			System.out.println("New SalesDB file created.");
		
		getFromSalesFile();
	}

	/**
	 * Reads the SalesDB.txt file and updates this database with the information in the text file.
	 * @throws IOException If an input or output exception occurred.
	 */
	public void getFromSalesFile() throws IOException{
		salesList.clear();
		File f = new File("database" + File.separator + "SalesDB.txt");
		BufferedReader br = new BufferedReader(new FileReader(f));
		String st = null;
		br.readLine();
		while ((st = br.readLine()) != null){
			String[] str = st.split(",");
			salesList.add(new Sales(str[0], str[1], str[2], str[3]));
		}
		br.close();
    }

	/**
	 * Writes the list of Sales in this database into the SalesDB.txt file.
	 * @throws IOException If an input or output exception occurred.
	 */
	public void updateToSalesFile() throws IOException{
		File f = new File("database" + File.separator + "SalesDB.txt");
		PrintWriter writer = new PrintWriter(f);
		writer.println("Invoice Number | Staff Name | Total Sales | Date Time");
		for (Sales sales : salesList){
			String info = sales.getInvoiceNumber() + "," + sales.getStaffName() + "," + sales.getRevenue() + "," + sales.getSaleDate();
			writer.println(info);
		}
		writer.close();
    }

	/**
	 * Creates a new Sales entry with the Order provided and stores in this database.
	 * @param newSale the new order that has been confirmed and paid that will be stored in this database.
	 */
	public void addSales(Order newSale, int invoiceNumber){
		if (newSale == null) {
			System.out.println("Invalid order being stored.");
			return;
		}
		
		Sales sale;
		if (newSale.getMember()) 
			sale = new Sales(String.valueOf(invoiceNumber), String.valueOf(newSale.getStaffName()), String.valueOf(newSale.getTotalPrice() * 1.17 * 0.85), newSale.getOrderDateTime().toString());
		else
			sale = new Sales(String.valueOf(invoiceNumber), String.valueOf(newSale.getStaffName()), String.valueOf(newSale.getTotalPrice() * 1.17), newSale.getOrderDateTime().toString());

		salesList.add(sale);
	}

	/**
	 * Searches this database for a matching invoice number to create a unique entry.
	 * @param invoiceNumber the invoice number to be searched.
	 * @return true if invoice number is found and false otherwise.
	 */
	public boolean searchInvoice(int invoiceNumber) {
		for (Sales sales : salesList) {
			if (sales.getInvoiceNumber().equals(Integer.toString(invoiceNumber)))
				return true;
		}

		return false;
	}

	/**
	 * Prints all sales on the month specified by the parameter
	 * and prints the total revenue generated from all sales from the same month.
	 * @param month the month of revenue to be printed.
	 */
	public void printRevenue(int month){
		
		float totalRevenue = 0;
		LocalDateTime saleDate;

		System.out.printf("============Revenue for %d-%02d============\n", LocalDateTime.now().getYear(), month);
		System.out.println("Invoice\t\tSales\t\tDate");
		for (Sales s : salesList) {
			saleDate = LocalDateTime.parse(s.getSaleDate());
			
			if (saleDate.getMonthValue() == month) {
				System.out.printf("%-15s $%-14.2f %s\n", s.getInvoiceNumber(), Double.parseDouble(s.getRevenue()), saleDate.format(formatter).toString());
				totalRevenue += Float.parseFloat(s.getRevenue());
			}
		}

		System.out.println();
		System.out.printf("Total revenue for %d-%d is $%.2f\n\n", month, LocalDateTime.now().getYear(), totalRevenue);
	}

	/**
	 * Prints all sales on the day and month specified by the parameter 
	 * and prints the total revenue generated from all sales from the same day and month.
	 * @param day the day of revenue to be printed.
	 * @param month the month of revenue to be printed.
	 */
	public void printRevenue(int day, int month){

		float totalRevenue = 0;
		LocalDateTime saleDate;

		System.out.printf("===========Revenue for %d-%02d-%02d===========\n", LocalDateTime.now().getYear(), month, day);
		System.out.println("Invoice\t\tSales\t\tDate");
		for (Sales s : salesList) {
			saleDate = LocalDateTime.parse(s.getSaleDate());
			if (saleDate.getDayOfMonth() == day && saleDate.getMonthValue() == month) {
				System.out.printf("%-15s $%-14.2f %s\n", s.getInvoiceNumber(), Double.parseDouble(s.getRevenue()), saleDate.format(formatter).toString());
				totalRevenue += Float.parseFloat(s.getRevenue());
			}
		}

		System.out.println();
		System.out.printf("Total revenue for %d-%d-%d is $%.2f\n\n", LocalDateTime.now().getYear(), month, day, totalRevenue);
	}

}