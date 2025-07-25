/**
 * MaxCircularSubarraySum.java
 * 
 * Problem:
 * Given a circular array, find the maximum possible sum of a non-empty subarray.
 * In a circular array, the subarray can start at the end and wrap around to the beginning.
 * 
 * Approach:
 * 1. Use Kadane's Algorithm to find the max subarray sum (non-circular).
 * 2. Find the total sum of the array and invert the array.
 * 3. Apply Kadane's Algorithm again on the inverted array to get the min subarray sum.
 * 4. Calculate the max circular subarray sum as total_sum - min_subarray_sum.
 * 5. Handle edge cases (all elements negative).
 * 6. Return the maximum of non-circular and circular subarray sums.
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

class Solution {

    // Main function to find maximum circular subarray sum
    public int maxCircularSum(int arr[]) {
        int n = arr.length;

        // Step 1: Normal Kadane for non-circular case
        int max_kadane = kadane(arr);

        // Step 2: Total sum of array & invert elements
        int total_sum = 0;
        for (int i = 0; i < n; i++) {
            total_sum += arr[i];
            arr[i] = -arr[i]; // Invert to find min subarray sum
        }

        // Step 3: Min subarray sum using Kadane on inverted array
        int max_inverse_kadane = kadane(arr);
        int max_circular = total_sum + max_inverse_kadane; // circular sum

        // Step 4: Handle case when all numbers are negative
        if (max_circular == 0) 
            return max_kadane;

        // Step 5: Return the best of circular or non-circular
        return Math.max(max_kadane, max_circular);
    }

    // Kadane's algorithm
    private int kadane(int[] arr) {
        int max_so_far = arr[0];
        int current_max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            current_max = Math.max(arr[i], current_max + arr[i]);
            max_so_far = Math.max(max_so_far, current_max);
        }

        return max_so_far;
    }

    // Driver for testing
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr = {8, -8, 9, -9, 10, -11, 12};
        System.out.println("Maximum Circular Subarray Sum: " + sol.maxCircularSum(arr));
    }
}
