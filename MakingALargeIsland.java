class Solution {
    // O(n^2) time, O(n^2) space
    public int largestIsland(int[][] grid) {
        // [1, 1]
        // [0, 1]
        // Corresponding matrix which stores the size of the component that
        // each cell belongs to (0 if it doesn't belong to a component).
        int[][] componentSizes = new int[grid.length][grid[0].length];
        
        // Have a matrix which maps each cell to what component it belongs to.
        int[][] cellComponents = new int[grid.length][grid[0].length];
        
        // Indicates what component a cell is in.
        int currComponent = 0;
        
        // Visited matrix which keeps track of the cells that we visited
        // when we are finding the components of the grid.
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        
        // Stores the direcitons that we can go from a cell.
        int[][] directions = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        // In the case that we can't find a 0.
        int maxComponentSize = 0;
        
        // Iterate through every element in the grid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // If the cell is 1, it belongs the island.
                // Only find the component that the cell belongs in if
                // we haven't visited it yet.
                if (!visited[i][j] && grid[i][j] == 1) {
                    // Execute a BFS traversal to find the component.
                    int componentSize = 0;
                    
                    // Update the curent component ID (#)
                    currComponent++;
                    
                    LinkedList<int[]> componentCells = new LinkedList<int[]>();
                    Queue<int[]> queue = new LinkedList<int[]>();
                    queue.add(new int[] {i, j});
                    
                    while (!queue.isEmpty()) {
                        int[] currCell = queue.poll();
                        int r = currCell[0];
                        int c = currCell[1];
                        
                        if (!visited[r][c]) {
                            // Mark it as visited.
                            visited[r][c] = true;

                            // Update our component size.
                            componentSize++;

                            // Update our list of cells for the current component.
                            componentCells.add(new int[] {r, c});

                            // Mark the current cell's component.
                            cellComponents[r][c] = currComponent;

                            // Figure out the neighboring cells that also belong to the component.
                            for (int[] direction : directions) {
                                int[] newCell = new int[] {r + direction[0], c + direction[1]};

                                // Check that the new cell is valid.
                                if (newCell[0] >= 0 && newCell[0] < grid.length &&
                                    newCell[1] >= 0 && newCell[1] < grid[0].length) {
                                    // Check that we haven't already visited the new cell.
                                    if (!visited[newCell[0]][newCell[1]] && grid[newCell[0]][newCell[1]] == 1) {
                                        queue.add(newCell);
                                    }
                                }
                            }
                        }
                    }
                    
                    // Update the max component size if necessary.
                    maxComponentSize = Math.max(maxComponentSize, componentSize);
                    
                    // Update the matrix storing the sizes of the component each cell is in.
                    for (int[] cell : componentCells) {
                        componentSizes[cell[0]][cell[1]] = componentSize;
                    }
                }
            }
        }
        
        // Iterate through all the 0s and figure out the maximum island we can generate from the flip.
        int result = 0;
        
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                // We are only allowed to flip 0s.
                if (grid[r][c] == 0) {
                    // Figure out what are the neighboring components from the current cell.
                    int maxIsland = 1;
                    
                    // Stores the unique components that we have already connecting by flipping the current cell from a 0 to a 1.
                    HashSet<Integer> uniqueComponents = new HashSet<Integer>();
                    
                    // Check top component.
                    if (r > 0) {
                        // Check that the above cell is actually in a component.
                        if (grid[r - 1][c] == 1) {
                            // Get the component that the above cell is in.
                            int cellComponent = cellComponents[r - 1][c];
                            
                            if (!uniqueComponents.contains(cellComponent)) {
                                maxIsland += componentSizes[r - 1][c];
                            }
                            
                            uniqueComponents.add(cellComponent);
                        }
                    }
                    // Check bottom component.
                    if (r < grid.length - 1) {
                        // Check that the cell is actually in a component.
                        int cellComponent = cellComponents[r + 1][c];
                            
                        if (!uniqueComponents.contains(cellComponent)) {
                            maxIsland += componentSizes[r + 1][c];
                        }
                        
                        uniqueComponents.add(cellComponent);
                    }
                    // Check left component.
                    if (c > 0) {
                        // Check that the cell is actually in a component.
                        int cellComponent = cellComponents[r][c - 1];
                            
                        if (!uniqueComponents.contains(cellComponent)) {
                            maxIsland += componentSizes[r][c - 1];
                        }
                        
                        uniqueComponents.add(cellComponent);
                    }
                    // Check right component.
                    if (c < grid[0].length - 1) {
                        // Check that the cell is actually in a component.
                        int cellComponent = cellComponents[r][c + 1];
                            
                        if (!uniqueComponents.contains(cellComponent)) {
                            maxIsland += componentSizes[r][c + 1];
                        }
                        
                        uniqueComponents.add(cellComponent);
                    }
                    
                    result = Math.max(result, maxIsland);
                }
            }
        }
        
        return result == 0 ? maxComponentSize : result;
    }
}