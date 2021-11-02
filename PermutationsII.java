// Time: O(sum of P(N, k) from k = 1 to N), Space: O(N!)
class Solution {
    List<List<Integer>> uniquePerms = new LinkedList<List<Integer>>();
    HashSet<String> uniquePermsString = new HashSet<String>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        LinkedList<Integer> numList = new LinkedList<Integer>();
        for (int num : nums) {
            numList.add(num);
        }
        
        dfsTraversal(numList, nums.length, 0);
        
        return uniquePerms;
    }
    
    // DFS Traversal
    private void dfsTraversal(LinkedList<Integer> numList, int n, int index) {
        if (index == n) {
            if (!uniquePermsString.contains(numList.toString())) {
            uniquePerms.add(new LinkedList<Integer>(numList));
                uniquePermsString.add(numList.toString());
            }
        }
        
        for (int i = index; i < n; i++) {
            Collections.swap(numList, i, index);

            dfsTraversal(numList, n, index + 1);

            // Backtrack
            Collections.swap(numList, i, index);
        }
    }
}