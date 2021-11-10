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
// O(n) time, O(log n) space (because we use DFS)
class Solution {
    public TreeNode generateTree(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        
        int mid = (start + end)/2;
        TreeNode root = new TreeNode(nums[mid]);
        
        root.left = generateTree(nums, start, mid - 1);
        root.right = generateTree(nums, mid + 1, end);
        
        return root;
    }
        
    public TreeNode sortedArrayToBST(int[] nums) {
        return generateTree(nums, 0, nums.length - 1);
    }
}