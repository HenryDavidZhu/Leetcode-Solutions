class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        // maps sum of nums[0:i] to i
        Map<Integer, Integer> dp = new HashMap<Integer, Integer>();
        
        int maxSubArrayLength = 0;
        int sum = 0;
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j];
            
            if (sum == k) {
                maxSubArrayLength = Math.max(maxSubArrayLength, j + 1);
            }
            if (dp.containsKey(sum - k)) {
                int i = dp.get(sum - k);
                maxSubArrayLength = Math.max(maxSubArrayLength, j - i);
            }
            
            if (!dp.containsKey(sum)) {
                dp.put(sum, j);
            }
        }
        
        return maxSubArrayLength;
    }
}