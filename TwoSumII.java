class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int leftNumIndex = 0;
        int rightNumIndex = numbers.length - 1;
        
        // While we still have a range to search for
        while (leftNumIndex < rightNumIndex) {
            int sum = numbers[leftNumIndex] + numbers[rightNumIndex];
            
            // If we have already found the element
            if (sum == target) {
                return new int[] {leftNumIndex + 1, rightNumIndex + 1};
            }
            
            // Our sum is too small, increment the left pointer
            if (sum < target) {
                leftNumIndex++;
            }
            
            // Our sum is too great, decrement the right pointer
            if (sum > target) {
                rightNumIndex--;
            }
        }
        
        return new int[] {-1, -1};
    }
}