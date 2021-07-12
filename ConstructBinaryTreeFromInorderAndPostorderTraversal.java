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
    // inorder: find nodes in left and right subtrees
    // postorder: find root nodes of left and right subtrees
    Map<Integer, Integer> inorderValToIndex = new HashMap<Integer, Integer>();
    Map<Integer, Integer> postorderValToIndex = new HashMap<Integer, Integer>();
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            inorderValToIndex.put(inorder[i], i);    
        }
        
        for (int j = 0; j < postorder.length; j++) {
            postorderValToIndex.put(postorder[j], j);
        }
        
        return buildTreeHelper(inorder, postorder, inorder.length - 1, 0, inorder.length - 1);
    }
    
    public TreeNode buildTreeHelper(int[] inorder, int[] postorder, int postorderIndex, int inorderStart, int inorderEnd) {
        if (inorderStart > inorderEnd) {
            return null;
        }
        
        TreeNode root = new TreeNode(postorder[postorderIndex]);
        
        // Find index of the root node in the inorder array
        int inorderRootIndex = inorderValToIndex.get(root.val);
        
        // Construct the right subtree:
        // Find the number of nodes in the right subtree
        int numRightNodes = inorderEnd - inorderRootIndex;
        int leftRootIndex = postorderValToIndex.get(root.val) - numRightNodes - 1;
        root.left = buildTreeHelper(inorder, postorder, leftRootIndex, inorderStart, inorderRootIndex - 1);

        // Compute the right subtree's root index
        int rightRootIndex = postorderValToIndex.get(root.val) - 1;
        root.right = buildTreeHelper(inorder, postorder, rightRootIndex, inorderRootIndex + 1, inorderEnd);
        
        return root;
    }
}