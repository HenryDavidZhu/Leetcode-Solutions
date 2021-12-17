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
// O(n) time and O(n) space
class Solution {
    Integer prev = null;
    
    public boolean isValidBST(TreeNode root) {
        return inorderHelper(root);
    }
    
    public boolean inorderHelper(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        if (!inorderHelper(root.left)) {
            return false;
        }
        
        if (prev != null) {
            if (root.val <= prev) {
                return false;
            }
        }
        prev = root.val;
        
        if (!inorderHelper(root.right)) {
            return false;
        }
        
        return true;
    }
}