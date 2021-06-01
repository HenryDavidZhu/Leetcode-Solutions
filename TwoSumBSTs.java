/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    boolean targetPairExists = false;
    
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        // Maps n_2 to n_1
        Map<Integer, Integer> targetPairs = new HashMap<Integer, Integer>();
        
        // Explore the 1st tree and fill out our target pair mapping.
        recursiveDFS(root1, target, true, targetPairs);
        
        // Explore the 2nd tree and see if we have found our target pair
        recursiveDFS(root2, target, false, targetPairs);
        
        return targetPairExists;
    }
    
    public void recursiveDFS(TreeNode currNode, int target, boolean fillPairMapping,
                             Map<Integer, Integer> targetPairs) {
        // Edge Case: We have reached the end of a branch.
        if (currNode == null) {
            return;
        }
        
        // Compute the corresponding value that needes to be paired with the current
        // node's value in order for the pair's sum to equal the target value.
        int correspondingVal = target - currNode.val;
        
        // If we are exploring the 1st tree, update our pair mapping with the new pair.
        if (fillPairMapping) {
            targetPairs.put(correspondingVal, currNode.val);
        }
        
        // If we are now exploring the 2nd tree, see if the current node's value
        // can be paired with one of the values in the 1st tree by looking at
        // our pair mapping.
        if (!fillPairMapping) {
            if (targetPairs.containsKey(currNode.val)) {
                targetPairExists = true;
                return;
            }
        }
        
        // Explore the left and right children.
        recursiveDFS(currNode.left, target, fillPairMapping, targetPairs);
        recursiveDFS(currNode.right, target, fillPairMapping, targetPairs);
    }
}