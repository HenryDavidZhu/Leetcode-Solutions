class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Represent this problem as a graph problem: nodes being the
        // courses, and a directed edge from u -> v if u is a prerequisite
        // to v. If this graph has a cycle, it means that we cannot finish
        // the schedule:
        //
        // adjacencyList:
        // Generate an adjacency list from the input prerequesites matrix, 
        // maping each prerequisite to a corresponding list of courses that 
        // have the prequisite as a requirement.
        //
        // courseVisits:
        // Also, initialize our mapping of courses to their visited status:
        // 0 = not visited
        // -1 = being visited (in progress)
        // 1 = visiting
        Map<Integer, List<Integer>> adjacencyList = new HashMap<Integer, List<Integer>>();
        Map<Integer, Integer> courseVisits = new HashMap<Integer, Integer>();
        
        for (int[] courseAndPrereq : prerequisites) {
            int course = courseAndPrereq[0];
            int prereq = courseAndPrereq[1];
            
            // Update our adjacency list
            if (!adjacencyList.containsKey(prereq)) {
                List<Integer> newCourseList = new LinkedList<Integer>();
                newCourseList.add(course);
                
                adjacencyList.put(prereq, newCourseList);
            }
            else {
                List<Integer> courseList = adjacencyList.get(prereq);
                courseList.add(course);
                
                adjacencyList.put(prereq, courseList);
            }
            
            // Update our mapping of courses to its traversal #
            courseVisits.put(course, 0);
            courseVisits.put(prereq, 0);
        }
        
        // Conduct DFS traversals until we have visited each course in the graph,
        // and if we have found a cycle, it means that the student cannot finish
        // all of the courses.
        Set<Integer> courses = adjacencyList.keySet();
        
        for (Integer rootCourse : courses) {
            // Only traverse from the root course if we have not already visited it
            if (courseVisits.get(rootCourse) == 0) {
                if (detectCycleThroughDFS(adjacencyList, rootCourse, courseVisits)) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    // Detect whether or not there is a cycle in a graph by running a DFS traversal
    private boolean detectCycleThroughDFS(Map<Integer, List<Integer>> adjacencyList, 
                                          int root,
                                          Map<Integer, Integer> courseVisits) {
        // If the current course has already been visited by the current traversal,
        // we have detected a cycle
        if (courseVisits.get(root) == -1) {
            return true;
        }
        
        // If we already explored the current course in a previous traversal, that means no
        // cycle was detected for that course
        if (courseVisits.get(root) == 1) {
            return false;
        }
        
        // We mark the current course as being visited (in progress)
        courseVisits.put(root, -1);
        
        // Visit of all of the current course's parent courses (if they exist)
        if (adjacencyList.containsKey(root)) {
            for (Integer parentCourse : adjacencyList.get(root)) {
                // See if we can detect a cycle from the current course
                if (detectCycleThroughDFS(adjacencyList, parentCourse, courseVisits)) {
                    return true;
                }
            }
        }
        
        // Once we have finished exploring the current node, we mark it as visited, and
        // we did not detect a cycle.
        courseVisits.put(root, 1);
        return false;
    }
}