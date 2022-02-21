package RRPSS;

/**
 * Represents a staff employed in the company.
 * @author Chua Chong Yih
 * @version 1.0
 * @since 2021-11-03
 */
public class Staff {

    /**
     * The name of this staff.
     */
    private String name;

    /**
     * The gender of this staff.
     */
    private Gender gender;

    /**
     * The unique employee ID of this staff.
     */
    private int employeeID;

    /**
     * The position that this staff holds in the company.
     */
    private String jobTitle;

    /**
     * Creates a new staff with the given name, gender, employeeID and jobTitle.
     * @param name          This staff's name.
     * @param gender        This staff's gender.
     * @param employeeID    This staff's employee ID.
     * @param jobTitle      This staff's job title.
     */
    public Staff(String name, Gender gender, int employeeID, String jobTitle){
        this.name = name;
        this.gender = gender;
        this.employeeID = employeeID;
        this.jobTitle = jobTitle;
    }

    /**
     * Creates a new staff with the given name, gender, employeeID and jobTitle given in String format
     * to be converted into the corresponding data types that.
     * Used for reading the database text file.
     * @param name          This staff's name.
     * @param gender        This staff's gender.
     * @param employeeID    This staff's employee ID.
     * @param jobTitle      This staff's job title.
     */
    public Staff(String name, String gender, String employeeID, String jobTitle){
        this.name = name;
        if (gender.equals("MALE"))
            this.gender = Gender.MALE;
        else
            this.gender = Gender.FEMALE;
        this.employeeID = Integer.parseInt(employeeID);
        this.jobTitle = jobTitle;
    }

    /**
     * Prints this staff's name, employee ID, job title and gender.
     */
    public void displayStaffDetails(){
        System.out.println("Employee Name: " + name);
        System.out.println("Employee ID: " + employeeID);
        System.out.println("Job Title: " + jobTitle);
        System.out.println("Gender: " + gender.toString());
    }
    
    /**
     * Gets the name of this staff.
     * @return this staff's name.
     */
    public String getName(){
        return name;
    }

    /**
     * Changes the name of this staff.
     * @param name This staff's new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the gender of this staff.
     * @return this staff's gender.
     */
    public Gender getGender(){
        return gender;
    }

    /**
     * Gets the employee ID of this staff.
     * @return this staff's employee ID.
     */
    public int getEmployeeID(){
        return employeeID;
    }

    /**
     * Changes the employee ID of this staff.
     * @param employeeID This staff's new employee ID.
     */
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }
    
    /**
     * Gets the job title of this staff.
     * @return this staff's job title.
     */
    public String getJobTitle(){
        return jobTitle;
    }
    
    /**
     * Changes the job title of this staff.
     * @param jobTitle This staff's new job title.
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
