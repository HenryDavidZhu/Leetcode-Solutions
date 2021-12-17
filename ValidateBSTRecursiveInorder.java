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
    LinkedList<Integer> inorderTraversal = new LinkedList<Integer>();
    
    // 1. An inorder traversal and store it in a list.
    // 2. See if that list is sorted or not.
    public boolean isValidBST(TreeNode root) {
        inorderHelper(root);
        
        if (inorderTraversal.size() <= 1) {
            return true;
        }
        
        for (int i = 1; i < inorderTraversal.size(); i++) {
            if (inorderTraversal.get(i) <= inorderTraversal.get(i - 1)) {
                return false;
            }
        }
        
        return true;
    }
    
    public void inorderHelper(TreeNode root) {
        if (root == null) {
            return;
        }
        
        inorderHelper(root.left);
        inorderTraversal.add(root.val);
        inorderHelper(root.right);
    }
}