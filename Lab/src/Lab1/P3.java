package Lab1;

import java.util.*;

public class P3 {

    private static double curr = 1.82;

    public static void main (String[] args){
        Scanner input = new Scanner(System.in);

        System.out.println("starting: ");
        int start = input.nextInt();
        System.out.println("ending: ");
        int end = input.nextInt();
        System.out.println("increment: ");
        int inc = input.nextInt();

        if (end < start){
            System.out.println("Error input");
        }

        else{
            System.out.println("for loop method");
            printHead();
            for (int i=start; i<=end; i+=inc){
                System.out.printf(i + "\t\t" + "%.2f", i*curr);
                System.out.println();
            }
            System.out.println("while loop method");
            printHead();
            int j = start;
            while (j<=end){
                System.out.printf(j + "\t\t" + "%.2f", j*curr);
                System.out.println();
                j+=inc;
            }
            System.out.println("do while loop method");
            printHead();
            j = start;
            do{
                System.out.printf(j + "\t\t" + "%.2f", j*curr);
                System.out.println();
                j+=inc;
            } while (j<=end);
        }
        

        input.close();
    }

    private static void printHead (){
        System.out.println("US$\t\tS$");
        System.out.println("-------------------");
    }
}
