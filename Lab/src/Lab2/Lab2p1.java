package Lab2;

import java.util.*;

public class Lab2p1 {
    public static void main(String[] args)
    {
        int choice;
        int m,n,result;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Perform the following methods:");
            System.out.println("1: miltiplication test");
            System.out.println("2: quotient using division by subtraction");
            System.out.println("3: remainder using division by subtraction");
            System.out.println("4: count the number of digits");
            System.out.println("5: position of a digit");
            System.out.println("6: extract all odd digits");
            System.out.println("7: quit");
            choice = input.nextInt();
            switch (choice) {
                case 1: 
                    mulTest();
                    break;
                case 2: 
                    System.out.println("input m");
                    m = input.nextInt();
                    System.out.println("input n");
                    n = input.nextInt();
                    result = divide(m, n);
                    System.out.println("result =" + result);
                    break;
                case 3: 
                    System.out.println("input m");
                    m = input.nextInt();
                    System.out.println("input n");
                    n = input.nextInt();
                    result = P4.modulus(m, n);
                    System.out.println("result =" + result);
                    break;
                case 4: 
                    System.out.println("input n:");
                    n = input.nextInt();
                    result = countDigits(n);
                    if (result == -1)
                        System.out.println("Error Input");
                    else{
                        System.out.println("count=" + result);
                    }
                    break;
                case 5: 
                    System.out.println("input m");
                    m = input.nextInt();
                    System.out.println("input n");
                    n = input.nextInt();
                    result = position(m, n);
                    System.out.println("position = " + result);
                    break;
                case 6: 
                    System.out.println("input long: ");
                    long k = input.nextLong();
                    long r = extractOddDigits(k);
                    if (r == -2)
                        System.out.println("Error Input");
                    else{
                        System.out.println("oddDigits=" + r);
                    }
                break; 
                case 7: 
                    System.out.println("Program terminating ….");
            }
        } while (choice < 7);

        input.close();
    }

    
    // case 1
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
    // case 2
    public static int divide(int m, int n){
        int result = 0;
        while (m>=0){
            m -= n;
            result++;
        }
        return result-1;
    }
    // case 3
    public static int modulus(int m, int n){
        while (m>=0){
            m -= n;
        }
        return m+n;
    }

    // case 4
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

    // case 5
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

    // case 6
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
