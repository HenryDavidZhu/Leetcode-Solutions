class Solution {
    // Binary Search
    // Time complexity: O(log(m * n))
    // Space complexity: O(1)
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        int start = 0;
        int end = m * n - 1;
        
        while (start <= end) {
            int midIndex = (start + end + 1) / 2;
            int midpoint = matrixValue(matrix, midIndex, n);
            
            if (midpoint == target) {
                return true;
            }
            else if (midpoint < target) {
                start = midIndex + 1;
            }
            else {
                end = midIndex - 1;
            }
        }
        
        return false;
    }
    
    public int matrixValue(int[][] matrix, int idx, int n) {
        int r = idx / n;
        int c = idx % n;
        return matrix[r][c];
    }
}