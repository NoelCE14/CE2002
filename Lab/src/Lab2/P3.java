package Lab2;

import java.util.*;

public class P3 {
    public static void main(String arg[]){
        Scanner input = new Scanner(System.in);
        int m = input.nextInt();
        int n = input.nextInt();
        int result = divide(m, n);
        System.out.println(result);

        input.close();
    }

    public static int divide(int m, int n){
        int result = 0;
        while (m>=0){
            m -= n;
            result++;
        }
        return result-1;
    }
}
