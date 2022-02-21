package Lab2;

import java.util.*;

public class P4 {
    public static void main(String arg[]){
        Scanner input = new Scanner(System.in);
        int m = input.nextInt();
        int n = input.nextInt();
        int result = modulus(m, n);
        System.out.println(result);

        input.close();
    }

    public static int modulus(int m, int n){
        while (m>=0){
            m -= n;
        }
        return m+n;
    }
}
