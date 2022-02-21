package Lab1;

import java.util.*;

public class P4 {
    public static void main (String[] args){
        Scanner input = new Scanner(System.in);

        System.out.println("input the height");
        int h = input.nextInt();

        if (h<=0){
            System.out.println("Error input");
        }
        else{
            for (int i=1; i<=h;i++){
                int j = i;
                for (int k=0;k<i;k++,j++){
                    if (j%2 == 1){
                        System.out.printf("AA");
                    }
                    else{
                        System.out.printf("BB");
                    }
                }
                System.out.println();
            }
        }
        
        input.close();
    }
}
