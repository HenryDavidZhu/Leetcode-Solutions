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
    HashSet<Long> subtreeASums = new HashSet<Long>();

    public int maxProduct(TreeNode root) {
        // We split the larger tree into two smaller subtrees:
        // Subtree A and Subtree B.
        //
        // Subtree A is the subtree with a different root than
        // the original root of the tree, and Subtree B is
        // just the original tree minus Subtree A.
        //
        // If we know the sum of the entire tree and the sum of
        // Subtree A, we know that the sum of Subtree B is going
        // to be (sum of the entire tree) - (sum of Subtree A)
        //
        // 1. Figure out the sum of the entire tree and all possible
        // subtrees A using DFS traversal.
        //
        // 2. For each subtree A's sum, figure out subtree B's sum and the product
        // of subtree A and subtree B's sums. Update the max product.
        long originalTreeSum = sumTree(root);

        long maxProduct = -1;
        for (Long subtreeASum : subtreeASums) {
            long subtreeBSum = originalTreeSum - subtreeASum;

            long product = subtreeASum * subtreeBSum;
            if (product > maxProduct) {
                maxProduct = product;
            }
        }

        return (int) (maxProduct % (1e9 + 7));
    }

    public long sumTree(TreeNode root) {
        if (root == null) {
            return 0l;
        }

        long treeSum = Long.valueOf(root.val) + sumTree(root.left) + sumTree(root.right);
        subtreeASums.add(treeSum);
        return treeSum;
    }
}
