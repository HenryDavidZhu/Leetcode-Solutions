class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        Queue<Integer> maxHeap = new PriorityQueue<Integer>((a, b) ->
                                     Math.abs(b - x) == Math.abs(a - x) ? b - a : Math.abs(b - x) -                                            Math.abs(a - x));
        
        for (int num : arr) {
            maxHeap.add(num);
            
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        
        List<Integer> kClosestElements = new LinkedList<Integer>();
        while (!maxHeap.isEmpty()) {
            kClosestElements.add(maxHeap.poll());
        }
        
        Collections.sort(kClosestElements);
        
        return kClosestElements;
    }
}