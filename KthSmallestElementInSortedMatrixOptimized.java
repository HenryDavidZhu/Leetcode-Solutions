class Cell {
    int r;
    int c;
    int value;
    
    public Cell(int r, int c, int value) {
        this.r = r;
        this.c = c;
        this.value = value;
    }
}

class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        Queue<Cell> minHeap = new PriorityQueue<Cell>((cell1, cell2) -> cell1.value - cell2.value);
        
        // Add first column to min heap
        for (int i = 0; i < matrix.length; i++) {
            minHeap.add(new Cell(i, 0, matrix[i][0]));
        }
        
        int kthSmallest = matrix[0][0];
        int smallestCount = 0;
        
        while (smallestCount < k) {
            Cell kthSmallestCell = minHeap.poll();
            int smallestR = kthSmallestCell.r;
            int smallestC = kthSmallestCell.c;
            kthSmallest = kthSmallestCell.value;

            if (smallestC < matrix[0].length - 1) {
                minHeap.add(new Cell(smallestR, smallestC + 1, matrix[smallestR][smallestC + 1]));
            }
            
            smallestCount++;
        }
        
        return kthSmallest;
    }
}