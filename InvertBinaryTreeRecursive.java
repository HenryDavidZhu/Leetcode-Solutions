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
    public TreeNode invertTree(TreeNode root) {
        return invertTreeHelper(root);
    }
    
    private TreeNode invertTreeHelper(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        TreeNode leftTemp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(leftTemp);
        
        return root;
    }
}