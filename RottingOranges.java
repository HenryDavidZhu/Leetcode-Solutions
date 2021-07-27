class RottenCell {
    int r;
    int c;
    int depth;
    
    public RottenCell(int r, int c, int depth) {
        this.r = r;
        this.c = c;
        this.depth = depth;
    }
}

class Solution {
    public int orangesRotting(int[][] grid) {
        Queue<RottenCell> queue = new LinkedList<RottenCell>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int[][] directions = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        int orangeCount = 0;
        int initialRottenOranges = 0;
        int rottenOranges = 0;
        int time = 0;
        
        // Find all the root rotten cells to execute BFS from
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] > 0) {
                    orangeCount++;
                }
                
                if (grid[r][c] == 2) {
                    initialRottenOranges++;
                    queue.add(new RottenCell(r, c, 0));
                }
            }
        }
        
        if (orangeCount == initialRottenOranges) {
            return 0;
        }
        
        // Execute a single BFS traversal simultaneously on all root
        // rotten cells.
        while (!queue.isEmpty() && rottenOranges < orangeCount) {
            RottenCell curr = queue.poll();
            grid[curr.r][curr.c] = 2;
            
            if (!visited[curr.r][curr.c]) {
                rottenOranges++;
                visited[curr.r][curr.c] = true;
            }
            
            time = Math.max(time, curr.depth);
            
            for (int[] direction : directions) {
                int childR = curr.r + direction[0];
                int childC = curr.c + direction[1];

                if (childR >= 0 && childR < grid.length &&
                    childC >= 0 && childC < grid[0].length) {
                    if (!visited[childR][childC]) {
                        if (grid[childR][childC] > 0) {
                            RottenCell currChild = new RottenCell(childR, childC, curr.depth + 1);
                            queue.add(currChild);
                        }
                    }
                }
            }
        }

        return rottenOranges == orangeCount ? time : -1;
    }
}