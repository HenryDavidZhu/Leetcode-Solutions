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
class UpgradedTreeNode {
    public TreeNode node;
    public String path;
    
    public UpgradedTreeNode(TreeNode node, String path) {
        this.node = node;
        this.path = path;
    }
}

// O(n) time, O(w) space
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> allPaths = new LinkedList<String>();
        
        Queue<UpgradedTreeNode> queue = new LinkedList<UpgradedTreeNode>();
        queue.add(new UpgradedTreeNode(root, String.valueOf(root.val)));
        
        while (!queue.isEmpty()) {
            UpgradedTreeNode curr = queue.poll();
            
            if (curr.node.left == null && curr.node.right == null) {
                allPaths.add(curr.path.toString());
            }
            
            if (curr.node.left != null) {
                String pathToLeft = curr.path;
                pathToLeft += ("->" + String.valueOf(curr.node.left.val));
                queue.add(new UpgradedTreeNode(curr.node.left, pathToLeft));
            }
            if (curr.node.right != null) {
                String pathToRight = curr.path;
                pathToRight += ("->" + String.valueOf(curr.node.right.val));
                queue.add(new UpgradedTreeNode(curr.node.right, pathToRight));                
            }
        }
        
        return allPaths;
    }
}