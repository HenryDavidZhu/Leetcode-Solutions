/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return recursiveDFS(root, p, q);
    }
    
    // Helper function to find LCA.
    private TreeNode recursiveDFS(TreeNode root, TreeNode p, TreeNode q) {
        // Base Cases:
        // Empty tree is not going to contain LCA.
        if (root == null) {
            return null;
        }
        // If the root is already one of the targets, LCA is the root.
        if (root == p || root == q) {
            return root;
        }
        
        // Find the LCAs INSIDE the left and right subtrees
        TreeNode leftLCA = recursiveDFS(root.left, p, q);
        TreeNode rightLCA = recursiveDFS(root.right, p, q);
        
        // Case 1 - left subtree contains p and right subtree contains q,
        // or vice versa: Root is the LCA.
        if (leftLCA != null && rightLCA != null) {
            return root;
        }
        
        // Case 2 - Only left subtree contains both p and q, thus LCA is
        // in the left subtree.
        if (leftLCA != null && rightLCA == null) {
            return leftLCA;
        }
        
        // Case 3 - Only right subtree contains both p and q, thus LCA is
        // in the right subtree.
        if (leftLCA == null && rightLCA != null) {
            return rightLCA;
        }
        
        return null;
    }
}