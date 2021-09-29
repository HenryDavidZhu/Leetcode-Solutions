/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        
        StringBuilder serializedBST = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr != null) {
                serializedBST.append(curr.val + ",");
            }
            else {
                serializedBST.append("n,");
            }
            
            if (curr != null) {
                queue.add(curr.left);
                queue.add(curr.right);
            }
        }
        
        serializedBST.deleteCharAt(serializedBST.length() - 1);
        return serializedBST.toString();
    }
    
    // Constructs a TreeNode from its String value.
    private TreeNode constructTreeNode(String value) {
        if (!value.equals("n")) {
            return new TreeNode(Integer.parseInt(value));
        }
        
        return null;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 0) {
            return null;
        }
        
        String[] nodeValues = data.split(",");
        
        Queue<String> nodeValueQueue = new LinkedList<String>();
        for (String value : nodeValues) {
            nodeValueQueue.add(value);
        }
        
        Queue<TreeNode> nodesToVisit = new LinkedList<TreeNode>();
        String rootValueStr = nodeValueQueue.isEmpty() ? null : nodeValueQueue.poll();
        TreeNode root = constructTreeNode(rootValueStr);

        nodesToVisit.add(root);
        
        while (!nodesToVisit.isEmpty()) {
            TreeNode curr = nodesToVisit.poll();
            
            String leftValueStr = nodeValueQueue.isEmpty() ? null : nodeValueQueue.poll();
            TreeNode left = constructTreeNode(leftValueStr);

            String rightValueStr = nodeValueQueue.isEmpty() ? null : nodeValueQueue.poll();
            TreeNode right = constructTreeNode(rightValueStr);
            
            curr.left = left;
            curr.right = right;
            
            if (curr.left != null) {
                nodesToVisit.add(curr.left);
            }
            if (curr.right != null) {
                nodesToVisit.add(curr.right);
            }
        }
        
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// String tree = ser.serialize(root);
// TreeNode ans = deser.deserialize(tree);
// return ans;