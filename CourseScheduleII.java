enum Color {
    WHITE, GRAY, BLACK
}

// O(n) time and space complexity, with n being the number of courses.
class Solution {
    // Maps each course to its COLOR
    HashMap<Integer, Color> courseToColor = new HashMap<Integer, Color>();
    
    // Represents our DAG (Directed Acyclic Graph)
    HashMap<Integer, LinkedList<Integer>> adjacencyList = new HashMap<Integer, LinkedList<Integer>>();
    
    // Stack storing a topological ordering of the nodes in the DAG
    Stack<Integer> topologicalOrdering = new Stack<Integer>();
    
    // Whether we should continue exploring the current node.
    boolean cycleExists = false;
    
    // Initialize our data structures.
    public void initializeDataStructures(int numCourses, int[][] prerequisites) {
        // Initialize the mapping of each course to its COLOR
        for (int i = 0; i < numCourses; i++) {
            courseToColor.put(i, Color.WHITE);
        }
        
        // Initialize our DAG (Directed Acyclic Graph)
        for (int[] link : prerequisites) {
            int destination = link[0];
            int prerequisite = link[1];
            
            LinkedList<Integer> destinations = adjacencyList.getOrDefault(prerequisite, new LinkedList<Integer>());
            destinations.add(destination);
            
            adjacencyList.put(prerequisite, destinations);
        }
    }
    
    // DFS traversal using 3-level Coloring
    private void dfsTraversal(Integer root) {
        // Mark the current root as in-progress of being traversed (GRAY)
        courseToColor.put(root, Color.GRAY);
        
        for (Integer child : adjacencyList.getOrDefault(root, new LinkedList<Integer>())) {
            // We only visit the child if it does not result in a cycle (it is WHITE). 
            if (courseToColor.get(child) == Color.WHITE) {
                dfsTraversal(child);
            }
            
            // If it is gray, that means that we went back to a node we already visited in the traversal; we detected a cycle, and thus no topological sort exists.
            if (courseToColor.get(child) == Color.GRAY) {
                cycleExists = true;
                return;
            }
        }
        
        // Once we have hit a dead end (no children or all children are gray or black), that means we have already added all of the courses that have the current course as a prerequisite to the Stack, so we can mark the current course as BLACK and add the current course to the Stack (topological order).
        courseToColor.put(root, Color.BLACK);
        topologicalOrdering.add(root);
    }
    
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        initializeDataStructures(numCourses, prerequisites);
        
        // Go through all the courses.
        for (int i = 0; i < numCourses; i++) {
            // Only traverse through nodes that have not been processed.
            if (courseToColor.get(i) == Color.WHITE) {
                dfsTraversal(i);
            }
        }
        
        // If no cycle exists, build the topological sorted array from the Stack.
        int[] order = new int[] {};
        if (!cycleExists) {
            order = new int[topologicalOrdering.size()];
            int index = 0;
            
            while (!topologicalOrdering.isEmpty()) {
                order[index] = topologicalOrdering.pop();
                index++;
            }
        }
        
        return order;
    }
}