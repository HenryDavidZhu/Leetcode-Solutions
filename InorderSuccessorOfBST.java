/** 
 * Definition for a binary tree node. 
 * public class TreeNode { 
 *     int val; 
 *     TreeNode left; 
 *     TreeNode right; 
 *     TreeNode(int x) { val = x; } 
 * } 
 */ 
// O(n) time, O(1) space 
class Solution { 
    // Left Subtree, Root, Right Subtree 
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) { 
        TreeNode currNode = root; 
        TreeNode inorderSuccessor = null; 
         
        while (currNode != null) { 
            if (currNode.val > p.val) { 
                // Update the inorder successor if necessary. 
                if (inorderSuccessor == null || currNode.val < inorderSuccessor.val) { 
                    inorderSuccessor = currNode; 
                } 
                 
                // Pivot left. 
                currNode = currNode.left; 
            } 
            else { 
                // Pivot right. 
                currNode = currNode.right; 
            } 
        } 
         
        return inorderSuccessor; 
    } 
} 