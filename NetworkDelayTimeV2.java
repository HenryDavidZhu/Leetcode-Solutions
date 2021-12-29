// Time Complexity: O(N^{N}), Space Complexity: O(N + E) since we use adjacency list.
class Solution {
    // Maps each node to the shortest time it takes to get there from k.
    Map<Integer, Integer> shortestTimeToK = new HashMap<Integer, Integer>();
    
    public int networkDelayTime(int[][] times, int n, int k) {
        // Step 1: Represent the provided graph as an adjacency list.
        Map<Integer, LinkedList<Pair<Integer, Integer>>> adjList = convertToAdjList(times);
        
        // Step 2: Perform DFS on the provided graph, finding the shortest
        // time it takes to get to each node.
        dfsOnAdjList(adjList, k, 0);
        
        // Step 3: Go through each node, and figure out the max time it takes
        // to get to any of the nodes (this is our answer).
        // Deal with the edge case that we didn't visit all the nodes.
        if (shortestTimeToK.values().size() < n) {
            return -1;
        }
        
        int maxTime = Integer.MIN_VALUE;
        for (Integer shortestTime : shortestTimeToK.values()) {
            maxTime = Math.max(maxTime, shortestTime);
        }
        
        return maxTime;
    }
    
    // Performs DFS on a provided graph (adjacency list).
    private void dfsOnAdjList(Map<Integer, LinkedList<Pair<Integer, Integer>>> adjList, int startNode, int timeToStart) {
        // If the time it takes for us to get to the startNode is already greater
        // than the existing fastest time it takes for us to get to startNode, we
        // don't need to exlore any further along this traversal.
        if (shortestTimeToK.containsKey(startNode) && timeToStart >= shortestTimeToK.get(startNode)) {
            return;
        }
        
        // Update the shortest time it takes for us to reach startNode.
        shortestTimeToK.put(startNode, timeToStart);
        
        // Now we go through the startNode's adjacent neighbors.
        for (Pair<Integer, Integer> edge : adjList.getOrDefault(startNode, new LinkedList<Pair<Integer, Integer>>())) {
            dfsOnAdjList(adjList, edge.getKey(), shortestTimeToK.get(startNode) + edge.getValue());
        }
    }
    
    // Convert the provided graph into an adjacency list.
    private Map<Integer, LinkedList<Pair<Integer, Integer>>> convertToAdjList(int[][] times) {
        // Initialize an empty adjacency list.
        Map<Integer, LinkedList<Pair<Integer, Integer>>> adjList = new HashMap<Integer, LinkedList<Pair<Integer, Integer>>>();
        
        // Iterate through each edge.
        for (int[] edge : times) {
            // Retrieve the start node u.
            Integer u = edge[0];
            
            // Retrieve the end node v.
            Integer v = edge[1];
            
            // Retrieve the weight of the edge (w).
            Integer w = edge[2];
            
            // Retrieve a list of all the edges from u in our existing adjacency list and update it.
            LinkedList<Pair<Integer, Integer>> existingEdgesFromU = adjList.getOrDefault(u, new LinkedList<Pair<Integer, Integer>>());
            existingEdgesFromU.add(new Pair<Integer, Integer>(v, w));
            
            // Update the list of all the edges from u in our existing adjacency list.
            adjList.put(u, existingEdgesFromU);
        }
        
        return adjList;
    }
}