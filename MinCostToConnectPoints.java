class Edge {
    int pointAIndex;
    int pointBIndex;
    int distance; // Manhattan distance.
    
    public Edge(int pointAIndex, int pointBIndex, int distance) {
        this.pointAIndex = pointAIndex;
        this.pointBIndex = pointBIndex;
        this.distance = distance;
    }
}

class UnionFind {
    int[] parents;
    
    public UnionFind(int n) {
        this.parents = new int[n];
        
        for (int i = 0; i < n; i++) {
            this.parents[i] = i;
        }
    }
    
    public void union(int pointA, int pointB) {
        int pointAComponent = find(pointA);
        int pointBComponent = find(pointB);
        
        this.parents[pointAComponent] = pointBComponent;
    }
    
    public int find(int point) {
        while (this.parents[point] != point) {
            point = this.parents[point];
        }
        
        return point;
    }
}

class Solution {
    public int minCostConnectPoints(int[][] points) {
        if (points.length == 1) {
            return 0;
        }
        
        List<Edge> edges = new LinkedList<Edge>();
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int[] pointA = points[i];
                int[] pointB = points[j];
                int distance = Math.abs(pointB[0] - pointA[0]) + Math.abs(pointB[1] - pointA[1]);
                    
                edges.add(new Edge(i, j, distance));
            }
        }
        
        // Sort all the edges by increasing distance.
        Collections.sort(edges, new Comparator<Edge>() {
            @Override
            public int compare(Edge pointA, Edge pointB) {
                return pointA.distance - pointB.distance;
            }
        });
        
        // Kruskal's algorithm using Union-Find data structure.
        UnionFind uf = new UnionFind(points.length);
        int edgeCount = 0;
        int minCost = 0;
        
        for (int k = 0; k < edges.size(); k++) {
            Edge edge = edges.get(k);
            
            // Add the edge if it does not form a cycle with the existing MST.
            int pointAIndex = edge.pointAIndex;
            int pointBIndex = edge.pointBIndex;
            
            if (uf.find(pointAIndex) != uf.find(pointBIndex)) {
                uf.union(pointAIndex, pointBIndex);
                minCost += edge.distance;
                edgeCount++;
            }
            
            if (edgeCount == points.length - 1) {
                break;
            }
        }
        
        return minCost;
    }
}