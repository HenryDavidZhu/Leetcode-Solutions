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
class TreeNode2 {
    TreeNode treeNode;
    int xPos;
    
    public TreeNode2(TreeNode treeNode, int xPos) {
        this.treeNode = treeNode;
        this.xPos = xPos;
    }
}

class Solution {
    Map<Integer, List<TreeNode2>> xPosToNodes = new TreeMap<Integer, List<TreeNode2>>();
    
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) {
            return new LinkedList<List<Integer>>();
        }
        
        Queue<TreeNode2> queue = new LinkedList<TreeNode2>();
        TreeNode2 rootAndXPos = new TreeNode2(root, 0);
        queue.add(rootAndXPos);
        
        while (!queue.isEmpty()) {
            TreeNode2 curr = queue.poll();
            List<TreeNode2> xPosNodes = new LinkedList<TreeNode2>();
            if (xPosToNodes.containsKey(curr.xPos)) {
                xPosNodes = xPosToNodes.get(curr.xPos);
            }
            
            xPosNodes.add(curr);
            xPosToNodes.put(curr.xPos, xPosNodes);
            
            if (curr.treeNode.left != null) {
                TreeNode2 left = new TreeNode2(curr.treeNode.left, curr.xPos - 1); 
                queue.add(left);
            }
            if (curr.treeNode.right != null) {
                TreeNode2 right = new TreeNode2(curr.treeNode.right, curr.xPos + 1); 
                queue.add(right);                
            }
        }
        
        List<List<Integer>> verticalOrderList = new LinkedList<List<Integer>>();
        for (Map.Entry<Integer, List<TreeNode2>> xPosToNodesEntry : xPosToNodes.entrySet()) {
            List<Integer> xPosNodeValues = new LinkedList<Integer>();
            for (TreeNode2 treeNode2 : xPosToNodesEntry.getValue()) {
                xPosNodeValues.add(treeNode2.treeNode.val);
            }
            
            verticalOrderList.add(xPosNodeValues);
        }
        
        return verticalOrderList;
    }
}