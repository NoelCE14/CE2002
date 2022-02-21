package Lab1;

import java.util.*;

public class P1 {
    public static void main (String[] args){
        Scanner input = new Scanner(System.in);

        System.out.println("input your char");
        Character c = input.next().charAt(0);

        switch (c){
            case 'A':
            case 'a':
                System.out.println("Action movie fan");
                break;
            case 'C':
            case 'c':
                System.out.println("Comedy movie fan");
                break;
            case 'D':
            case 'd':
                System.out.println("Drama movie fan");
                break;
            default:
                System.out.println("Invalid choice");
        }

        input.close();
    }
}
