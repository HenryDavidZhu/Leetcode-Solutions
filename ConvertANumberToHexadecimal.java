class Solution {
    public String toHex(int num) {
        // Base Case
        if (num == 0) {
            return "0";
        }
        
        StringBuilder result = new StringBuilder();
        char[] hexDigits = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                                       'a', 'b', 'c', 'd', 'e', 'f', 'g'};
        
        while (num != 0) {
            result.append(hexDigits[num & 0b1111]);
            num >>>= 4;
        }
        
        return result.reverse().toString();
    }
}