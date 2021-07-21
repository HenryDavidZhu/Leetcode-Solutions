// Merge sort: 
// O(n log n) time complexity
// O(n) space complexity
class Solution {
    public int[] sortArray(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }
    
    private void mergeSort(int[] nums, int start, int end) {
        // Only need to sort the array if it contains numbers
        if (start < end) {
            // Sort the left and right halves of the array
            int mid = (start + end) / 2;
            mergeSort(nums, 0, mid);
            mergeSort(nums, mid + 1, end);
            
            // Merged the sorted left and right halves
            merge(nums, start, mid, end);
        }
    }
    
    private void merge(int[] nums, int start, int mid, int end) {
        int[] mergedArray = new int[end - start + 1];
        int leftIdx = start;
        int rightIdx = mid + 1;
        int mergedIdx = 0;
        
        while (leftIdx <= mid && rightIdx <= end) {
            if (nums[leftIdx] < nums[rightIdx]) {
                mergedArray[mergedIdx] = nums[leftIdx];
                leftIdx++;
                mergedIdx++;
            }
            
            if (nums[rightIdx] < nums[leftIdx]) {
                mergedArray[mergedIdx] = nums[rightIdx];
                rightIdx++;
                mergedIdx++;
            }
        }
        
        // Add remaining elements in the left or right half
        while (leftIdx <= mid) {
            mergedArray[mergedIdx] = nums[leftIdx];
            leftIdx++;
            mergedIdx++;
        }
        
        while (rightIdx <= end) {
            mergedArray[mergedIdx] = nums[rightIdx];
            rightIdx++;
            mergedIdx++;
        }
        
        // Copy the sorted portion to the nums array
        for (int i = 0; i < mergedArray.length; i++) {
            nums[start + i] = mergedArray[i];
        }
    }
}