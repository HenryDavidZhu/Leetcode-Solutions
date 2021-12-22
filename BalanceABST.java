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
// O(n) time, O(n) space 
class Solution { 
    LinkedList<TreeNode> inorderPath = new LinkedList<TreeNode>(); 
     
    public TreeNode balanceBST(TreeNode root) { 
        inorderTraversal(root); 
        return constructBalancedBST(0, inorderPath.size() - 1); 
    } 
     
    public void inorderTraversal(TreeNode root) { 
        if (root == null) { 
            return; 
        } 
         
        inorderTraversal(root.left); 
        inorderPath.add(root); 
        inorderTraversal(root.right); 
    } 
     
    public TreeNode constructBalancedBST(int start, int end) { 
        if (end < start) { 
            return null; 
        } 
         
        int mid = (start + end) / 2; 
        TreeNode root = inorderPath.get(mid); 
         
        root.left = constructBalancedBST(start, mid - 1); 
        root.right = constructBalancedBST(mid + 1, end); 
         
        return root; 
    } 
} 