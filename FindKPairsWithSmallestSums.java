class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // max-heap of size k
        Queue<int[]> maxHeap = new PriorityQueue<int[]>((x, y) -> (y[0] + y[1]) - (x[0] + x[1]));
        
        // Possible values for first num
        for (int i = 0; i < Math.min(nums1.length, k); i++) {
            // Possible values for second num
            for (int j = 0; j < Math.min(nums2.length, k); j++) {
                maxHeap.add(new int[] {nums1[i], nums2[j]});
                
                if (maxHeap.size() > k) {
                    maxHeap.poll();
                }
            }
        }
        
        // Convert max heap to list of pairs
        List<List<Integer>> kSmallest = new LinkedList<List<Integer>>();
        while (!maxHeap.isEmpty()) {
            List<Integer> pair = new LinkedList<Integer>();
            int[] pairArray = maxHeap.poll();
            pair.add(pairArray[0]);
            pair.add(pairArray[1]);
            kSmallest.add(pair);
        }
        
        return kSmallest;
    }
}