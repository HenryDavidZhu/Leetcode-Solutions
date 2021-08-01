class UnionFind {
    // parents[i] = parent node of node i.
    // If a node is the root node of a component, we define its parent
    // to be itself.
    int[] parents;
    
    public UnionFind(int n) {
        this.parents = new int[n];
        
        for (int i = 0; i < n; i++) {
            this.parents[i] = i;
        }
    }
    
    // Merges two nodes into the same component.
    public void union(int node1, int node2) {
        int node1Component = find(node1);
        int node2Component = find(node2);
        
        this.parents[node1Component] = node2Component;
    }
    
    // Returns the component that a node is in.
    public int find(int node) {
        while (this.parents[node] != node) {
            node = this.parents[node];
        }
        
        return node;
    }
}

class Solution {
    public int minimumCost(int n, int[][] connections) {
        UnionFind uf = new UnionFind(n + 1);
        
        // Sort edges by increasing cost.
        Arrays.sort(connections, new Comparator<int[]>() {
            @Override
            public int compare(final int[] a1, final int[] a2) {
                return a1[2] - a2[2];
            }
        });
        
        int edgeCount = 0;
        int connectionIndex = 0;
        int weight = 0;
        
        // Greedy algorithm: Choose the edge with the smallest weight 
        // which does not form a cycle. We know that an edge between 
        // two nodes will result in a cycle if those nodes are already 
        // in the same component.
        for (int i = 0; i < connections.length; i++) {
            int[] connection = connections[i];
            int nodeAComponent = uf.find(connection[0]);
            int nodeBComponent = uf.find(connection[1]);
            
            if (nodeAComponent != nodeBComponent) {
                uf.union(nodeAComponent, nodeBComponent);
                weight += connection[2];
                edgeCount++;
            }
            
            if (edgeCount == n - 1) {
                break;
            }
        }
        
        // MST, by definition, must have (n - 1) edges.
        if (edgeCount == n - 1) {
            return weight;
        }
        return -1;
    }
}