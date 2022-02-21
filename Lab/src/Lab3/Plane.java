package Lab3;

import java.util.ArrayList;
import java.util.*;

public class Plane {
    private PlaneSeat[] seat = new PlaneSeat[12];
    private int numEmptySeat;

    public Plane(){
        for (int i=0; i<12; i++){
            seat[i] = new PlaneSeat(i+1);
        }
        this.numEmptySeat = 12;
    }

    private PlaneSeat[] sortSeats(PlaneSeat[] seat){
        
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (int i=0; i < 12; i++){
            if (seat[i].isOccupied()){
                arr.add(seat[i].getCustomerID());
            }
        }
        Collections.sort(arr);
        PlaneSeat[] sortS = new PlaneSeat[arr.size()];
        for (int i = 0; i<arr.size(); i++){
            for (int j=0;j<12;j++){
                if (seat[j].isOccupied()){
                    if (seat[j].getCustomerID() == arr.get(i)){
                        sortS[i] = new PlaneSeat(j+1);
                        sortS[i].assign(arr.get(i));
                        break;
                    }
                }
            }
        }

        return sortS;

    }

    public void showNumEmptySeats(){
        int r = 12;
        for (int i=0; i < 12; i++){
            if (seat[i].isOccupied())
                r--;
        }
        numEmptySeat = r;
        System.out.println("There are " + r + " empty seats");
    }

    public void showEmptySeats(){
        System.out.println("The following seats are empty: ");
        for (int i = 0; i<12; i++){
            if (!seat[i].isOccupied())
                System.out.println("SeatID " + seat[i].getSeatID());
        }
    }

    public void showAssignedSeats(boolean bySeatId){
        if (bySeatId){
            for (int i = 0; i< 12; i++){
                if (seat[i].isOccupied()){
                System.out.println("SeatID " + seat[i].getSeatID() + " assigned to CustomerID " + seat[i].getCustomerID());

                }
            }
        }
        else{
            PlaneSeat[] sortS = sortSeats(seat);
            for (int i=0; i<sortS.length; i++){
                System.out.println("SeatID " + sortS[i].getSeatID() + " assigned to CustomerID " + sortS[i].getCustomerID());
            }
        }
    }

    public void assignSeat(int seatId, int cust_id){
        if (!seat[seatId-1].isOccupied()){
            seat[seatId-1].assign(cust_id);
            System.out.println("Seat assigned");
        }
        else
            System.out.println("Seat already assigned to a customer.");
    }

    public void unAssignSeat(int seatId){
        if (seat[seatId-1].isOccupied()){
            seat[seatId-1].unAssign();
            System.out.println("Seat Unassigned!");
        }
        else{
            System.out.println("Seat Empty.");
        }
    }

}
