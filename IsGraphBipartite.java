class NodeV2 {
    Integer node;
    boolean parentInSet1;
    
    public NodeV2(Integer node, boolean parentInSet1) {
        this.node = node;
        this.parentInSet1 = parentInSet1;
    }
}

class Solution {
    public boolean isBipartite(int[][] graph) {
        Map<Integer, Boolean> nodeToSet1 = new HashMap<Integer, Boolean>();
        boolean[] visitedNodes = new boolean[graph.length];
        
        for (int i = 0; i < graph.length; i++) {
            if (!visitedNodes[i]) {
                Queue<NodeV2> queue = new LinkedList<NodeV2>();
                queue.add(new NodeV2(i, false));

                while (!queue.isEmpty()) {
                    NodeV2 curr = queue.poll();

                    // Not bipartite, current node is adjacent to parent and both are
                    // in the same set.
                    if (nodeToSet1.containsKey(curr.node)) {
                        if (nodeToSet1.get(curr.node) == curr.parentInSet1) {
                            return false;
                        }
                    }

                    if (!visitedNodes[curr.node]) {
                        nodeToSet1.put(curr.node, !curr.parentInSet1);
                        visitedNodes[curr.node] = true;

                        for (int node : graph[curr.node]) {
                            queue.add(new NodeV2(node, nodeToSet1.get(curr.node)));
                        }
                    }
                }
            }
        }
        
        return true;
    }
}