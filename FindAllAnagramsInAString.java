class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> startIndices = new LinkedList<Integer>();
        
        // Generate a mapping of each character to its frequency for p
        Map<Character, Integer> pCharFrequency = new HashMap<Character, Integer>();
        for (char c : p.toCharArray()) {
            Integer freq = pCharFrequency.getOrDefault(c, 0);
            pCharFrequency.put(c, freq + 1);
        }
        
        Map<Character, Integer> currCharFrequency = new HashMap<Character, Integer>();
        
        // Iterate through all possible anagrams
        for (int i = 0; i <= s.length() - p.length(); i++) {
            int j = i + p.length();
            
            // Get the current substring
            char[] currChars = s.substring(i, j).toCharArray();
            
            // See if the current character is an anagram
            int satisfiedChars = 0;
            boolean isAnagram = true;
            
            if (i == 0) {
                for (char c : currChars) {
                    Integer freq = currCharFrequency.getOrDefault(c, 0);
                    currCharFrequency.put(c, freq + 1);
                }
            }
            else {
                char leftRemovedChar = s.charAt(i - 1);
                Integer leftRemovedCharFreq = currCharFrequency.getOrDefault(leftRemovedChar, 0);
                leftRemovedCharFreq--;
                
                if (leftRemovedCharFreq.equals(0)) {
                    currCharFrequency.remove(leftRemovedChar);
                }
                else {
                    currCharFrequency.put(leftRemovedChar, leftRemovedCharFreq);
                }
                
                char rightAddedChar = s.charAt(j - 1);
                Integer rightAddedCharFreq = currCharFrequency.getOrDefault(rightAddedChar, 0);
                rightAddedCharFreq++;
                currCharFrequency.put(rightAddedChar, rightAddedCharFreq);
            }
            
            for (Map.Entry<Character, Integer> freqEntry : currCharFrequency.entrySet()) {
                if (!pCharFrequency.containsKey(freqEntry.getKey())) {
                    isAnagram = false;
                    break;
                }
                int currFreq = freqEntry.getValue().intValue();
                int pCharFreq = pCharFrequency.get(freqEntry.getKey()).intValue();

                if (!(currFreq == pCharFreq)) {
                    isAnagram = false;
                    break;
                }
            }
            
            if (isAnagram) {
                startIndices.add(i);
            }
        }
        
        return startIndices;
    }
}