class Solution {
    // O(n^2) time, O(n) space
    public int mctFromLeafValues(int[] arr) {
        // Greedy Algorithm: Pair up the smallest node with the smallest out of its
        // left and right neighbors (nodes that are NOT paired).
        LinkedList<Integer> nodeValues = new LinkedList<Integer>();
        for (int value : arr) {
            nodeValues.add(value);
        }
        
        int sum = 0;
        
        while (nodeValues.size() > 1) {
            // Find the minimum node that has not been paired yet.
            int minNode = Collections.min(nodeValues);
            int minNodeIndex = nodeValues.indexOf(minNode);
            
            // Find the minimum of its left and right neighbors.
            int pairNode = Integer.MAX_VALUE;
            int pairNodeIndex = -1;
            if (minNodeIndex > 0) {
                int leftNode = nodeValues.get(minNodeIndex - 1);
                
                if (leftNode < pairNode) {
                    pairNode = leftNode;
                    pairNodeIndex = minNodeIndex - 1;
                }
            }
            if (minNodeIndex < nodeValues.size() - 1) {
                int rightNode = nodeValues.get(minNodeIndex + 1);
                
                if (rightNode < pairNode) {
                    pairNode = rightNode;
                    pairNodeIndex = minNodeIndex + 1;
                }
            }
            
            // New node constructed = minNode * pairNode
            int newNode = minNode * pairNode;
            sum += newNode;
            
            // minNode and pairNode are now paired, so replace them with the new node constructed
            nodeValues.set(minNodeIndex, pairNode);
            nodeValues.remove(pairNodeIndex);
        }
        
        return sum;
    }
}