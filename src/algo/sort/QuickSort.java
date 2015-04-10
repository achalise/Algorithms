package algo.sort;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: arunc
 * Date: 26/03/15
 * Time: 10:44 AM
 */
public class QuickSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type no of integers followed by the integers to be sorted. ");
        Integer count = scanner.nextInt();
        int[] input = new int[count];
        int index = 0;
        while (index < count) {
           input[index++] = scanner.nextInt();
        }
        System.out.print("Input array: ");
        printArray(input);
    }

    private static void printArray(int[] input) {
        int i = 0;
        System.out.print(" [ " + input[i++]);
        for(; i < input.length; i++) {
            System.out.print(", " + input[i]);
        }
        System.out.println(" ]");
    }
}
