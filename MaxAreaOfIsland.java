class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int maxIslandArea = 0;
        
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                // If the cell is visited, skip it.
                if (visited[r][c]) {
                    continue;
                }
                
                // If the cell is 0, mark it as visited and skip it.
                if (grid[r][c] == 0) {
                    visited[r][c] = true;
                    continue;
                }
                
                // If that cell is a 1, execute BFS traversal on that cell,
                // and each time a cell is visited in the traversal, mark it
                // as visited and increment the current island size
                if (grid[r][c] == 1) {
                    Queue<int[]> queue = new LinkedList<int[]>();
                    queue.add(new int[] {r, c});
                    int islandSize = 0;
                    
                    while (!queue.isEmpty()) {
                        int[] currCell = queue.poll();
                        int currR = currCell[0];
                        int currC = currCell[1];

                        visited[currR][currC] = true;
                        islandSize++;
                        
                        // Explore unvisited and adjacent 1 cells.
                        // Left
                        if (currC > 0) {
                            if (!visited[currR][currC - 1]) {
                                if (grid[currR][currC - 1] == 1) {
                                    queue.add(new int[] {currR, currC - 1});
                                    visited[currR][currC - 1] = true;
                                }
                            }
                        }
                        // Right
                        if (currC < grid[0].length - 1) {
                            if (!visited[currR][currC + 1]) {
                                if (grid[currR][currC + 1] == 1) {
                                    queue.add(new int[] {currR, currC + 1});
                                    visited[currR][currC + 1] = true;
                                }
                            }
                        }
                        // Top
                        if (currR > 0) {
                            if (!visited[currR - 1][currC]) {
                                if (grid[currR - 1][currC] == 1) {
                                    queue.add(new int[] {currR - 1, currC});
                                    visited[currR - 1][currC] = true;
                                }
                            }
                        }
                        // Down
                        if (currR < grid.length - 1) {
                            if (!visited[currR + 1][currC]) {
                                if (grid[currR + 1][currC] == 1) {
                                    queue.add(new int[] {currR + 1, currC});
                                    visited[currR + 1][currC] = true;
                                }
                            }
                        }
                    }
                    
                    maxIslandArea = Math.max(maxIslandArea, islandSize);
                }
            }
        }
        
        return maxIslandArea;
    }
}