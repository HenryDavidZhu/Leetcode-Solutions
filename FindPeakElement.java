// O(log(n)) time, O(1) space.
class Solution {
    public int findPeakElement(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        
        int start = 0;
        int end = nums.length - 1;
        
        while (start <= end) {
            int mid = (start + end) / 2;

            if (mid > 0 && nums[mid] <= nums[mid - 1]) {
                end = mid - 1;
            }
            else if (mid < nums.length - 1 && nums[mid] <= nums[mid + 1]) {
                start = mid + 1;
            }
            else {
                return mid;
            }
        }
        
        return -1;
    }
}