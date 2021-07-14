/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};
*/

class Solution {
    public Node inorderSuccessor(Node node) {
        Node successor = null;
        
        // If the node is a left child, check its parent
        if (node.parent != null && node.parent.val > node.val) {
            if (successor == null || node.parent.val < successor.val) {
                successor = node.parent;
            }
        }
        
        // If the node is a right child, find the node's nearest parent to its right
        if (node.parent != null && node.parent.val < node.val) {
            Node currNode = node.parent;
            
            while (currNode != null) {
                // Found the node's nearest parent to its right
                if (currNode.parent != null && currNode.val < currNode.parent.val) {
                    currNode = currNode.parent; 
                    
                    if (successor == null || currNode.val < successor.val) {
                        successor = currNode;
                    }
                } else {
                    currNode = currNode.parent;
                }
            }
        }
        
        // Search leftwards from the right child
        Node currNodeRightTree = node.right;
        
        while (currNodeRightTree != null) {
            if (currNodeRightTree.val > node.val) {
                if (successor == null || currNodeRightTree.val < successor.val) {
                    successor = currNodeRightTree;
                }
            }
            
            currNodeRightTree = currNodeRightTree.left;
        }
        
        return successor;
    }
}