package Lab2;

import java.util.*;

public class P5 {
    public static void main(String arg[]){
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int result = countDigits(n);
        if (result == -1)
            System.out.println("Error Input");
        else{
            System.out.println("count=" + result);
        }

        input.close();
    }

    public static int countDigits(int n){
        if (n < 0)
            return -1;
        else if (n==0)
            return 1;
        else{
            int count = 0;
            while (n>0){
                n /= 10;
                count++;
            }
            return count;
        }
    }
}
