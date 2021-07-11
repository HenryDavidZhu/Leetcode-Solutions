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
        if (root == null) {
            return root;
        }
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            TreeNode currNode = queue.poll();
            TreeNode leftTemp = currNode.left;
            currNode.left = currNode.right;
            currNode.right = leftTemp;
            
            if (currNode.left != null) {
                queue.add(currNode.left);
            }
            if (currNode.right != null) {
                queue.add(currNode.right);
            }
        }
        
        return root;
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