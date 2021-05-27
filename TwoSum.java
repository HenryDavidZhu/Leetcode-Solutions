class Solution {
    public int[] twoSum(int[] nums, int target) {
        // key + nums[value] = target
        Map<Integer, Integer> differenceHash = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {
            // See if we have found the pair from our mapping and that we don't use the
            // same element.
            if (differenceHash.containsKey(nums[i]) && differenceHash.get(nums[i]) != i) {
                return new int[] {differenceHash.get(nums[i]), i};
            }
            
            differenceHash.put(target - nums[i], i);
        }
        
        return new int[] {-1, -1};
    }
}