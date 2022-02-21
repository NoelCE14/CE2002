package Lab2;

import java.util.*;
public class P2 {
    public static void main(String arg[]){
        String c = "K";
        char a = c.charAt(0);
        a += 1;
        System.out.println("hello" + a + "yes");

        // mulTest();
    }

    public static void mulTest(){
        Scanner input = new Scanner(System.in);
        int k=0;
        for (int i=0;i<5;i++){
            int r1 = (int) ((Math.random() * 9) + 1);
            int r2 = (int) ((Math.random() * 9) + 1);
            System.out.print("how much is " + r1 + " times "+ r2 + "?");
            int r = input.nextInt();
            if (r == r1*r2)
                k++;
        }
        System.out.printf("%d answers out 5 are correct\n", k);

        input.close();
    }
}
