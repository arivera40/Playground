package QuickSort;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class QuickSort {

    /**
     * @param nums
     * @param low
     * @param high
     */
    public void quickSort(int[] nums, int low, int high) {
        if (low < high) {
            int partition = partition(nums, low, high);

            quickSort(nums, low, partition - 1);
            quickSort(nums, partition + 1, high);
        }

    }

    /**
     * @param nums
     * @param low
     * @param high
     */
    public void quickSort(LinkedList<Integer> nums, int low, int high) {

    }

    /**
     * @param nums
     * @param low
     * @param high
     * @return
     */
    public int partition(int[] nums, int low, int high) {
        // Choose random pivot index within range.
        int pivotIdx = ThreadLocalRandom.current().nextInt(low, high + 1);

        int temp = nums[high];
        nums[high] = nums[pivotIdx];
        nums[pivotIdx] = temp;

        int pivot = nums[high];
        int pivotloc = low;

        for (int i = low; i < high; i++) {
            if (nums[i] <= pivot) {
                temp = nums[i];
                nums[i] = nums[pivotloc];
                nums[pivotloc] = temp;
                pivotloc++;
            }
        }

        temp = nums[high];
        nums[high] = nums[pivotloc];
        nums[pivotloc] = temp;

        return pivotloc;
    }
}