package RRPSS;

import java.util.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Order database used to retrieve, update and store entries of orders currently in place.
 * Stores the order records into OrderDB.txt file in the database folder.
 * Confirms the order which will release the table and print out the invoice and add to the sales database.
 * @author Chua Chong Yih
 * @version 1.0
 * @since 2021-11-03
 */
public class OrderDB {
    
    /**
     * List of orders in this database.
     */
    private ArrayList<Order> orderList = new ArrayList<Order>();

    /**
     * The current order number to be assigned to a new order.
     */
    private int orderNumber;

    /**
     * Format of date and time in this database.
     */
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
	 * Creates a new Order database and creates a new OrderDB.txt file in the database folder 
	 * to store the sales entry if one does not exist.
	 * Updates the Sales list with the information in the text file.
     * Sets the initial order number to be 10000.
     * @throws IOException If an input or output exception occurred.
     */
    public OrderDB() throws IOException{
        orderNumber = 10000;
		getFromOrderFile();
    }

    /**
	 * Reads the OrderDB.txt file and updates this database with the information in the text file.
	 * @throws IOException If an input or output exception occurred.
	 */
    public void getFromOrderFile() throws IOException{
        File f = new File("database" + File.separator + "OrderDB.txt");
        BufferedReader br = new BufferedReader(new FileReader(f));
        String st = null;
        br.readLine();
        while ((st = br.readLine()) != null){
            String[] str = st.split(",");
            if(str.length <= 1)
                continue;
            Order o = new Order(str[1], str[2], str[3], str[4], str[0]);
            o.setOrderDateTime(LocalDateTime.parse(str[5]));
            String[] substr = str[6].split("/");
            MenuDB menu = new MenuDB();
            if (substr.length != 0 && !substr[0].equals("")) {
                for (String menuName : substr) {
                    o.addOrderItem(menu.searchMenu(menuName));
                }
            }
            orderList.add(o);
            this.orderNumber = Integer.parseInt(str[4]) + 1;
        }
        br.close();
    }

    /**
	 * Writes the list of Orders in this database into the OrderDB.txt file.
	 * @throws IOException If an input or output exception occurred.
	 */
	public void updateToOrderFile() throws IOException{
        File f = new File("database" + File.separator + "OrderDB.txt");
        PrintWriter writer = new PrintWriter(f);
        writer.println("Table Number | Staff Name | member | pax | orderNumber | localDateTime | orderList");
        for (Order order : orderList){
            writer.print(order.getTableNum() + "," 
                            + order.getStaffName() + "," + order.getMember() + "," + order.getPax() + "," + order.getOrderNumber() + "," 
                            + order.getOrderDateTime() + ",");
            
            if (order.getOrderList().size() == 0)
                writer.print("/");
            else {
		        for (MenuItem menuItem : order.getOrderList()) {
		            writer.print(menuItem.getName() + "/");
		        }
            }
            
            writer.print(",");

            writer.println();
        }
        writer.close();
    }

    /**
     * Searches this database for the order with the corresponding order number.
     * @param orderNumber The order number to be searched.
     * @return the Order if found and null otherwise.
     */
    public Order searchOrder(int orderNumber) {
        for (Order order : orderList) {
            if (order.getOrderNumber() == orderNumber)
                return order;
        }

        return null;
    }

    /**
     * Creates a new order with the given parameters and adds into this database.
	 * @param staffName     The name of the staff assigned to this order.
	 * @param member        The membership status of the customer of this order.
	 * @param pax           The number of customers in this order.
	 * @param orderNumber   The order number of this order assigned by the database
	 * @param table         The table assigned to this order.
     * @return the order number of the order created.
     */
    public int createOrder(String staffName, boolean member, int pax, int tableNum) {
        Order o = new Order(staffName, member, pax, orderNumber++, tableNum);
        orderList.add(o);
        return orderNumber - 1;
    }

    /**
     * Removes the order number specified by the parameter.
     * Prints an error message if order cannot be found.
     */
    public void removeOrder(int orderNumber) {
		if (searchOrder(orderNumber) != null) {
			orderList.remove(searchOrder(orderNumber));
			System.out.println("Order removed!");
			return;
		}

		System.out.println("Order not found!");
    }

    /**
     * List the orders in this database with only its order index, date time and order number to be viewed.
     */
    public void viewOrder() {
        if (orderList.size() == 0) {
            System.out.println("No order in database!");
            return;
        }
        for (Order o : orderList)
            System.out.println("[" + (orderList.indexOf(o) + 1) + "] " + o.getOrderDateTime().format(formatter) + "\tOrder Number: " + o.getOrderNumber());
    }

    /**
     * Confirms the order in this database when the order is completed 
     * and to release the table, print the invoice and add the order to
     * the sales database.
     * @param orderNumber The order number of the order that has been confirmed.
     */
    public void confirmOrder(int orderNumber){
        try {
            SalesRevenueDB salesDB = new SalesRevenueDB();
            int invoiceNumber = (int) (Math.random() * 100000 + 1);
            while (salesDB.searchInvoice(invoiceNumber))
                invoiceNumber = (int) (Math.random() * 100000 + 1);
            new Invoice(searchOrder(orderNumber), invoiceNumber);
            salesDB.addSales(searchOrder(orderNumber), invoiceNumber);
        } catch(IOException io) {
            io.printStackTrace();
            System.out.println("Invoice was not printed");
        }
    }

}