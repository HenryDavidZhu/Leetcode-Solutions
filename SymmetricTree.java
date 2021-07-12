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
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        TreeNode rootLeft = root.left;
        TreeNode invertedRootRight = invertTree(root.right);
        
        if (sameTree(rootLeft, invertedRootRight)) {
            return true;
        }
        return false;
    }
    
    private TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        TreeNode leftTree = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(leftTree);
        
        return root;
    }
    
    private boolean sameTree(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        
        if (root1 == null || root2 == null) {
            return false;
        }
        
        if (root1.val == root2.val && sameTree(root1.left, root2.left) && sameTree(root1.right, root2.right)) {
            return true;
        }
        
        return false;
    }
}