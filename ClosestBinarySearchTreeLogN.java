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
// O(log(n)) time and O(1) space, Binary Search
class Solution {
    public int closestValue(TreeNode root, double target) {
        TreeNode curr = root;
        int closestValue = root.val;
        
        while (curr != null) {
            double savedClosestToTarget = Math.abs(closestValue - target);
            double currClosestToTarget = Math.abs(curr.val - target);
            if (currClosestToTarget < savedClosestToTarget) {
                closestValue = curr.val;
            }
            
            if (curr.val == target) {
                return curr.val;
            }
            if (curr.val < target) {
                curr = curr.right;
                continue;
            }
            if (curr.val > target) {
                curr = curr.left;
                continue;
            }
        }
        
        return closestValue;
    }
}