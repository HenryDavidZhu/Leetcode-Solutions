class Solution {
    List<List<Integer>> tripletList = new LinkedList<List<Integer>>();
    
    // Find all non-duplicate triplets that sums up to 0
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length == 0) {
            return tripletList;
        }
        
        Arrays.sort(nums);
        
        int prevNum = nums[0];
        
        // Iterate through all possible first (base) numbers in our triplet
        for (int i = 0; i < (nums.length - 2); i++) {
            int currNum = nums[i];

            // Only analyze the current number if we haven't processed it already
            if (currNum != prevNum || i == 0) {
                findCorrespondingPair(nums, i);
            }
            
            prevNum = currNum;
        }
        
        return tripletList;
    }
    
    public void findCorrespondingPair(int[] numbers, int firstNumIndex) {
        int firstNum = numbers[firstNumIndex];
        int target = 0 - firstNum;
        
        int leftNumIndex = firstNumIndex + 1;
        int rightNumIndex = numbers.length - 1;
        
        // While we still have a range to search for
        while (leftNumIndex < rightNumIndex) {
            int leftNum = numbers[leftNumIndex];
            int rightNum = numbers[rightNumIndex];
            int sum = leftNum + rightNum;
            
            // If we have already found the element
            if (sum == target) {
                // Compute the triplet's values
                List<Integer> triplet = new LinkedList<Integer>();
                triplet.add(firstNum);
                triplet.add(leftNum);
                triplet.add(rightNum);
                
                // Add our triplet to our list of triplets
                tripletList.add(triplet);

                // Decrease our search range by moving the left and right indexes
                // to point to the next numbers that are not equal to the current
                // left and right numbers, respectively:
                
                // Narrow search range from left index
                leftNumIndex++;
                
                while (leftNumIndex < rightNumIndex && numbers[leftNumIndex] == numbers[leftNumIndex - 1]) {
                    leftNumIndex++;
                }
                
                // Narrow search range from right index
                rightNumIndex--;
                
                while (leftNumIndex < rightNumIndex && numbers[rightNumIndex] == numbers[rightNumIndex + 1]) {
                    rightNumIndex--;
                }
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
    }
}