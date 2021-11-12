class Solution {
    // O(V + E) time, O(V + E) space.
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] schedule = new int[numCourses];
        
        // Generate a mapping of courses to their indegree.
        int[] indegree = new int[numCourses];
        
        // Generate a dependency mapping and update the indegree mapping.
        // dependency mapping: course -> list of dependencies to that course
        //
        // Generate an adjacency list that maps each course to the list
        // of courses it is a dependency for.
        // adjacency list: course -> list of courses it is a dependency for
        HashMap<Integer, LinkedList<Integer>> dependencies = new HashMap<Integer, LinkedList<Integer>>();
        HashMap<Integer, LinkedList<Integer>> adjacencyList = new HashMap<Integer, LinkedList<Integer>>();
        
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int dependency = prerequisite[1];
            
            LinkedList<Integer> courseDependencies = dependencies.getOrDefault(course, new LinkedList<Integer>());
            courseDependencies.add(dependency);
            
            dependencies.put(course, courseDependencies);
            indegree[course] = courseDependencies.size();
            
            LinkedList<Integer> coursesThatRequireDependency = adjacencyList.getOrDefault(dependency, new LinkedList<Integer>());
            coursesThatRequireDependency.add(course);
            adjacencyList.put(dependency, coursesThatRequireDependency);
        }
        
        // Keep track of the courses that we have added to your schedule.
        HashSet<Integer> coursesAdded = new HashSet<Integer>();
        
        // Continuously add course with 0 indegree (prerequisites all
        // satisfied) and update the indegree mapping.
        int index = 0;
        
        while (true) {
            if (index == numCourses) {
                return schedule;
            }
            
            // Find courses with 0 indegree.
            LinkedList<Integer> zeroIndegree = new LinkedList<Integer>();
            for (int i = 0; i < numCourses; i++) {
                if (indegree[i] == 0) {
                    if (!coursesAdded.contains(i)) {
                        zeroIndegree.add(i);
                    }
                }
            }
            
            if (zeroIndegree.size() == 0) {
                return new int[] {};
            }
            
            for (Integer nextCourse : zeroIndegree) {
                // Add courses with 0 indegree to our schedule.
                schedule[index] = nextCourse;
                coursesAdded.add(nextCourse);
                index++;
                
                // Find all the courses that require nextCourse.
                LinkedList<Integer> coursesRequireNextCourse = adjacencyList.getOrDefault(nextCourse, new LinkedList<Integer>());
                for (Integer course : coursesRequireNextCourse) {
                    indegree[course]--;
                }
            }
        }
    }
}