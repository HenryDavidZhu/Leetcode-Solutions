class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        // K most frequent strings:
        // Min-Heap with capacity k, and any time that capacity is exceeded,
        // we pop from the min-heap. Popping the (n - k) least frequencies
        // from the heap leaves us the k highest frequencies.
        
        // Time: O(n log k)
        // Space: O(n)
        
        // Generate the word to frequency map.
        // Time: O(n)
        // Space: O(n)
        HashMap<String, Integer> wordToFrequency = new HashMap<String, Integer>();
        for (String word : words) {
            Integer wordFrequency = wordToFrequency.getOrDefault(word, 0);
            wordFrequency++;
            wordToFrequency.put(word, wordFrequency);
        }
        
        // Generate the frequencies to list of words with that frequency.
        // Time: O(n)
        // Space: O(n)
        HashMap<Integer, LinkedList<String>> frequencyToWords = new HashMap<Integer, LinkedList<String>>();
        for (Map.Entry<String, Integer> wordToFrequencyEntry : wordToFrequency.entrySet()) {
            String word = wordToFrequencyEntry.getKey();
            Integer frequency = wordToFrequencyEntry.getValue();
            
            LinkedList<String> freqWordList = frequencyToWords.getOrDefault(frequency, new LinkedList<String>());
            freqWordList.add(word);
            frequencyToWords.put(frequency, freqWordList);
        }
        
        // Add the frequencies to min-heap with max capacity k.
        // Time: O(n log k)
        // Space: O(k)
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        for (Integer frequency : frequencyToWords.keySet()) {
            minHeap.add(frequency);
            
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        // Compute the k highest frequencies, sorted from highest to lowest.
        // Time: O(k log k)
        // Space: O(k)
        LinkedList<Integer> kHighestFrequencies = new LinkedList<Integer>();
        while (!minHeap.isEmpty()) {
            kHighestFrequencies.add(minHeap.poll());
        }
        Collections.sort(kHighestFrequencies);
        Collections.reverse(kHighestFrequencies);
        
        // Generate our answer.
        // Time: O(n)
        // Space: O(n)
        LinkedList<String> answer = new LinkedList<String>();
        for (Integer highestFrequency : kHighestFrequencies) {
            LinkedList<String> highestFrequencyWords = frequencyToWords.getOrDefault(highestFrequency, new LinkedList<String>());
            Collections.sort(highestFrequencyWords);
            
            for (String highestFrequencyWord : highestFrequencyWords) {
                answer.add(highestFrequencyWord);
                
                if (answer.size() == k) {
                    return answer;
                }
            }
        }
        
        return answer;
    }
}