class Solution {
    public boolean containsDuplicate(int[] nums) {
        // O(n) time, O(n) space
        Set<Integer> numsSet = new HashSet<Integer>();
        for (int num : nums) {
            numsSet.add(num);
        }
        
        if (numsSet.size() == nums.length) {
            return false;
        }
        
        return true;
    }
}