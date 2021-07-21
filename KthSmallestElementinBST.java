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
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> inorderPath = new LinkedList<Integer>();
        inorderTraversal(root, inorderPath);
        
        return inorderPath.get(k - 1);
    }
    
    private void inorderTraversal(TreeNode root, List<Integer> inorderPath) {
        if (root == null) {
            return;
        }
        
        inorderTraversal(root.left, inorderPath);
        inorderPath.add(root.val);
        inorderTraversal(root.right, inorderPath);
    }
}