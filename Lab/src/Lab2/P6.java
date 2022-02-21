package Lab2;

import java.util.*;

public class P6 {
    public static void main(String arg[]){
        Scanner input = new Scanner(System.in);
        int m = input.nextInt();
        int n = input.nextInt();
        int result = position(m, n);
        System.out.println("position = " + result);

        input.close();
    }

    public static int position(int m, int n){
        int pos = 1;
        while (m>0){
            if (n == m%10)
                return pos;
            m /= 10;
            pos++;
        }
        return -1;
    }
}
