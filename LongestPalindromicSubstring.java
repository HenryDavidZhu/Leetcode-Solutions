class Solution {
    public String longestPalindrome(String s) {
        boolean[][] substrIsPalindrome = new boolean[s.length() + 1][s.length() + 1];
        String longestSubstrPalindrome = "";
        
        if (s.length() == 0) {
            return longestSubstrPalindrome;
        }
        
        if (s.length() >= 1) {
            longestSubstrPalindrome = String.valueOf(s.charAt(0));
        }
        
        for (int a = 0; a < s.length(); a++) {
            substrIsPalindrome[a][a] = true;
            substrIsPalindrome[a][a + 1] = true;
        }
        
        for (int substrLen = 2; substrLen <= s.length(); substrLen++) {
            for (int i = 0; i <= s.length() - substrLen; i++) {
                int j = i + substrLen - 1;
                String substr = s.substring(i, j + 1);
                
                if (substrLen == 2) {
                    substrIsPalindrome[i][j] = (s.charAt(i) == s.charAt(j));
                    
                    if (substrLen > longestSubstrPalindrome.length() &&
                        substrIsPalindrome[i][j]) {
                        longestSubstrPalindrome = substr;
                    }
                }
                else if (substrIsPalindrome[i + 1][j - 1]) {
                    substrIsPalindrome[i][j] = (s.charAt(i) == s.charAt(j));
                        
                    if (substrLen > longestSubstrPalindrome.length() &&
                        substrIsPalindrome[i][j]) {
                        longestSubstrPalindrome = substr;
                    }
                }
            }
        }

        return longestSubstrPalindrome;
    }
}