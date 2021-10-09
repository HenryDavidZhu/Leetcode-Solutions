class Solution {
    // O(n) time, O(1) space
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        
        int uniqueElements = 1;
        
        for (int i = 1; i < nums.length; i++) {
            // Found a unique element
            if (nums[i] != nums[i - 1]) {
                uniqueElements++;
                
                // Move the unique element to the correct index.
                nums[uniqueElements - 1] = nums[i];
            }
        }
        
        return uniqueElements;
    }
}