class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Set<Character> substringChars = new HashSet<Character>();
        int start = 0;
        int maxSubstringLen = 0;
        
        for (int i = 0; i < s.length(); i++) {
            Character currChar = s.charAt(i);
            
            if (!substringChars.contains(currChar)) {
                // More than 2 distinct characters
                if (substringChars.size() == 2) {
                    maxSubstringLen = Math.max(maxSubstringLen, i - start);
                    substringChars = new HashSet<Character>();
                    Character lastChar = Character.MIN_VALUE;
                    
                    for (int j = i - 1; j >= 0; j--) {
                        if (lastChar == Character.MIN_VALUE) {
                            lastChar = s.charAt(j);
                            start = j;
                        }
                        else if (s.charAt(j) == lastChar) {
                            start = j;
                        }
                        else {
                            break;
                        }
                    }
                    
                    for (int k = start; k <= i; k++) {
                        substringChars.add(s.charAt(k));
                    }
                } else {
                    substringChars.add(currChar);
                }
            }
        }

        return Math.max(maxSubstringLen, (s.length() - 1) - start + 1);
    }
}