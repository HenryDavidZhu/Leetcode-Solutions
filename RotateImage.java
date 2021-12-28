// O(n^2) time, n is length of matrix and O(1) space
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        
        // Outer-loop: Iterate through the rings
        for (int i = 0; i < n/2; i++) {
            // Inner-loop: Rotate the 4 cells.
            for (int j = i; j < n - i - 1; j++) {
                // Cell A
                // Cell B
                // Cell C
                // Cell D
                int rowA = i;
                int colA = j;
                int cellA = matrix[rowA][colA];
                
                int rowB = j;
                int colB = n - i - 1;
                int cellB = matrix[rowB][colB];
                
                int rowC = n - i - 1;
                int colC = n - j - 1;
                int cellC = matrix[rowC][colC];
                
                int rowD = n - j - 1;
                int colD = i;
                int cellD = matrix[rowD][colD];
                
                // Cell A -> Cell B -> Cell C -> Cell D -> Cell A
                matrix[rowB][colB] = cellA;
                matrix[rowC][colC] = cellB;
                matrix[rowD][colD] = cellC;
                matrix[rowA][colA] = cellD;
            }
            //
            // a b c d
            // e f g h
            // i j k l
            // m n o p
        }
    }
}