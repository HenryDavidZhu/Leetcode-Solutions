class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        
        boolean[][] canMakeS3Portion = new boolean[s1.length() + 1][s2.length() + 1];
        
        for (int r = 0; r <= s1.length(); r++) {
            for (int c = 0; c <= s2.length(); c++) {
                int currs3Index = r + c - 1;
                
                if (r == 0 && c == 0) {
                    canMakeS3Portion[0][0] = true;
                }
                else if (r == 0) {
                    if (canMakeS3Portion[r][c - 1]) {
                        canMakeS3Portion[r][c] = (s2.charAt(c - 1) == s3.charAt(currs3Index));
                    }
                }
                else if (c == 0) {
                    if (canMakeS3Portion[r - 1][c]) {
                        canMakeS3Portion[r][c] = (s1.charAt(r - 1) == s3.charAt(currs3Index));
                    }
                }
                else {
                    if (canMakeS3Portion[r][c - 1]) {
                        canMakeS3Portion[r][c] = (s2.charAt(c - 1) == s3.charAt(currs3Index));
                    }
                    
                    if (canMakeS3Portion[r - 1][c]) {
                        canMakeS3Portion[r][c] = (s1.charAt(r - 1) == s3.charAt(currs3Index));
                    }
                }
            }
        }
        
        return canMakeS3Portion[s1.length()][s2.length()];
    }
}