class Solution {
    List<List<Integer>> permutations = new LinkedList<List<Integer>>();
        
    public List<List<Integer>> permute(int[] nums) {
        dfsHelper(nums, 0, nums.length - 1);
        return permutations;
    }
    
    private void dfsHelper(int[] nums, int currIndex, int lastIndex) {
        if (currIndex == lastIndex) {
            List<Integer> numsList = new LinkedList<Integer>();
            for (int num : nums) {
                numsList.add(num);
            }
            permutations.add(numsList);
            return;
        }
        
        for (int i = currIndex; i <= lastIndex; i++) {
            // Swap currIndex with i
            int temp = nums[currIndex];
            nums[currIndex] = nums[i];
            nums[i] = temp;
            
            dfsHelper(nums, currIndex + 1, lastIndex);
            
            // Backtrack: Swap back currIndex with i
            int temp2 = nums[currIndex];
            nums[currIndex] = nums[i];
            nums[i] = temp2;
        }
    }
}