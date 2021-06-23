class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        
        Map<Character, Integer> substringIndexes = new HashMap<Character, Integer>();
        int maxSubstringLen = 0;
        
        for (int i = 0; i < s.length(); i++) {
            Character currChar = s.charAt(i);
                
            if (!substringIndexes.containsKey(currChar)) {
                substringIndexes.put(currChar, i);
            }
            else {
                maxSubstringLen = Math.max(maxSubstringLen, substringIndexes.size());
                
                int start = substringIndexes.get(currChar) + 1;
                substringIndexes = new HashMap<Character, Integer>();
                
                for (int j = start; j <= i; j++) {
                    currChar = s.charAt(j);
                    substringIndexes.put(currChar, j);
                }
            }
        }
        
        return Math.max(maxSubstringLen, substringIndexes.size());
    }
}