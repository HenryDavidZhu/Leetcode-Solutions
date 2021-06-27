class Solution {
    Map<Integer, Integer> frequencies = new HashMap<Integer, Integer>();

    public int[] topKFrequent(int[] nums, int k) {
        Queue<Integer> kFrequentElements = new PriorityQueue<Integer>(
                                               (n1, n2) -> frequencies.get(n1) - frequencies.get(n2));
        
        for (int num : nums) {
            if (!frequencies.containsKey(num)) {
                frequencies.put(num, 1);
            }
            else {
                frequencies.put(num, frequencies.get(num) + 1);
            }
        }
        
        for (int num : frequencies.keySet()) {
            kFrequentElements.add(num);
            
            if (kFrequentElements.size() > k) {
                kFrequentElements.poll();
            }
        }
        
        int[] kFrequent = new int[k];
        
        for (int i = 0; i < k; i++) {
            kFrequent[i] = kFrequentElements.poll();
        }
        
        return kFrequent;
    }
}