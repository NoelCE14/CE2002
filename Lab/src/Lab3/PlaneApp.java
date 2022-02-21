package Lab3;

import java.util.*;
public class PlaneApp {
    public static void main(String[] args){
        int choice;
        Scanner input = new Scanner(System.in);

        Plane p = new Plane();

        System.out.println("1: Show number of empty seats");
        System.out.println("2: Show the list of empty seats");
        System.out.println("3: Show the list of seat assignments by seat ID");
        System.out.println("4: Show the list of seat assignments by customer ID");
        System.out.println("5: Assign a customer to a seat");
        System.out.println("6: Remove a seat assignment");
        System.out.println("7: Exit");

        do {
            System.out.println("Enter the number of your choice:");
            
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    p.showNumEmptySeats();
                    break;
                case 2:
                    p.showEmptySeats();
                    break;
                case 3:
                    p.showAssignedSeats(true);
                    break;
                case 4:
                    p.showAssignedSeats(false);
                    break;
                case 5:
                    System.out.printf("enter seatID: ");
                    int s = input.nextInt();
                    System.out.printf("Enter customer ID: ");
                    int c = input.nextInt();
                    p.assignSeat(s, c);
                    break;
                case 6:
                    System.out.printf("Enter SeatID to unassign customer from: ");
                    int seatID = input.nextInt();
                    p.unAssignSeat(seatID);
                    break;
                case 7: 
                    System.out.println("Program terminating ….");
            }
        } while (choice < 7);

        input.close();
    }
}
