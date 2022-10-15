package QuickSort;

import java.util.Scanner;

public class SortDriver {

    static QuickSort qs = new QuickSort();

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        char command;
        boolean quit = false;
        String list = "";

        System.out.println("Use a sorting algorithm to order your numbers.");
        try {
            while (!quit) {
                System.out.println(
                        "Enter one of the following commands...\n'Q' for QuickSort\n'B' for BucketSort\n'M' for MergetSort\n\n'E' to Exit");
                command = userInput.next().charAt(0);
                if (command == 'E')
                    break;
                System.out.println("Enter the numbers (separated by commas) you wish to sort");
                list = userInput.next();
                String[] numsStr = list.split(",");
                int[] nums = new int[numsStr.length];
                for (int i = 0; i < numsStr.length; i++) {
                    nums[i] = Integer.parseInt(numsStr[i]);
                }
                if (command == 'Q') {
                    qs.quickSort(nums, 0, nums.length - 1);
                } else if (command == 'B') {

                } else if (command == 'M') {

                }
                for (int num : nums) {
                    System.out.print(num + " ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            // System.in has been closed
            System.out.println("Error: Exiting...");
        }
        userInput.close();
    }
}