package RRPSS;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.util.*;

/**
 * Invoice class is reponsible for printing the invoice of a order into 
 * a text file in the invoice folder with the invoice number as the file name.
 * @author Chua Chong Yih
 * @version 1.0
 * @since 2021-11-03
 */
public class Invoice {

    /**
     * The unique invoice number of this invoice.
     */
    private int invoiceNumber;

    /**
     * The DateTimeFormatter for date and time.
     */
    private DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");

    /**
     * Creates a new invoice file with the invoice number as the file name.
     * @param o The order to be printed.
     * @throws IOException If an input or output exception occurred.
     */
    public Invoice(Order o, int invoiceNumber) throws IOException{
        this.invoiceNumber = invoiceNumber;

        File f = new File("invoice" + File.separator + invoiceNumber + ".txt");
        if (f.createNewFile())
            System.out.println("Invoice printed at " + f.getName());

        printInvoice(o);

    }

    /**
     * Prints the invoice of this order by writing to the invoice text file.
     * The price shown includes the subtotal price, GST price,
     * membership discount if applicable and the total price.
     * @param o The order to be printed.
     * @throws IOException If an input or output exception occurred.
     */
    private void printInvoice(Order o) throws IOException{

        PrintWriter writer = new PrintWriter("invoice"+ File.separator + invoiceNumber + ".txt");
        writer.printf("               _..----.._       \n");
        writer.printf("             .'     o    '.     \n");
        writer.printf("            /   o       o  \\    \n");
        writer.printf("           |o        o     o|   \n");
        writer.printf("           /'-.._o     __.-'\\   \n");
        writer.printf("           \\      `````     /   \n");
        writer.printf("           |``--........--'`|    \n");
        writer.printf("            \\              /\n");
        writer.printf("             `'----------'`\n");
        writer.println("            Bad Street Kitchen");
        writer.println("            10 BayFront Avenue");
        writer.println("            Tel: +65 6688 5665");
        writer.println("           Invoice Number: " + invoiceNumber);
        writer.printf("Server: %-15sDate: %s\n", o.getStaffName(),LocalDateTime.now().format(formatterDate));
        writer.printf("Table: %-16sTime: %s\n",o.getTableNum(), LocalDateTime.now().format(formatterTime));
        
        int counter;
        writer.println("\nOrder Details\n=========================================");

        ArrayList<Class<?>> menuClasses = new ArrayList<Class<?>>();
		menuClasses = o.getAllClasses();

        if(o.getOrderList().size() != 0) {
            for (Class<?> distinctMenuClass : menuClasses) {
                counter = 1;
                writer.println("Ordered " + distinctMenuClass.getSimpleName() + " items: ");
                writer.printf("%-7s %-23s %s\n", "Amount", "Name", "Price");
                
                for (int i = 0; i < o.getOrderList().size(); i++) {
                        if (o.getOrderList().get(i).getClass() == distinctMenuClass) {
                            if (i != o.getOrderList().size() - 1)
                                if (o.getOrderList().get(i).equals(o.getOrderList().get(i+1))) {
                                    counter++;
                                    if (i != o.getOrderList().size() - 1)
                                        continue;
                                }
                        
                        writer.printf("%-7d %-23s $%.2f\n", counter, o.getOrderList().get(i).getName(), o.getOrderList().get(i).getPrice() * counter);
                        counter = 1;
                    }
                }

                writer.println();
            }
        
        }
        double totalPrice = o.getTotalPrice();
        writer.println("=========================================");
        writer.printf("SubTotal:                       $%.2f\n", totalPrice);
        writer.printf("10%% Service Charge:             $%.2f\n", totalPrice * 0.1);
        writer.printf("7%% GST:                         $%.2f\n", totalPrice*1.1 * 0.07);
        if (o.getMember()) {
            writer.printf("15%% Membership Discount:       -$%.2f\n", totalPrice *1.1*1.07* 0.15);
            totalPrice = totalPrice * 0.85 *1.1*1.07;
            writer.printf("Total (inclusive of GST):       $%.2f\n\n", totalPrice);
        }
        else {
        	totalPrice = totalPrice * 1.1 * 1.07;
        	writer.printf("Total (inclusive of GST):       $%.2f\n\n", totalPrice);
        }
        writer.close();
        
    }
    
}