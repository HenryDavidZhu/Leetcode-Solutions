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
    Map<Integer, TreeNode> valToTreeNode = new HashMap<Integer, TreeNode>();
    List<Integer> inorderTraversalPath = new LinkedList<Integer>();
    List<Integer> correctInorderTraversalPath;
    
    public void recoverTree(TreeNode root) {
        inorderTraversal(root);
        
        correctInorderTraversalPath = new LinkedList<Integer>(inorderTraversalPath);
        Collections.sort(correctInorderTraversalPath);
        
        TreeNode nodeToSwap1 = null;
        TreeNode nodeToSwap2 = null;
        
        for (int i = 0; i < inorderTraversalPath.size(); i++) {
            TreeNode currInorder = valToTreeNode.get(inorderTraversalPath.get(i));
            TreeNode correctCurrInorder = valToTreeNode.get(correctInorderTraversalPath.get(i));
            
            if (currInorder != correctCurrInorder) {
                if (nodeToSwap1 == null) {
                    nodeToSwap1 = currInorder;
                }
                else if (nodeToSwap2 == null) {
                    nodeToSwap2 = currInorder;
                }
            }
        }
        
        int currNode1Val = nodeToSwap1.val;
        nodeToSwap1.val = nodeToSwap2.val;
        nodeToSwap2.val = currNode1Val;
    }
    
    public void inorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        
        inorderTraversal(root.left);

        inorderTraversalPath.add(root.val);
        valToTreeNode.put(root.val, root);
        
        inorderTraversal(root.right);
    }
}