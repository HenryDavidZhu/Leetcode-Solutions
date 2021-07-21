class Solution {
    public int jump(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        
        int[] dp = new int[nums.length];
        dp[0] = 0;
        
        for (int a = 1; a < dp.length; a++) {
            dp[a] = -1;
        }

        for (int i = 1; i < nums.length; i++) {
            int minDPJ = Integer.MAX_VALUE;
                
            for (int j = 0; j < i; j++) {
                if (j + nums[j] >= i && dp[j] != -1) {
                    minDPJ = Math.min(minDPJ, dp[j]);
                }
            }
            
            dp[i] = minDPJ + 1;
        }
        
        return dp[nums.length - 1];
    }
}