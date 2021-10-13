class Solution {
    // O(n) time, O(n) space
    public int findMaxConsecutiveOnes(int[] nums) {
        List<int[]> subarrayBounds = new LinkedList<int[]>();
        
        int[] currSubarrayBounds = new int[2];
        currSubarrayBounds[0] = -1;
        
        int prevNum = nums[0];
        
        // Find all subarrays of all consecutive ones.
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (prevNum == 1) {
                    subarrayBounds.add(currSubarrayBounds);
                }
                
                currSubarrayBounds = new int[2];
                currSubarrayBounds[0] = -1;
            }
            
            if (nums[i] == 1) {
                if (currSubarrayBounds[0] == -1) {
                    currSubarrayBounds[0] = i;
                }
                
                currSubarrayBounds[1] = i;
            }
            
            prevNum = nums[i];
        }
        
        // Edge case: we have a 1 subarray that ends at the input array's end.
        if (currSubarrayBounds[0] != -1) {
            subarrayBounds.add(currSubarrayBounds);
        }
        
        // From the subarrays of all ones, compute the max length of the
        // subarray of all 1s from flipping a 0 to a 1.
        if (subarrayBounds.size() == 0) {
            return 1;
        }
        
        int[] prevSubarray = subarrayBounds.get(0);
        int prevSubarrayLen = prevSubarray[1] - prevSubarray[0] + 1;
        int maxLenSubarray = prevSubarrayLen;
        
        for (int i = 0; i < subarrayBounds.size(); i++) {
            int[] currSubarray = subarrayBounds.get(i);
            int currSubarrayLen = currSubarray[1] - currSubarray[0] + 1;
            
            // If we can add 0 before current subarray.
            if (currSubarray[0] > 0) {
                maxLenSubarray = Math.max(maxLenSubarray, currSubarrayLen + 1);
                
                // If 0 will be combine 2 subarrays.
                if (prevSubarray[1] + 1 == currSubarray[0] - 1) {
                    maxLenSubarray = Math.max(maxLenSubarray, prevSubarrayLen + currSubarrayLen + 1);
                }
            }
            
            // If we can add 0 after current subarray.
            if (currSubarray[1] < nums.length - 1) {
                maxLenSubarray = Math.max(maxLenSubarray, currSubarrayLen + 1);
            }
            
            prevSubarray = currSubarray;
            prevSubarrayLen = prevSubarray[1] - prevSubarray[0] + 1;
        }
        
        return maxLenSubarray;
    }
}