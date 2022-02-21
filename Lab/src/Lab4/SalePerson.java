package Lab4;

public class SalePerson implements Comparable<SalePerson>{

    private String firstName;
    private String lastName;
    private int totalSales;

    public SalePerson(String firstName, String lastName, int totalSales){
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalSales = totalSales;
    }
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public int getTotalSales(){
        return this.totalSales;
    }

    public String toString(){
        return lastName + " , " + firstName + " : " + totalSales;
    }

    public boolean equals(SalePerson o){
        if (this.firstName.equals(o.firstName) && this.lastName.equals(o.lastName)){
            return true;
        }
        return false;
    }
    @Override
    public int compareTo(SalePerson o) {
        if (this.totalSales > o.totalSales){
            return 1;
        }
        else if (this.totalSales < o.totalSales){
            return -1;
        }
        else{
            if (this.lastName.compareTo(o.lastName)<0){
                return 1;
            }
            else{
                return -1;
            }
        }
    }
    
}
