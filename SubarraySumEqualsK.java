class Solution { 
    // O(n) time, O(n) space 
    public int subarraySum(int[] nums, int k) { 
        // Mapping that maps a prefix sum (sum(nums[0:i])) to its frequency. 
        HashMap<Integer, Integer> prefixSumToFrequency = new HashMap<Integer, Integer>(); 
        int result = 0; 
        int prefixSum = 0; 
         
        // Go through all the possible end indexes for our subarray 
        for (int i = 0; i < nums.length; i++) { 
            // Update the prefix sum. 
            prefixSum += nums[i]; 
             
            Integer validSubarraysEndAtI = prefixSumToFrequency.getOrDefault(prefixSum - k, 0); 
            result += validSubarraysEndAtI; 
             
            if (prefixSum == k) { 
                result++; 
            } 
             
            // Update our mapping of prefix sums to their frequency. 
            Integer newPrefixSumFreq = prefixSumToFrequency.getOrDefault(prefixSum, 0); 
            newPrefixSumFreq++; 
            prefixSumToFrequency.put(prefixSum, newPrefixSumFreq); 
        } 
         
        return result; 
    } 
} 