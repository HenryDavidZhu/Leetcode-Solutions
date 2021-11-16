// O(n log(n)) time, O(n) space.
class Solution {
    public String frequencySort(String s) {
        // Generate a mapping of each character to its frequency. 
        HashMap<Character, Integer> frequencyMapping = new HashMap<Character, Integer>();
        for (Character c : s.toCharArray()) {
            Integer cFreq = frequencyMapping.getOrDefault(c, 0);
            cFreq++;
            frequencyMapping.put(c, cFreq);
        }
        
        // Based on that mapping, generate a max-heap storing the
        // characters in decreasing order from their frequency.
        PriorityQueue<Character> maxHeap = new PriorityQueue<Character>((x, y) -> frequencyMapping.get(y) - frequencyMapping.get(x));
        for (Character character : frequencyMapping.keySet()) {
            maxHeap.add(character);
        }
        
        // Generate the sorted string from the max-heap.
        StringBuilder sb = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            Character mostFreqCharacter = maxHeap.poll();
            for (int i = 0; i < frequencyMapping.get(mostFreqCharacter); i++) {
                sb.append(mostFreqCharacter);
            }
        }
        
        return sb.toString();
    }
}