class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        // prefix[i] sum of nums[0:i]
        int[] prefix = new int[nums.length];
        int prefixA = 0;
        for (int a = 0; a < nums.length; a++) {
            prefixA += nums[a];
            prefix[a] = prefixA;
        }

        int i = 0;
        int minSubArrayLength = 0;
        
        for (int j = 0; j < nums.length; j++) {
            if (i == j) {
                if (nums[j] >= target) {
                    minSubArrayLength = 1;
                    i = i + 1;
                }
            }
            else {
                int numsIToJ = i == 0 ? prefix[j] : prefix[j] - prefix[i - 1];
                
                if (numsIToJ >= target) {
                    if (minSubArrayLength == 0) {
                        minSubArrayLength = j - i + 1;
                    }
                    else {
                        minSubArrayLength = Math.min(minSubArrayLength, j - i + 1);
                    }
                    
                    while (i < j && numsIToJ >= target) {
                        i++;
                        numsIToJ = i == 0 ? prefix[j] : prefix[j] - prefix[i - 1];
                        
                        if (numsIToJ >= target) {
                            if (minSubArrayLength == 0) {
                                minSubArrayLength = j - i + 1;
                            }
                            else {
                                minSubArrayLength = Math.min(minSubArrayLength, j - i + 1);
                            }
                        }
                    }
                }
            }
        }
            
        return minSubArrayLength;
    }
}