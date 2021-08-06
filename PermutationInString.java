class Solution {
    public boolean checkInclusion(String s1, String s2) {
        // Generate mapping of s1's characters to their frequency
        Map<Character, Integer> s1Frequency = new HashMap<Character, Integer>();
        for (Character character : s1.toCharArray()) {
            Integer charFrequency = 0;
            if (s1Frequency.containsKey(character)) {
                charFrequency = s1Frequency.get(character);
            }
            
            charFrequency++;
            s1Frequency.put(character, charFrequency);
        }
        
        // Iterate through each substring (sliding window) of s1's length in s2
        int startIndex = 0;
        int endIndex = s1.length() - 1;
        
        while (endIndex <= s2.length() - 1) {
            if (substrIsPermutation(s1Frequency, s2.substring(startIndex, endIndex + 1))) {
                return true;
            }
            
            startIndex++;
            endIndex++;
        }
        
        return false;
    }
    
    // Helper function to see if a sbustring in s2 is a permutation of s1
    private boolean substrIsPermutation(Map<Character, Integer> s1Frequency, String s2Substr) {
        Map<Character, Integer> s2Frequency = new HashMap<Character, Integer>();
        int correctCharsUsed = 0;
        for (Character s2Char : s2Substr.toCharArray()) {
            if (s1Frequency.containsKey(s2Char)) {
                Integer s2CharFrequency = 0;
                if (s2Frequency.containsKey(s2Char)) {
                    s2CharFrequency = s2Frequency.get(s2Char);
                }
                
                s2CharFrequency++;
                s2Frequency.put(s2Char, s2CharFrequency);
                
                if (s2CharFrequency > s1Frequency.get(s2Char)) {
                    return false;
                }
            }
            else {
                return false;
            }
        }
        
        return true;
    }
}