package RRPSS;

import java.util.*;

import java.io.*;

/**
 * Member databse use to store, retrieve and update Membership List in place
 * Stores the membership records into MemberDB.txt in the databse folder 
 * @author Zhang Xuanye
 * @version 1.0
 * @since 2021-11-11
 */
public class MemberDB {

    /**
     * List of memberships in this database.
     */
    private ArrayList<String> memberList = new ArrayList<>();

    /**
	 * Initialize Member Database and get membership records from MemberDB.txt
     * To store the records of membership in MemberDB.txt
     * To updata the records of membership in the txt
     * @throws IOException If an input or output exception occurred.
     */
    public MemberDB() throws IOException{
        getFromMemberFile();
    }

    /**
	 * Reads the MemberDB.txt file and updates memberList with the records in the txt file.
     * @throws IOException If an input or output exception occurred.
	 */
    public void getFromMemberFile() throws IOException{
        File f = new File("database"+ File.separator + "MemberDB.txt");
        BufferedReader reader = new BufferedReader(new FileReader(f));
        String line = "";
        while ((line = reader.readLine()) != null){
            memberList.add(line);
        }
        reader.close();
    }

    /**
	 * Writes the memberList to MemberDB.txt file
     * @throws IOException If an input or output exception occurred.
	 */
    public void updateToMemberFile() throws IOException{
        File f = new File("database"+ File.separator + "MemberDB.txt");
        PrintWriter writer = new PrintWriter(f);
        for (String p : memberList){            
            writer.println(p);
        }
        writer.close();
    }
    
    /**
	 * Searches the corresponding phone number in memberList.
	 * @param phoneNumber	The phone number of the customer to be searched for
	 * @return		Whether it is a member or not	
	 */
    public boolean searchMember(String phoneNumber){
        for (String p : memberList){
            if (p.equals(phoneNumber)){
                return true;
            }
        }
        return false;
    }

    /**
	 * Add a member into memberList with the phoneNumber of the customer
	 * @param phoneNumber	The phone number of the customer to be added 
	 */
    public void addMember(String phoneNumber){
        if (searchMember(phoneNumber)){
            System.out.println(phoneNumber + " is already a member");
            return;
        }
        else{
            memberList.add(phoneNumber);
            System.out.println("Member added successfully.");
            return;
        }
    }

    /**
	 * Remove a member from memberList with the phoneNumber of the customer
	 * @param phoneNumber	The phone number of the customer to be removed 
	 */
    public void removeMember(String phoneNumber){
        if (searchMember(phoneNumber)){
            memberList.remove(phoneNumber);
            System.out.println("Member removed successfully.");
            return;
        }
        else{
            System.out.println(phoneNumber + " is not a member.");
            return;
        }
    }
}
