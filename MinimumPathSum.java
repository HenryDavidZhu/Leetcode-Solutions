// O(m * n) time and space
class Solution {
    public int minPathSum(int[][] grid) {
        // Memoization by mapping each cell to the minimum distance
        // from the root to that cell.
        int[][] memoization = new int[grid.length][grid[0].length];
        memoization[0][0] = grid[0][0];
        
        // Fill up the base cases.
        // First row.
        for (int c = 1; c < grid[0].length; c++) {
            memoization[0][c] = memoization[0][c - 1] + grid[0][c];
        }
        // First column.
        for (int r = 1; r < grid.length; r++) {
            memoization[r][0] = memoization[r - 1][0] + grid[r][0];
        }
        
        // Return our memoization's value at the bottom right cell
        // (destination).
        for (int r1 = 1; r1 < grid.length; r1++) {
            for (int c1 = 1; c1 < grid[0].length; c1++) {
                memoization[r1][c1] = Math.min(memoization[r1 - 1][c1], memoization[r1][c1 - 1]) + grid[r1][c1];
            }
        }
        
        return memoization[grid.length - 1][grid[0].length - 1];
    }
}