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
        // Determine the paths from the root to nodes p and q
        List<TreeNode> pathToP = findPath(root, p);
        List<TreeNode> pathToQ = findPath(root, q);
        
        // Determine the LCA from the paths from the root to nodes p and q by
        // finding the last node that is shared in both paths.
        TreeNode lca = root;
        int endSearchIndex = Math.min(pathToP.size(), pathToQ.size());
        
        for (int i = 0; i < endSearchIndex; i++) {
            // Found an ancestor, update our LCA
            if (pathToP.get(i) == pathToQ.get(i)) {
                lca = pathToP.get(i);
            }
        }
        
        return lca;
    }
    
    // Find the path of the root to the target
    public List<TreeNode> findPath(TreeNode root, TreeNode target) {
        List<TreeNode> path = new LinkedList<TreeNode>();
        path.add(root);
        
        while (root != target) {
            // If root is too large, pivot to the left
            if (root.val > target.val) {
                root = root.left;
                path.add(root);
            }
            
            // If root is too small, pivot to the right
            if (root.val < target.val) {
                root = root.right;
                path.add(root);
            }
        }
        
        return path;
    }
}