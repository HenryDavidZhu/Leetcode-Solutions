class Solution {
    // O(n) time and space.
    public int trap(int[] height) {
        // Base Case: Only have first and last indexes.
        if (height.length <= 2) {
            return 0;
        }
        
        int n = height.length;
        int[] leftBounds = new int[n];
        int[] rightBounds = new int[n];
        int total = 0;
        
        // Iterate from left to right to fill up leftBounds.
        for (int i = 1; i < n - 1; i++) {
            leftBounds[i] = Math.max(leftBounds[i - 1], height[i - 1]);
        }
        
        // Iterate right to left to fill up rightBounds.
        for (int j = n - 2; j >= 1; j--) {
            rightBounds[j] = Math.max(rightBounds[j + 1], height[j + 1]);
        }
        
        // Add up the maximum amount of water we can trap at each index.
        for (int k = 1; k < n - 1; k++) {
            int waterLevel = Math.min(leftBounds[k], rightBounds[k]) - height[k];
            
            if (waterLevel > 0) {
                total += waterLevel;
            }
        }
        
        return total;
    }
}