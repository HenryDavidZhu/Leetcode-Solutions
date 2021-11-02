// Time Complexity: O(n log n), Space Complexity: O(1)
class Solution {
    public int findDuplicate(int[] nums) {
        if (nums.length == 2) {
            return nums[0];
        }
       
        // Sort array -> O(n log n)
        Arrays.sort(nums);
       
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return nums[i];
            }
        }
       
        return -1;
    }
}