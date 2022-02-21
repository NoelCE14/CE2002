package Lab4;

import java.util.*;
public class Strings {
    public static void main (String[] args)
    {
        
        int size;
        Scanner scan = new Scanner(System.in);
        System.out.print ("\nHow many Strings do you want to sort? ");
        size = scan.nextInt();
        scan.nextLine();
        Comparable<String>[] stringList = new Comparable[size];
        System.out.println ("\nEnter the String...");
        for (int i = 0; i < size; i++)
            stringList[i] = scan.nextLine();
            // Sorting.selectionSort(stringList);
            Sorting.insertionSort(stringList);
            System.out.println ("\nYour numbers in sorted order...");
        for (int i = 0; i < size; i++)
            System.out.print(stringList[i] + " ");
        System.out.println ();

        scan.close();
    }
}
