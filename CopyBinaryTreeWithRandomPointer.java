/**
 * Definition for Node.
 * public class Node {
 *     int val;
 *     Node left;
 *     Node right;
 *     Node random;
 *     Node() {}
 *     Node(int val) { this.val = val; }
 *     Node(int val, Node left, Node right, Node random) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *         this.random = random;
 *     }
 * }
 */

class Solution {
    Map<Node, NodeCopy> visitedNodes = new HashMap<Node, NodeCopy>();
    
    public NodeCopy copyRandomBinaryTree(Node root) {
        // O(n) time, O(n) space
        if (root == null) {
            return null;
        }
        if (visitedNodes.containsKey(root)) {
            return visitedNodes.get(root);
        }
        
        NodeCopy copiedRoot = new NodeCopy(root.val);
        visitedNodes.put(root, copiedRoot);
        
        copiedRoot.left = copyRandomBinaryTree(root.left);
        copiedRoot.right = copyRandomBinaryTree(root.right);
        copiedRoot.random = copyRandomBinaryTree(root.random);
        
        return copiedRoot;
    }
}