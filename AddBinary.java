class Solution {
    public String addBinary(String a, String b) {
        int aLen = a.length();
        int bLen = b.length();
        int maxLen = Math.max(aLen, bLen);
        
        StringBuilder sum = new StringBuilder();
        
        int carry = 0;
        for (int i = 0; i < maxLen; i++) {
            int aDigit = 0;
            int bDigit = 0;
            
            if (aLen == maxLen) {
                aDigit = Character.getNumericValue(a.charAt(maxLen - i - 1));
                
                int bIndex = bLen - i - 1;
                bDigit = bIndex >= 0 ? Character.getNumericValue(b.charAt(bIndex)) : 0;
            }
            else {
                bDigit = Character.getNumericValue(b.charAt(maxLen - i - 1));
                
                int aIndex = aLen - i - 1;
                aDigit = aIndex >= 0 ? Character.getNumericValue(a.charAt(aIndex)) : 0;
            }
            
            int colSum = carry + aDigit + bDigit;
            if (colSum == 0) {
                sum.insert(0, "0");
                carry = 0;
            }
            if (colSum == 1) {
                sum.insert(0, "1");
                carry = 0;
            }
            
            if (i == maxLen - 1) {
                if (colSum == 2) {
                    sum.insert(0, "10");
                }
                if (colSum == 3) {
                    sum.insert(0, "11");
                }        
            }
            else {
                if (colSum == 2) {
                    sum.insert(0, "0");
                    carry = 1;
                }
                if (colSum == 3) {
                    sum.insert(0, "1");
                    carry = 1;
                }
            }
        }
        
        return sum.toString();
    }
}