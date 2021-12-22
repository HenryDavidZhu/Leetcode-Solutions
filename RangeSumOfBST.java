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
    // O(n) time complexity, O(n) space 
    public int rangeSumBST(TreeNode root, int low, int high) { 
        Queue<TreeNode> queue = new LinkedList<TreeNode>(); 
        queue.add(root); 
         
        int rangeSum = 0; 
         
        while (!queue.isEmpty()) { 
            TreeNode curr = queue.poll(); 
             
            // See if curr is within the range. 
            if (curr.val >= low && curr.val <= high) { 
                rangeSum += curr.val; 
            } 
             
            // Add the current's left and right children (if they exist). 
            if (curr.left != null) { 
                queue.add(curr.left); 
            } 
            if (curr.right != null) { 
                queue.add(curr.right); 
            } 
        } 
         
        return rangeSum; 
    } 
} 