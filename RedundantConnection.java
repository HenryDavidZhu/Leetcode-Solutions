class Solution {
    // Find the redundant connection between an undirected graph using union-find
    // on a disjoint set.
    public int[] findRedundantConnection(int[][] edges) {
        // nodeParents[i] = node i's parent
        int n = edges.length;
        int[] nodeParents = new int[n + 1];
        
        // Initially, all nodes are root nodes of distinct components, and to
        // indicate this, we set all of their parents to themselves.
        for (int i = 1; i <= n; i++) {
            nodeParents[i] = i;
        }
        
        int[] redundantConnection = new int[] {};
        
        // Check if every edge is a redundant connection.
        for (int[] edge : edges) {
            int nodeAComponent = find(edge[0], nodeParents);
            int nodeBComponent = find(edge[1], nodeParents);
            
            // If both of the edge's nodes are already in the same component,
            // the edge is a redundant connection.
            if (nodeAComponent == nodeBComponent) {
                redundantConnection = edge;
            }
            // When we have too nodes which are in different components, we
            // apply the union operation to merge them into the same component.
            else {
                nodeParents[nodeAComponent] = nodeBComponent;
            }
        }
        
        return redundantConnection;
    }
    
    // Determines the root node of the component a specified node is in
    private int find(int node, int[] nodeParents) {
        // Continue traversing up through the node's parents, until we
        // hit a node whose parent is itself (root node of component),
        // or we reached the start of a cycle
        while (nodeParents[node] != node) {
            node = nodeParents[node];
        }
        
        return node;
    }
}