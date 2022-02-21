package Lab1;

import java.util.*;

public class P2 {
    public static void main (String[] args){
        Scanner input = new Scanner(System.in);

        System.out.println("Salary: ");
        int salary = input.nextInt();
        System.out.println("Merit: ");
        int merit = input.nextInt();

        if (salary<=899 && salary>=700){
            if (salary >= 800)
                System.out.println("Grade A");
            else{
                if (merit>=20)
                    System.out.println("Grade A");
                else
                    System.out.println("Grade B");
            }
        }

        else if (salary<=699 && salary>=600){
            if (salary >= 650)
                System.out.println("Grade B");
            else{
                if (merit>=10)
                    System.out.println("Grade B");
                else
                    System.out.println("Grade C");
            }
        }

        else if (salary<=599 && salary>=500){
            System.out.println("Grade C");
        }

        input.close();
    }
}
