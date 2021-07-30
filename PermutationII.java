class Solution {
    List<List<Integer>> uniquePermutations = new LinkedList<List<Integer>>();
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> numsList = new LinkedList<Integer>();
        for (int num : nums) {
            numsList.add(num);
        }
        
        dfs(new LinkedList<Integer>(), nums.length, numsList);
        
        return uniquePermutations;
    }
    
    private void dfs(List<Integer> currPermutation, int permutationLength, List<Integer> numsLeft) {
        if (currPermutation.size() == permutationLength) {
            uniquePermutations.add(currPermutation);
        }
        
        Set<Integer> numsLeftSet = new HashSet<Integer>();
        for (Integer num : numsLeft) {
            numsLeftSet.add(num);
        }
        
        for (Integer nextNum : numsLeftSet) {
            // Use instantiation instead of backtracking
            List<Integer> newPermutation = new LinkedList<Integer>(currPermutation);
            newPermutation.add(nextNum);
            
            List<Integer> newNumsLeft = new LinkedList<Integer>(numsLeft);
            newNumsLeft.remove(nextNum);
            
            dfs(newPermutation, permutationLength, newNumsLeft);
        }
    }
}