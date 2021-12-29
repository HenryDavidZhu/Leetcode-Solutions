class Solution {
    // Stack-Based Solution
    // O(n) time and space (iterate through each character of the string)
    // and add it to the Stack.
    public int calculate(String s) {
        int sign = 1; // 1 = +, -1 = -.
        int sum = 0;
        
        // The stack stores all the sums before the left parentheses,
        // so that when we encounter a right parentheses, we can add
        // our current sum to the sum in the parentheses in the next
        // outer level.
        Stack<Integer> sumsBeforeLeftParentheses = new Stack<Integer>();
        
        char[] sChars = s.toCharArray();
        
        // Iterate through each character in the provided string.
        for (int i = 0; i < sChars.length; i++) {
            Character currChar = sChars[i];
            
            // Case 1: Character is a digit (find the complete number) and add or
            // subtract it to our current parentheses' sum.
            if (Character.isDigit(currChar)) {
                int num = 0;
                
                // Find all the digits in the number.
                while (i < sChars.length && Character.isDigit(sChars[i])) {
                    num = num * 10;
                    num += Character.getNumericValue(sChars[i]);
                    
                    // Move onto the next digit.
                    i++;
                }
                
                sum += sign * num;
                
                // We went over by one index.
                i--;
            }
            // Case 2: Character is + (change the sign).
            else if (currChar == '+') {
                sign = 1;
            }
            // Case 3: Character is - (change the sign).
            else if (currChar == '-') {
                sign = -1;
            }
            // Case 4: Character is ( (add the current sum to the stack)
            else if (currChar == '(') {
                // Add our current parentheses' sum and sign to the stack.
                sumsBeforeLeftParentheses.push(sum);
                sumsBeforeLeftParentheses.push(sign);
                
                // Reset the sum and sign.
                sum = 0;
                sign = 1;
            }
            // Case 5: Character is ) (add to the current sum to the top sum in the stack).
            else if (currChar == ')') {
                // Stack order: [previous sum], [operator to current sum], [current sum]
                sum *= sumsBeforeLeftParentheses.pop();
                sum += sumsBeforeLeftParentheses.pop();
            }
        }
        
        return sum;
    }
}