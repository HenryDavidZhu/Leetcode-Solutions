class Solution {
    // O(n) time, O(1) space
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxLen = 0;
        int currLen = 0;
        
        for (int num : nums) {
            if (num == 0) {
                currLen = 0;
            }
            
            if (num == 1) {
                currLen++;
                
                if (currLen > maxLen) {
                    maxLen = currLen;
                }
            }
        }
        
        return maxLen;
    }
}