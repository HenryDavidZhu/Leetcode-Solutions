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
class NodeWithLevel {
    TreeNode node;
    Integer level;
    
    public NodeWithLevel(TreeNode node, Integer level) {
        this.node = node;
        this.level = level;
    }
}

class Solution {
    // O(n) time, O(n) space
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new LinkedList<List<Integer>>();
        }
        
        // Store the BFS traversal.
        List<List<Integer>> bfsTraversal = new LinkedList<List<Integer>>();
        Queue<NodeWithLevel> queue = new LinkedList<NodeWithLevel>();
        queue.add(new NodeWithLevel(root, 0));
        
        while (!queue.isEmpty()) {
            NodeWithLevel curr = queue.poll();
            
            // Add the node to our BFS traversal list.
            // We haven't created a list for our current node yet.
            if (curr.level >= bfsTraversal.size()) {
                List<Integer> newCurrLevel = new LinkedList<Integer>();
                newCurrLevel.add(curr.node.val);
                bfsTraversal.add(newCurrLevel);
            }
            // If we have created a list for our current node.
            else {
                // Insert the current node into the list for the current level.
                List<Integer> currLevel = bfsTraversal.get(curr.level);
                currLevel.add(curr.node.val);
                
                // Update the current level's list in our BFS traversal storage.
                bfsTraversal.set(curr.level, currLevel);
            }
            
            // Add the current node's children to the queue.
            if (curr.node.left != null) {
                queue.add(new NodeWithLevel(curr.node.left, curr.level + 1));
            }
            if (curr.node.right != null) {
                queue.add(new NodeWithLevel(curr.node.right, curr.level + 1));
            }
        }
        
        // Reverse the pdd numbered levels.
        for (int i = 0; i < bfsTraversal.size(); i++) {
            if (i % 2 == 1) {
                List<Integer> oddLevelList = bfsTraversal.get(i);
                Collections.reverse(oddLevelList);
                bfsTraversal.set(i, oddLevelList);
            }
        }
        
        return bfsTraversal;
    }
}