class Solution {
    // O(n) time, O(1) space.
    public int calculate(String s) {
        // Proivded Expression: 356 + 5 * 5 * 5 - 63 + 45
        // Auxillary Notation: [+ 0] - 356 + 5 * 5 * 5 - 63 + 45
        //
        // Go through each character and perform the following procedure
        //
        // -----------------------------------------------------
        //
        // Case 1: We encounter a digit.
        // Get the complete number and set currNum to that.
        //        
        // Case 2: We encounter an operator (+/*/divide/-) or we have
        // reached the last character.
        //  (a). Operator is + (then we can add previous num)
        //       -> if previous operator is +, then prevNum = currNum
        //       -> if previous operator is -, then prevNum = -currNum
        //  (b). Operator is - (then we can add previous num)
        //       -> if previous operator is +, then prevNum = currNum
        //       -> if previous operator is -, then prevNum = -currNum
        //  (c). Operator is * (prevNum = prevNum * currNum)
        //  (d). Operator is / (prevNum = prevNum / currNum)
        //
        // Finally, we reset currNum and the operation.
        // currNum = 0
        // operation is current character
        //
        // -----------------------------------------------------
        //
        // Finally, add prevNum to result and return result.
        int prevNum = 0;
        int currNum = 0;
        Character prevOp = '+';
        int result = 0;
        
        char[] sCharArray = s.toCharArray();
        
        for (int i = 0; i < sCharArray.length; i++) {
            if (Character.isDigit(sCharArray[i])) {
                currNum = 10 * currNum + Character.getNumericValue(sCharArray[i]);
            }
            if (!Character.isDigit(sCharArray[i]) && sCharArray[i] != ' ' || i == sCharArray.length - 1) {
                switch (prevOp) {
                    case '+':
                        result += prevNum;
                        prevNum = currNum;
                        break;
                    case '-':
                        result += prevNum;
                        prevNum = -currNum;
                        break;
                    case '*':
                        prevNum *= currNum;
                        break;
                    case '/':
                        prevNum /= currNum;
                        break;
                }
                
                prevOp = sCharArray[i];
                currNum = 0;
            }
        }
        
        result += prevNum;
        return result;
    }
} 