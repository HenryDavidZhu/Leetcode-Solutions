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

enum Direction {
    LEFT,
    RIGHT
}

class Parent {
    TreeNode node;
    Direction direction;
    
    public Parent(TreeNode node, Direction direction) {
        this.node = node;
        this.direction = direction;
    }
}

class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        // Edge Case: Tree is empty
        if (root == null) {
            return root;
        }
        
        // Find the node that we want to delete.
        TreeNode nodeToDelete = root;
        
        // After we have found the path from the root to the node we want
        // to delete, keep track of each node in that path's parent and
        // whether the parent is to the LEFT or RIGHT of that node.
        Map<TreeNode, Parent> pathNodeToParent = new HashMap<TreeNode, Parent>();
        
        while (nodeToDelete != null) {
            //System.out.println("nodeToDelete.val = " + nodeToDelete.val);
            //System.out.println("nodeToDelete.left = " + (nodeToDelete.left != null));
            // Found the node that we want to delete.
            if (nodeToDelete.val == key) {
                break;
            }
            
            // Search the left subtree.
            if (nodeToDelete.val > key) {
                if (nodeToDelete.left != null) {
                    pathNodeToParent.put(nodeToDelete.left, new Parent(nodeToDelete, Direction.RIGHT));
                }
                
                nodeToDelete = nodeToDelete.left;
            }
            
            // Search the right subtree.
            else {       
                if (nodeToDelete.right != null) {
                    pathNodeToParent.put(nodeToDelete.right, new Parent(nodeToDelete, Direction.LEFT));
                }
                
                nodeToDelete = nodeToDelete.right;
            }
        }
        
        // Edge Case: the node we want to delete does not exist.
        if (nodeToDelete == null) {
            return root;
        }
        
        // Delete the node.
        // Case 1: The node is a leaf node (has no children).
        if (nodeToDelete.left == null && nodeToDelete.right == null) {
            if (nodeToDelete == root) {
                return null;
            }
            
            Parent nodeToDeleteParent = pathNodeToParent.get(nodeToDelete);
            
            // The node's parent is to its LEFT
            if (nodeToDeleteParent.direction == Direction.LEFT) {
                nodeToDeleteParent.node.right = null;
            }
            
            // The node's parent is to its right
            if (nodeToDeleteParent.direction == Direction.RIGHT) {
                nodeToDeleteParent.node.left = null;
            }
        }
        // Case 2: The node has only a left or only a right child.
        // Left child
        else if (nodeToDelete.left != null && nodeToDelete.right == null) {
            Parent nodeToDeleteParent = pathNodeToParent.get(nodeToDelete);
            TreeNode leftChild = nodeToDelete.left;
            
            if (nodeToDeleteParent == null) {
                root = leftChild;
                return root;
            }

            // The node's parent is to its LEFT
            if (nodeToDeleteParent.direction == Direction.LEFT) {
                nodeToDeleteParent.node.right = leftChild;
            }
            
            // The node's parent is to its right
            if (nodeToDeleteParent.direction == Direction.RIGHT) {
                nodeToDeleteParent.node.left = leftChild;
            }
        }
        // Right child
        else if (nodeToDelete.left == null && nodeToDelete.right != null) {
            Parent nodeToDeleteParent = pathNodeToParent.get(nodeToDelete);
            TreeNode rightChild = nodeToDelete.right;
            
            if (nodeToDeleteParent == null) {
                root = rightChild;
                return root;
            }
            
            // The node's parent is to its LEFT
            if (nodeToDeleteParent.direction == Direction.LEFT) {
                nodeToDeleteParent.node.right = rightChild;
            }
            
            // The node's parent is to its right
            if (nodeToDeleteParent.direction == Direction.RIGHT) {
                nodeToDeleteParent.node.left = rightChild;
            }
        }
        // Case 3: The node has both a left and a right child.
        else {
            // Replace the node with its inorder successor's value.
            // Node has a right subtree, inorder successor is the right subtree's
            // left most node.
            TreeNode inorderSuccessor = nodeToDelete.right;
            TreeNode inorderSuccessorParent = nodeToDelete;
            boolean inorderSuccessorToRight = true;
                
            while (inorderSuccessor.left != null) {
                inorderSuccessorToRight = false;
                inorderSuccessorParent = inorderSuccessor;
                inorderSuccessor = inorderSuccessor.left;
            }
                
            // Replace the node to be deleted with its inorder successor's value
            int inorderSuccessorVal = inorderSuccessor.val;
                
            // Delete the inorder successor
            if (inorderSuccessorToRight) {
                nodeToDelete.right = inorderSuccessor.right;
            }
            else {
                inorderSuccessorParent.left = inorderSuccessor.right;
            }
                
            nodeToDelete.val = inorderSuccessorVal;
        }
        
        return root;
    }
}