// O(n) time, O(n) space
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // Maps each value in the array to its corresponding index.
        Map<Integer, Integer> differenceMap = new HashMap<Integer, Integer>();
       
        for (int secondIndex = 0; secondIndex < nums.length; secondIndex++) {
            int secondNum = nums[secondIndex];
            
            // See if we have located the corresponding first number of the pair
            // in our map (target - secondNum).
            if (differenceMap.containsKey(target - secondNum)) {
                return new int[] {differenceMap.get(target - secondNum), secondIndex};
            }
            
            differenceMap.put(secondNum, secondIndex);
        }
        
        return new int[] {-1, -1};
    }
}