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
    public boolean isValidBST(TreeNode root) {
        return isValidBSTDFS(root, null, null, null);
    }
    
    public boolean isValidBSTDFS(TreeNode currNode, TreeNode parentNode, Integer low,                                        Integer high) {
        // Edge Case
        if (currNode == null) {
            return true;
        }
        
        // Ensure that the current node is within range
        if (low != null && currNode.val <= low || high != null && currNode.val >= high) {
            return false;
        }
        
        return isValidBSTDFS(currNode.left, currNode, low, currNode.val) &&
               isValidBSTDFS(currNode.right, currNode, currNode.val, high);
    }
}