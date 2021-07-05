class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxLen = 1;
        
        for (int i = 1; i < nums.length; i++) {
            int maxLenEndingAtI = 1;
            
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxLenEndingAtI = Math.max(maxLenEndingAtI, dp[j] + 1);
                }
            }
            
            dp[i] = maxLenEndingAtI;
            maxLen = Math.max(maxLen, maxLenEndingAtI);
        }
        
        return maxLen;
    }
}