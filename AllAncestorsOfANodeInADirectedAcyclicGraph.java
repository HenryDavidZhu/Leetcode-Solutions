class Solution {
    private List<List<Integer>> answer = new LinkedList<>();

    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        // Construct the adjacencyList of the reversed graph.
        Map<Integer, List<Integer>> reversedGraphAdjacencyList = new HashMap<>();

        for (int i = 0; i < n; i++) {
            reversedGraphAdjacencyList.put(i, new LinkedList<Integer>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            reversedGraphAdjacencyList.get(v).add(u);
        }

        // Find ancestors of every node using DFS.
        for (int i = 0; i < n; i++) {
            Set<Integer> ancestorsOfNode = new TreeSet<Integer>();
            dfs(reversedGraphAdjacencyList, i, ancestorsOfNode, new boolean[n]);
            answer.add(new LinkedList<Integer>(ancestorsOfNode));
        }

        return answer;
    }

    private void dfs(Map<Integer, List<Integer>> adjacencyList, int node, Set<Integer> ancestorsOfNode, boolean[] visited) {
        if (visited[node]) {
            return;
        }

        for (int child : adjacencyList.get(node)) {
            ancestorsOfNode.add(child);
            dfs(adjacencyList, child, ancestorsOfNode, visited);
            visited[node] = true;
        }
    }
}
