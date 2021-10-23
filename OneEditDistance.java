class Solution {
    // One-pass, O(n) time, O(1) space
    public boolean isOneEditDistance(String s, String t) {
        int lenS = s.length();
        int lenT = t.length();
        
        // Replacement: len(s) = len(t)
        if (lenS == lenT) {
            // Edge Case
            if (lenS == 0) {
                return false;
            }
            
            // If more than 1 corresponding pairs of chars are different, then
            // edit distance is not 1.
            int numDiffPairs = 0;
            for (int i = 0; i < lenS; i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    numDiffPairs++;
                    
                    if (numDiffPairs > 1) {
                        return false;
                    }
                }
            }
            
            if (numDiffPairs == 0) {
                return false;
            }
            
            return true;
        }
        // Insertion: len(s) + 1 = len(t)
        else if (lenS + 1 == lenT) {
            // Edge Case
            if (lenS == 0) {
                return true;
            }
            
            for (int i = 0; i < lenS; i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    // Ensure that all pairs from s[i:m) -> t[i+1:n) are equivalent
                    for (int j = i; j < lenS; j++) {
                        if (s.charAt(j) != t.charAt(j + 1)) {
                            return false;
                        }
                    }
                }
            }
            
            return true;
        }
        // Deletion: len(s) - 1 = len(t)
        else if (lenS - 1 == lenT) {
            // Edge Case
            if (lenS == 1) {
                return true;
            }
            
            for (int i = 0; i < lenT; i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    // Ensure that all pairs from s[i+1:m) -> t[i:n) are equivalent
                    for (int j = i + 1; j < lenS; j++) {
                        if (s.charAt(j) != t.charAt(j - 1)) {
                            return false;
                        }
                    }
                }
            }
            
            return true;
        }
        // Otherwise, edit distance isn't 1
        else {
            return false;
        }
    }
}