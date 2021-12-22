
class Solution {
    // Goal: nums[i:j] % k = 0
    // Since nums[i:j] = nums[0:j] - nums[0:i)
    // nums[i:j] % k = nums[0:j] % k - nums[0:i) % k = 0
    // nums[0:i) % k = nums[0:j] % k
    // O(n) time, O(n) space
    public boolean checkSubarraySum(int[] nums, int k) {
        // Mapping that maps a prefix modulus (sum(nums[0:i]) % k) to the smallest possible value of i.
        HashMap<Integer, Integer> prefixModToFrequence = new HashMap<Integer, Integer>();
        int prefixSum = 0;
        int prefixModulus = 0;
        
        // Go through all the possible end indexes for our subarray
        for (int i = 0; i < nums.length; i++) {
            // Update the prefix sum and thus its modulus.
            prefixSum += nums[i];
            prefixModulus = prefixSum % k;
            
            Integer validPrefixSumIndex = prefixModToFrequence.getOrDefault(prefixModulus % k, -1);
            if (validPrefixSumIndex != -1 && i - validPrefixSumIndex > 1) {
                return true;
            }
            if (prefixModulus == 0 && i > 0) {
                return true;
            }
            
            // Update our mapping of prefix sums to their frequency.
            if (!prefixModToFrequence.containsKey(prefixModulus)) {
                prefixModToFrequence.put(prefixModulus, i);
            }
        }
        
        return false;
    }
}