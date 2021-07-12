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
    private int findIndexOf(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        
        return -1;
    }

    int preorderIndex = 0;
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeHelper(preorder, inorder, 0, inorder.length - 1);
    }
    
    public TreeNode buildTreeHelper(int[] preorder, int[] inorder, int inorderStart, int inorderEnd) {
        if (inorderStart > inorderEnd) {
            return null;
        }
        
        TreeNode root = new TreeNode(preorder[preorderIndex++]);
        int inorderIndex = findIndexOf(inorder, root.val);
        
        root.left = buildTreeHelper(preorder, inorder, inorderStart, inorderIndex - 1);
        root.right = buildTreeHelper(preorder, inorder, inorderIndex + 1, inorderEnd);
        
        return root;
    }
}