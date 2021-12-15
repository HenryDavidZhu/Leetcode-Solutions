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
// O(n) time and O(n) space, Inorder Traversal + Linear Search
class Solution {
    LinkedList<Integer> inorderList = new LinkedList<Integer>();
        
    public void inorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        
        inorderTraversal(root.left);
        inorderList.add(root.val);
        inorderTraversal(root.right);
    }
    
    public int closestValue(TreeNode root, double target) {
        inorderTraversal(root);
        
        if (target < inorderList.get(0)) {
            return inorderList.get(0);
        }
        if (target > inorderList.get(inorderList.size() - 1)) {
            return inorderList.get(inorderList.size() - 1);
        }
        
        for (int i = 0; i < inorderList.size() - 1; i++) {
            int j = i + 1;
            if (inorderList.get(i) <= target && inorderList.get(j) >= target) {
                double iDistToTarget = Math.abs(inorderList.get(i) - target);
                double jDistToTarget = Math.abs(inorderList.get(j) - target);
                
                if (iDistToTarget < jDistToTarget) {
                    return inorderList.get(i);
                }
                else {
                    return inorderList.get(j);
                }
            }
        }

        return -1;
    }
}