// O(n) time, O(n) space where n is number of cells in 
class Solution {
    // Grid stores the min distance to that location.
    public int shortestPathBinaryMatrix(int[][] grid) {
        int[] curr = new int[] {0, 0};
        Queue<int[]> queue = new LinkedList<int[]>();
        if (grid[0][0] == 0) {
            queue.add(curr);
        }
        
        int[][] neighborVectors = new int[][] {
            {0, -1}, // LEFT
            {0, 1}, // RIGHT
            {-1, 0}, // UP
            {1, 0}, // DOWN
            {-1, -1}, // UP-LEFT
            {-1, 1}, // UP-RIGHT
            {1, -1}, // DOWN-LEFT
            {1, 1} // DOWN-RIGHT
        };
        
        while (!queue.isEmpty()) {
            curr = queue.poll();
            int distToCurr = grid[curr[0]][curr[1]];
            
            if (curr[0] == grid.length - 1 && curr[1] == grid[0].length - 1) {
                return distToCurr + 1;
            }
            
            for (int[] neighborVector : neighborVectors) {
                int[] neighbor = new int[] {curr[0] + neighborVector[0], 
                                            curr[1] + neighborVector[1]};
                
                if (neighbor[0] >= 0 && neighbor[0] <= grid.length - 1 &&
                    neighbor[1] >= 0 && neighbor[1] <= grid[0].length - 1) {
                    if (grid[neighbor[0]][neighbor[1]] == 0) {
                        grid[neighbor[0]][neighbor[1]] = distToCurr + 1;
                        queue.add(neighbor);
                    }
                }
            }
        }
        
        return -1;
    }
}