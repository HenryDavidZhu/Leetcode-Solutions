class Solution {
    // Determines the end destination of a rolling ball given the maze and the direction.
    private int[] endDestination(int[][] maze, int[] currCell, int[] direction) {
        int[] currPos = new int[] {currCell[0], currCell[1]};
        
        while (maze[currPos[0]][currPos[1]] == 0) {
            // If the ball will go out of range, stop rolling it.
            if (currPos[0] + direction[0] < 0 || currPos[0] + direction[0] >= maze.length || 
                currPos[1] + direction[1] < 0 || currPos[1] + direction[1] >= maze[0].length) {
                break;
            }

            // If the ball is hitting a wall, stop rolling it.
            if (maze[currPos[0] + direction[0]][currPos[1] + direction[1]] == 1) {
                break;
            }
            
            currPos[0] = currPos[0] + direction[0];
            currPos[1] = currPos[1] + direction[1];
        }
        
        return currPos;
    }
    
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.add(start);
        
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        int[][] vectors = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while (!queue.isEmpty()) {
            int[] currCell = queue.poll();
            visited[currCell[0]][currCell[1]] = true;
            
            if (currCell[0] == destination[0] && currCell[1] == destination[1]) {
                return true;
            }
            
            // Add the current cell's adjacent neighbors (not blocked or
            // visited) to the queue.
            for (int[] vector : vectors) {
                int[] newDestination = endDestination(maze, currCell, vector);
                
                if (maze[newDestination[0]][newDestination[1]] == 0) {
                    if (!visited[newDestination[0]][newDestination[1]]) {
                        queue.add(newDestination);
                    }
                }
            }
        }
        
        return false;
    }
}