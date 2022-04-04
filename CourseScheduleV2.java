class Solution {
    enum Status {
        UNVISITED,
        PROGRESS,
        VISITED
    }
    
    boolean detectedCycle = false;
    
    // Time: O(|V| + |E|)
    // Space: O(|V| + |E|)
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Store the graph as an adjacency list.
        HashMap<Integer, LinkedList<Integer>> adjacencyList = new HashMap<Integer, LinkedList<Integer>>();
        
        // Iterate through all the prerequisites and update every prerequisite's list of
        // successors in our adjacency list.
        for (int[] prerequisite : prerequisites) {
            int successor = prerequisite[0];
            int predecessor = prerequisite[1];
            
            LinkedList<Integer> successors = adjacencyList.getOrDefault(predecessor, new LinkedList<Integer>());
            successors.add(successor);
            
            adjacencyList.put(predecessor, successors);
        }
        
        // By default, all nodes have not been vsiited yet.
        Status[] nodeStatuses = new Status[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            nodeStatuses[i] = Status.UNVISITED;
        }
        
        // For all the nodes in the graph, if we have not already visited that node, we
        // will execute a DFS traversal on that node.
        for (int i = 0; i < numCourses; i++) {
            if (nodeStatuses[i] == Status.UNVISITED) {
                dfsRecursive(adjacencyList, nodeStatuses, i);
                
                // If, during our traversal, we detect a cycle, we can't finish through all of the courses.
                if (detectedCycle) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private void dfsRecursive(HashMap<Integer, LinkedList<Integer>> adjacencyList, Status[] nodeStatuses, Integer currCourse) {
        // If our current course is already visited, we do not need to proceed further.
        if (nodeStatuses[currCourse] == Status.VISITED) {
            return;
        }
        
        // If our current course is being processed, we have a cycle.
        if (nodeStatuses[currCourse] == Status.PROGRESS) {
            detectedCycle = true;
            return;
        }
        
        // We are in progress of visiting our current course.
        nodeStatuses[currCourse] = Status.PROGRESS;
        
        // Visit all the successors to the current course.
        LinkedList<Integer> successors = adjacencyList.getOrDefault(currCourse, new LinkedList<Integer>());
        
        for (Integer successor : successors) {
            dfsRecursive(adjacencyList, nodeStatuses, successor);
        }
        
        // After visiting all of our current course's successors, mark our current course
        // as visited.
        nodeStatuses[currCourse] = Status.VISITED;
        
        return;
    }
}