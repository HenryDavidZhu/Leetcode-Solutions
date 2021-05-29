class Solution {
    // Memoization: Map a word to the longest chain ending in that word
    Map<String, Integer> wordMaxChainLen = new HashMap<String, Integer>();
    
    String[] wordList = new String[] {};
            
    public int longestStrChain(String[] words) {
        wordList = words;
        int globalMaxChainLen = 1;
        
        // Sort the array of words in ascending order based on word length
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String word1, String word2) {
                return word1.length() - word2.length();
            }
        });
        
        // Iterate through all the possible base words
        for (String baseWord : words) {
            // If the word is a single character, then its only and longest possible
            // chain is just itself
            if (baseWord.length() == 1) {
                wordMaxChainLen.put(baseWord, 1);
            }
            // Otherwise, we run the DFS traversal through the word to determine its
            // longest chain
            else {
                int currMaxChainLen = findMaxChainLen(baseWord);
                wordMaxChainLen.put(baseWord, currMaxChainLen);
                
                // Update our global max chain length
                if (currMaxChainLen > globalMaxChainLen) {
                    globalMaxChainLen = currMaxChainLen;
                }
            }
        }
        
        return globalMaxChainLen;
    }
    
    // Execute a DFS traversal through the word to determine its longest chain
    private int findMaxChainLen(String baseWord) {
        // If we have already found the longest chain for the base word, return
        // its value from our memoization mapping
        if (wordMaxChainLen.containsKey(baseWord)) {
            return wordMaxChainLen.get(baseWord);
        }
        
        int maxChainLength = 1;
        
        // Convert the base word into a StringBuilder for easier processing
        StringBuilder sbBaseWord = new StringBuilder(baseWord);

        // If we have already computed the max chain length for the current word
        if (wordMaxChainLen.containsKey(baseWord)) {
            return wordMaxChainLen.get(baseWord);
        }
            
        // Compute the predecessors of the current word
        for (int i = 0; i < sbBaseWord.length(); i++) {
            sbBaseWord.deleteCharAt(i);
                
            // See if the predecessor is in the word list
            String predecessor = sbBaseWord.toString();
                
            if (Arrays.asList(wordList).contains(predecessor)) {
                int currMaxChainLength = wordMaxChainLen.get(predecessor) + 1;
                
                // Update the base word's max length if necessary
                if (currMaxChainLength > maxChainLength) {
                    maxChainLength = currMaxChainLength;
                }
            }
            
            // Backtracking: After analyzing the predecessor, move back to analyze
            // the base word
            sbBaseWord.insert(i, baseWord.charAt(i));
        }
        
        // After computing the max chain length for the current word, update our
        // memoization mapping
        wordMaxChainLen.put(baseWord, maxChainLength);
        
        return maxChainLength;
    }
}