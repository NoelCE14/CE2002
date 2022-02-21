package RRPSS;

/**
 * Contains the record of sales made that will be stored in the SalesRevenueDB.
 * @author Chua Chong Yih
 * @version 1.0
 * @since 2021-11-03
 */
public class Sales {

    /**
     * The invoice number used to identify this sale.
     */
    private String invoiceNumber;

    /**
     * The name of staff that made this sale.
     */
    private String staffName;

    /**
     * The total revenue generated from this sale.
     */
    private String revenue;

    /**
     * The date that this sale was made.
     */
    private String saleDate;

    /**
     * Creates a new Sale entry with the given information.
     * The saleDate should be in LocalDateTime String format.
     * @param invoiceNumber This Sale's invoice number.
     * @param staffName     This Sale's staff in charge.
     * @param totalPrice    This Sale's total price revenue generated.
     * @param saleDate      This Sale's date of purchase.
     */
    public Sales(String invoiceNumber, String staffName, String totalPrice, String saleDate) {
        this.invoiceNumber = invoiceNumber;
        this.staffName = staffName;
        this.revenue = totalPrice;
        this.saleDate = saleDate;
    }

    /**
     * Gets the invoice number of this Sale.
     * @return this Sale's invoice number.
     */
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    /**
     * Gets the name of staff in charge of this Sale.
     * @return  the name of the staff that made this Sale.
     */
    public String getStaffName() {
        return staffName;
    }

    /**
     * Gets the total sale revenue from this Sale.
     * @return this Sale's revenue.
     */
    public String getRevenue() {
        return revenue;
    }

    /**
     * Gets the date that this sale was made.
     * @return this Sale's date of purchase.
     */
    public String getSaleDate() {
        return saleDate;
    }

}
