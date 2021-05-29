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
    // Path to P: 3, 5
    // Path to Q: 3, 5, 2, 4
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Tree is empty, no LCA exists
        if (root == null) {
            return null;
        }
        
        // Calculate the paths from the root to p to q
        List<TreeNode> rootToP = new LinkedList<TreeNode>();
        List<TreeNode> rootToQ = new LinkedList<TreeNode>();
        
        findPath(root, p, rootToP);
        findPath(root, q, rootToQ);
        
        // Determine the last shared node in the paths from p to q
        int shortestPathLen = Math.min(rootToP.size(), rootToQ.size());
        TreeNode lca = root;
        
        for (int i = 0; i < shortestPathLen; i++) {
            // Found an ancestor, update the LCA
            if (rootToP.get(i) == rootToQ.get(i)) {
                lca = rootToP.get(i);
            }
        }
        
        return lca;
    }
    
    // Recursive function that determines the path from a given root to a node (DFS)
    private boolean findPath(TreeNode root, TreeNode target, List<TreeNode> path) {
        if (root == null) {
            return false;
        }
        
        path.add(root);
        
        if (root == target) {
            return true;
        }
        
        // Found target node in either the left or right subtree
        if (findPathToNode(root.left, target, path) || findPathToNode(root.right, target, path)) {
            return true;
        }
        
        // Didn't find target node in the tree, need to backtrack/remove it from path
        path.remove(path.size() - 1);
        
        return false;
    }
    
}