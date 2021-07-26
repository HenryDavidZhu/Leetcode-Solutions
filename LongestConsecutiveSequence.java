class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        
        // i : length of longest consecutive sequence starting at index i
        Map<Integer, Integer> dp = new HashMap<Integer, Integer>();
        
        // O(1) lookup to see if a number is in nums
        Set<Integer> numsSet = new HashSet<Integer>();
        
        for (int num : nums) {
            numsSet.add(num);
        }
        
        int maxSequenceLen = 1;
        
        for (int i = 0; i < nums.length; i++) {
            int curr = nums[i];
            int sequenceLength = 0;
            while (numsSet.contains(curr)) {
                if (dp.containsKey(curr)) {
                    sequenceLength += dp.get(curr);
                    break;
                }
                else {
                    sequenceLength++;
                    curr++;
                }
            }
            
            dp.put(nums[i], sequenceLength);
            maxSequenceLen = Math.max(maxSequenceLen, sequenceLength);
        }
        
        return maxSequenceLen;
    }
}