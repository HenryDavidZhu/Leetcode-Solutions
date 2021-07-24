class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int lenLCIS = 1;
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= nums[i - 1]) {
                dp[i] = 1;
            }
            else {
                dp[i] = dp[i - 1] + 1;
            }
            
            lenLCIS = Math.max(lenLCIS, dp[i]);
        }
        
        return lenLCIS;
    }
}