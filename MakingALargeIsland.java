class Solution {
    public int largestIsland(int[][] grid) {
        // General Pattern: Finding the connected components of a graph (an island is a connected component)
        //
        // High-level solution has 2 steps:
        // 1. Find all the connected components of the graph.
        // 2. Go through all the 0s in the graph and for each 0, see what connected components are adjacent to that 0.
        // If we flip the 0 to a 1, then all those conncted components will be connected plus the flipped 1. Update the
        // result accordingly.
        //
        // Few details to take note of:
        // 1. Use a visited matrix while finding the connected components to avoid iterating infinitely in a cycle.
        // 2. For each 0, while seeing which connected compnents are adjacent, using a HashSet to keep track of the ids of the
        // connected components already found to avoid double-counting a connected component.
        //
        // Time Complexity: O(n^2), we need to iterate through every cell in the provided grid (n x n).
        // Space Complexity: O(n^2) as well, because we need to use a visited matrix, matrix to keep track of the size of the
        // component that each cell belongs to, plus matrix map what component each cell belongs to, and so forth.

        // Matrix which stores the size of the component that each cell belongs to (0 if it doesn't belong to a component).
        int[][] componentSizes = new int[grid.length][grid[0].length];

        // Have a matrix which maps each cell to what cmoponent it belongs to.
        int[][] cellComponents = new int[grid.length][grid[0].length];

        // Indicate what cmponent a cell is in.
        int currComponentId = 0;

        // Visited matrix, which keeps track of the cells that we visited when we are finding the grid's components.
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        // Stores the directions that we can go to from a cell.
        int[][] directions = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int maxComponentSize = 0;

        // Iterate through every element in the grid.
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // If we haven't visited the cell and the cell is 1, it belongs to an island.
                if (!visited[i][j] && grid[i][j] == 1) {
                    // Execute a BFS traversal to find the cmoponent.
                    int componentSize = 0;

                    currComponentId++;

                    LinkedList<int[]> componentCells = new LinkedList<int[]>();
                    Queue<int[]> queue = new LinkedList<int[]>();
                    queue.add(new int[] {i, j});

                    while (!queue.isEmpty()) {
                        int[] currCell = queue.poll();
                        int r = currCell[0];
                        int c = currCell[1];

                        if (!visited[r][c]) {
                            visited[r][c] = true;
                            componentSize++;
                            componentCells.add(new int[] {r, c});
                            cellComponents[r][c] = currComponentId;

                            // Figure out the neighboring cells that also belong to the component.
                            for (int[] direction : directions) {
                                int[] newCell = new int[] {r + direction[0], c + direction[1]};

                                // Check that the row is valid.
                                if (newCell[0] >= 0 && newCell[0] < grid.length) {
                                    // Check that the column is valid.
                                    if (newCell[1] >= 0 && newCell[1] < grid[0].length) {
                                        if (!visited[newCell[0]][newCell[1]] && grid[newCell[0]][newCell[1]] == 1) {
                                            queue.add(newCell);
                                        }
                                    }
                                }
                            }
                        }
                    }

                    maxComponentSize = Math.max(maxComponentSize, componentSize);

                    // Update the matrix storing the sizes of the component each cell is in.
                    for (int[] cell : componentCells) {
                        componentSizes[cell[0]][cell[1]] = componentSize;
                    }
                }
            }
        }

        // Iterate through all the 0s and figure out the maximum island we can generate from the flip.
        int maxComponentSizeFromFlip = 0;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 0) {
                    // Figure out what are the neighboring components from the current cell.
                    int maxIslandSize = 1;

                    // Store the unique components that we have already connected by flipping the current cell from 0 to 1.
                    Set<Integer> uniqueComponents = new HashSet<Integer>();

                    // Note that by default, all cells in 2D Matrix (Java) are 0.
                    // Analyze possible above component.
                    if (r > 0) {
                        // Get the component that the cell is in.
                        int cellComponent = cellComponents[r - 1][c];

                        if (!uniqueComponents.contains(cellComponent)) {
                            maxIslandSize += componentSizes[r - 1][c];
                        }

                        uniqueComponents.add(cellComponent);
                    }
                    // Analyze possible bottom component.
                    if (r < grid.length - 1) {
                        int cellComponent = cellComponents[r + 1][c];

                        if (!uniqueComponents.contains(cellComponent)) {
                            maxIslandSize += componentSizes[r + 1][c];
                        }

                        uniqueComponents.add(cellComponent);
                    }
                    // Analyze possible left component.
                    if (c > 0) {
                        int cellComponent = cellComponents[r][c - 1];

                        if (!uniqueComponents.contains(cellComponent)) {
                            maxIslandSize += componentSizes[r][c - 1];
                        }

                        uniqueComponents.add(cellComponent);
                    }
                    // Analyze possible right component.
                    if (c < grid[0].length - 1) {
                        int cellComponent = cellComponents[r][c + 1];

                        if (!uniqueComponents.contains(cellComponent)) {
                            maxIslandSize += componentSizes[r][c + 1];
                        }

                        uniqueComponents.add(cellComponent);
                    }

                    maxComponentSizeFromFlip = Math.max(maxComponentSizeFromFlip, maxIslandSize);
                }
            }
        }

        return maxComponentSizeFromFlip == 0 ? maxComponentSize : maxComponentSizeFromFlip;
    }
}
