class Solution {
    // Time Complexity: O(k^2 log k)
    // Space Complexity: O(k)
    public int kthSmallest(int[][] matrix, int k) {
        Queue<Integer> maxHeap = new PriorityQueue<Integer>((x, y) -> y - x);
        
        for (int i = 0; i < Math.min(matrix.length, k); i++) {
            for (int j = 0; j < Math.min(matrix[0].length, k); j++) {
                maxHeap.add(matrix[i][j]);
                
                if (maxHeap.size() > k) {
                    maxHeap.poll();
                }
            }
        }
        
        return maxHeap.poll();
    }
}