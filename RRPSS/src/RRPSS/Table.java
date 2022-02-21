package RRPSS;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Contains the information of table that will be used in TableDB
 * @author Kua Hao Ren
 * @version 1.0
 * @since 2021-11-03
 */
public class Table 
{
	/**
	 * The status of this table. It is set to AVAILABLE upon initialization.
	 */
    private TableStatus tableStatus = TableStatus.AVAILABLE;
    
    /**
     * The table number of this table.
     */
    private int tableNumber;
    
    /**
     * The table size of this table.
     */
    private int tableSize;
    
    /**
     * The DateTimeFormatter for date and time in hours and minutes.
     */
    private DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("yyyy-MM-dd | HH:mm");
    
    /**
     * List of reservation timings in this table.
     */
    private ArrayList<LocalDateTime> ldtArr = new ArrayList<LocalDateTime>();

    /**
     * Creates a new table with the parameters given below.
     * @param tableStatus 		The status of the table for this table.
     * @param tableNumber 		The table number of this table.
     * @param tablesize   		The table size of this table.
     */
    public Table(TableStatus tableStatus, int tableNumber, int tablesize)
    {
        if(tableStatus.equals("AVAILABLE"))
            this.tableStatus = TableStatus.AVAILABLE;
        else if(tableStatus.equals("RESERVED"))
			this.tableStatus = TableStatus.RESERVED;
        else
            this.tableStatus = TableStatus.OCCUPIED;
        
        this.tableNumber = tableNumber;
        this.tableSize = tablesize;
    }
    
    /**
     * Creates a new table with the parameters given below in String format.
     * @param tableStatus 		The status of the table for this table.
     * @param tableNumber 		The table number of this table.
     * @param tablesize   		The table size of this table.
     * @param ldrArr			The list of reservation times for this table.
     */
    public Table(String tableStatus, String tableNumber, String tablesize, ArrayList<LocalDateTime> ldtArr)
    {
        if (tableStatus.equals("AVAILABLE"))
            this.tableStatus = TableStatus.AVAILABLE;
        else if(tableStatus.equals("RESERVED"))
			this.tableStatus = TableStatus.RESERVED;
        else
            this.tableStatus = TableStatus.OCCUPIED;
        
        this.tableNumber = Integer.parseInt(tableNumber);
        this.tableSize = Integer.parseInt(tablesize);
        this.ldtArr = ldtArr;
    }
    
    /**
     * Joins the list of reservation times assigned to a specific table.
     * @return	The list of concatenated reservation times
     */
    public String updateRes() 
    {
    	String info = new String();
    	for(LocalDateTime time : ldtArr) 
    	{
    		info += time +"/";
    	}
    	return info;
    }
    
    /**
     * Prints the details of the table.
     */
	public void displayTableDetails()
	{
		System.out.printf("%-25s %-21s", ("[" + this.tableStatus + "]"), this.tableNumber + "(" + this.tableSize+")");
		for(LocalDateTime time: ldtArr)
		{
			if(ldtArr.indexOf(time)==0)
			{
				System.out.printf(time.format(formatterTime).toString());
				continue;
			}
			System.out.printf("\n%65s", time.format(formatterTime).toString());
		}
		System.out.println("");
	}
	
	/**
	 * Adds a new reservation to this table.
	 * @param reservationTime	The time of reservation
	 */
	public void addReservation(LocalDateTime reservationTime)
	{
		ldtArr.add(reservationTime);
	}
	
	/**
	 * Removes a reservation to this table.
	 * @param reservationTime	The time of reservation
	 */
	public void removeReservation(LocalDateTime reservationTime)
	{
		ldtArr.remove(reservationTime);
	}

	/**
	 * Contains the status of this table.
	 * @return	The status of this table
	 */
    public TableStatus getStatus()
    {
        return this.tableStatus;
    }
    
    /**
     * Sets the status of this table
     * @param tableStatus	The status of this table
     */
    public void setStatus(TableStatus tableStatus)
    {
        this.tableStatus = tableStatus;
    }
    
    /**
     * Gets the table number of this table
     * @return	The table number of this table
     */
    public int getTableNumber()
    {
        return this.tableNumber;
    }

    /**
     * Sets the table number of this table
     * @param tableNumber	The table number of this table
     */
    public void setTableNumber(int tableNumber)
    {
        this.tableNumber = tableNumber;
    }

    /**
     * Gets the table size (in terms of number of pax) of this table
     * @return	The table size (in terms of number of pax) of this table
     */
    public int getTableSize()
    {
        return this.tableSize;
    }

    /**
     * Sets the table size (in terms of number of pax) of this table
     * @param tableSize	The table size (in terms of number of pax) of this table
     */
    public void setTableSize(int tableSize)
    {
        this.tableSize = tableSize;
    }
 
    /**
     * Checks for new reservation time requirement (within a 2h range).
     * @param time	The new time of reservation booking
     * @return		
     */
    public boolean checkTableReservations(LocalDateTime time)
    {
    	for(LocalDateTime t : ldtArr)
    	{
    		if(t.plusHours(2).isAfter(time) && t.minusHours(2).isBefore(time))
    		{ 
    			return false;
    		}
    	}
    	return true;
    }
    
    /**
     * Function used to check if a table has existing reservation upon releasing.
     * @return true if reservation is empty
     */
    public boolean reservationIsEmpty()
    {
    	if(ldtArr.size()==0)
    		return true;
    	
    	return false;
    }
    
    /**
     * Function used to check for expired reservation
     */
    public void checkReservation() {
    	ArrayList<LocalDateTime> expList = new ArrayList<LocalDateTime>();
    	for(LocalDateTime t: ldtArr) {
            LocalDateTime currentTime = LocalDateTime.now();
            if (t.plusDays(1).isBefore(currentTime)){
                expList.add(t);
                if(tableStatus!=TableStatus.OCCUPIED && reservationIsEmpty()) {
                	setStatus(TableStatus.AVAILABLE);
                }
            }
            else
            	t.minusDays(1);
    	}
    	for(LocalDateTime r: expList) {
    		ldtArr.remove(r);
    	}
    }
}
