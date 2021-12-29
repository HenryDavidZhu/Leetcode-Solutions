class Solution {
    // O(n) time, O(n) space.
    public int calculate(String s) {
        // "5 + 8 + 3 * 3 * 6+"
        // Stack that stores all the (operators + numbers) before a multiplication or division
        // operator. We don't compute addition and subtraction after we finish multiplication
        // or division.
        //
        // We keep track of the current number, the sign of that number (+ operator means 1, -
        // operator means -1), the default sign will be 1. We also keep track of whether or
        // not or current number follows a * or / operator, and if so, which * or / operator
        // it follows. This is because if the operator is + or -, we can simply add it to
        // the stack, but once we encounter a * or / operator, we need to immediately
        // * or / the  current number with the previous (operator + number), located at
        // the top of the stack, and then add that to the stack.
        //
        // Finally, if we have any remaining (operators + numbers) on the Stack, we add
        // them together to the final result.
        
        int sum = 0;
        Stack<Integer> expBeforeMultOrDiv = new Stack<Integer>();
        
        int num = 0;
        int sign = 1; // Operator (1 means +, -1 means -)
        
        boolean followMultOrDiv = false;
        Character multOrDiv = null;
        
        // Iterate through all the characters in the provided string.
        char[] sCharArray = s.toCharArray();
        
        for (int i = 0; i < sCharArray.length; i++) {
            // Case 1: We encounter a digit (we need to find the complete number)
            if (Character.isDigit(sCharArray[i])) {
                // Find the complete number.
                while (i < sCharArray.length && Character.isDigit(sCharArray[i])) {
                    num = 10 * num + Character.getNumericValue(sCharArray[i]);
                    i++;
                }
                
                // i went over by one.
                i--;
                
                // Add the operator and current number to the stack.
                if (!followMultOrDiv) {
                    expBeforeMultOrDiv.push(sign * num);
                }
                else {
                    if (multOrDiv == '*') {
                        Integer prevNumAndOperator = expBeforeMultOrDiv.pop();
                        expBeforeMultOrDiv.push(prevNumAndOperator * num);
                    }
                    if (multOrDiv == '/') {
                        Integer prevNumAndOperator = expBeforeMultOrDiv.pop();
                        expBeforeMultOrDiv.push(prevNumAndOperator / num);
                    }
                }
                
                // Reset the number and followMultOrDiv.
                num = 0;
                followMultOrDiv = false;
            }
            // Case 2: We encounter a +/-, then we simply add the current sum and the operator
            // to the stack.
            if (sCharArray[i] == '+') {
                sign = 1;
                followMultOrDiv = false;
            }
            if (sCharArray[i] == '-') {
                sign = -1;
                followMultOrDiv = false;
            }
            // Case 3: When we encoutner a * or /, then we update the sum by popping the last
            // value from the Stack and aplying either the * or / value on the top of the stack
            // and the current number.
            if (sCharArray[i] == '*') {
                followMultOrDiv = true;
                multOrDiv = '*';
            }
            if (sCharArray[i] == '/') {
                followMultOrDiv = true;
                multOrDiv = '/';
            }
        }
        
        int answer = 0;
        while (!expBeforeMultOrDiv.isEmpty()) {
            answer += expBeforeMultOrDiv.pop();
        }
        
        return answer;
    }
}