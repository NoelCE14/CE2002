package RRPSS;

import java.util.*;
import java.io.*;
import java.time.*;

/**
 * Table database used to obtain, update, add, remove and print table reservation in the company.
 * Information of the table are stored in a txt file (Table.txt) in the database folder.
 * @author Kua Hao Ren
 * @version 1.0
 * @since 2021-11-03
 */
public class TableDB
{
	/**
	 * List of tables in the company.
	 */
	private ArrayList<Table> tableList = new ArrayList<Table>();

	/**
	 * Creates a new Table database and TableDB.txt file in the database folder.
	 * To store the information of table in the company.
	 * To update the Table list with the information in the txt file.
	 * @throws IOException	If an input or output exception occurs.
	 */
	public TableDB()
	{
		getFromTableFile();
	}
	
	/**
	 * Reads the TableDB.txt file and updates this database with the information in the txt file.
	 * @throws IOException If an input or output exception occurred.
	 */
	 public void getFromTableFile()
	 {
		 	try {
		 		File file = new File("database"+ File.separator + "TableDB.txt");
		 		BufferedReader br = new BufferedReader(new FileReader(file));
		 		String st = null;
		 		br.readLine();
		 		while((st = br.readLine()) != null)
		 		{
		 			String[] str = st.split(",");
		 			String[] res;
		 			ArrayList<LocalDateTime> resArr = new ArrayList<LocalDateTime>();
		 			if(str.length==4) {
			 			res = str[3].split("/");
			 			for(String s: res)
			 			{
			 				resArr.add(LocalDateTime.parse(s));
			 			}
		 			}
		 			tableList.add(new Table(str[0], str[1], str[2], resArr));
		 		}
		 		br.close();
		 		
		 	} catch (IOException e) {
		 		e.printStackTrace();
		 	}
		 }
	
	 /**
	  * Writes the list of Table in this database into the TableDB.txt file.
	  * @throws IOException If an input or output exception occurred.
	  */
	 public void updateToTableFile()
	 {
		 	try {
		 		File file = new File("database"+ File.separator + "TableDB.txt");
		 		PrintWriter writer = new PrintWriter(file);
		 		writer.println("Table Status | Table Number | Table Size | Reservation Time");
		 		for(Table table : tableList)
		 		{
		 			writer.printf(table.getStatus() + "," + table.getTableNumber() + "," + table.getTableSize() + ",");
		 			writer.printf(table.updateRes());
		 			writer.printf("\n");
		 		}
		 		writer.close();
		 		
		 	} catch(IOException e) {
		 		e.printStackTrace();
		 	}
		 }
	
	/**
	 * Searches this database and sets the status of the corresponding table to OCCUPIED upon reservation based on the table number given.
	 * @param tablenum	The table number of the corresponding table
	 */
	public void setToOccupied(int tablenum)
	{
		for(Table t : tableList)
		{
			if(t.getTableNumber() == tablenum)
				t.setStatus(TableStatus.OCCUPIED);
		}
	}
	
	/**
	 * Searches this database and sets the status of the corresponding table to RESERVED upon reservation based on the table number given.
	 * Adds this table to the list
	 * @param tablenum			The table number used for the reservation
	 * @param reservationTime	The time of reservation made
	 */
	public void addReservation(int tablenum, LocalDateTime reservationTime)
	{
		for(Table t : tableList)
		{
			if(t.getTableNumber() == tablenum)
			{
				t.addReservation(reservationTime);
				if(t.getStatus()==TableStatus.OCCUPIED)
					return;
				t.setStatus(TableStatus.RESERVED);
			}
		}
	}
	
	/**
	 * Searches this database and sets the status of the corresponding table to AVAILABLE upon reservation based on the table number given.
	 * Removes this table from the list.
	 * @param tablenum			The table number used for the reservation
	 * @param reservationTime	The time of reservation to be removed
	 */
	public void removeReservation(int tablenum, LocalDateTime reservationTime)
	{
		for(Table t : tableList)
		{
			if(t.getTableNumber() == tablenum)
			{
				t.removeReservation(reservationTime);
				if(t.getStatus()!=TableStatus.OCCUPIED) {
					if(!t.reservationIsEmpty())
						t.setStatus(TableStatus.RESERVED);
					else
						t.setStatus(TableStatus.AVAILABLE);
				}
			}
		}
	}
	
	/**
	 * Searches this database for a valid table with the corresponding table number.
	 * @param tablenum	The table number of the table to be searched for
	 * @return			
	 */
	public Table searchTable(int tablenum)
	{
		for(Table t : tableList)
		{
			if(t.getTableNumber() == tablenum)
				return t;
		}
		return null;
	}
	
	/**
	 * Searches this database for a valid available table with the corresponding pax (in terms of table size) based on the time of reservation.
	 * @param pax	The number of pax required (corresponds to the table size)
	 * @param time	The time of reservation made on this table
	 * @return		The table number of the corresponding table based on statues (AVAILABLE, RESERVED, OCCUPIED)
	 */
	public int findAvailableTable(int pax, LocalDateTime time) 
	{
		if(time.plusMinutes(1).isBefore(LocalDateTime.now())) //If input time is in the past
			return -1;
		
		for(Table t : tableList) //Check available tables first
		{
			if((t.getStatus() == TableStatus.AVAILABLE) && (t.getTableSize() == pax))
			{	
				return t.getTableNumber();
			}
		}
		
		for(Table t : tableList) //Check reserved tables
		{
			if(t.getStatus() == TableStatus.RESERVED)
			{
				if(t.checkTableReservations(time) && t.getTableSize() == pax)
					return t.getTableNumber();
			}
			
			if(t.getStatus() == TableStatus.OCCUPIED)
			{
				if(LocalDateTime.now().plusHours(2).isBefore(time) && t.getTableSize() >= pax && t.checkTableReservations(time))
					return t.getTableNumber();
			}
		}
		return -1;
	}

	/**
	 * Searches this database for a valid table with the corresponding table number.
	 * Releases the table by setting the status of this table to AVAILABLE if table has no existing reservation.
	 * Else, status of table remains RESERVED.
	 * @param tableNumber	The table number to be released
	 */
	public void releaseTable(int tableNumber) 
	{	
		for (Table t : tableList)
		{
			if(t.getTableNumber() == tableNumber)
			{
				System.out.printf("Table %d has been released.\n", tableNumber);
				if(t.reservationIsEmpty())
					t.setStatus(TableStatus.AVAILABLE);
				else
					t.setStatus(TableStatus.RESERVED);
			}
		}
	}
	
	/**
	 * Searches and prints the list of tables with the corresponding details in this database.
	 */
	public void printTableList()
	{
		if(tableList.size() < 1)
		{
			System.out.println("There is nothing in the Table database!");
			return;
		}
		
		System.out.println("=============================================  Table Details =============================================");
		System.out.printf("%-20s %-25s %s\n", "Status", "Table Number(Pax)", "Reservation Details");
		
		for(Table t : tableList)
		{
			t.displayTableDetails();
		}
		System.out.printf("\n");
	}
	
	/**
	 * check whether the reservation expire one day or not
	 */
    public void checkExpiry(){
    	for(Table t: tableList) {
    		t.checkReservation();
    	}
    }
}
