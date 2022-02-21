package Lab2;

import java.util.*;

public class P7 {
    public static void main(String arg[]){
        Scanner input = new Scanner(System.in);
        long n = input.nextLong();
        long result = extractOddDigits(n);
        if (result == -2)
            System.out.println("Error Input");
        else{
            System.out.println("oddDigits=" + result);
        }

        input.close();
    }

    public static long extractOddDigits(long n){
        if (n<0)
            return -2;
        int result = 0;
        int m = 1;
        while (n>0){
            if ((n%10)%2 == 1){
                result += m*(n%10);
                m *= 10;
            }
            
            n /= 10;
        }
        if (result == 0)
            return -1;
        return result;
    }
}
