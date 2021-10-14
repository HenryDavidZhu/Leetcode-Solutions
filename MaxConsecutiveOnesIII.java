class Solution {
    // Sliding Window Algorithm
    // O(n) time, O(1) space
    public int longestOnes(int[] nums, int k) {
        int start = 0;
        int end = 0;
        int longestOnesLen = k;
        
        while (end < nums.length) {
            // Flip the 0
            if (nums[end] == 0) {
                k--;
            }
            
            // If we used more than the flip capacity.
            if (k < 0) {
                // Update the flip capacity
                if (nums[start] == 0) {
                    k++;
                }
                
                start++;
            }
            // We can extend the current subarray of all 1s.
            else {
                longestOnesLen = Math.max(longestOnesLen, end - start + 1);
            }
            
            end++;
        }
        
        return longestOnesLen;
    }
}