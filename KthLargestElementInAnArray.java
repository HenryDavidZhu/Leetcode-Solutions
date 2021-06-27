class Solution {
    public class MaxComparator implements Comparator<Integer> {
        public int compare(Integer x, Integer y) {
            return x - y;
        }
    }
    
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(new MaxComparator());
        for (int num : nums) {
            maxHeap.add(num);
            
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        
        return maxHeap.poll();
    }
}