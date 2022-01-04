class Solution {
    public int calculate(String s) {
        // Convert the provided string into a Queue.
        Queue<Character> sQueue = new LinkedList<Character>();
        for (Character sChar : s.toCharArray()) {
            sQueue.add(sChar);
        }
        sQueue.add(' ');
        
        return computeExpression(sQueue);
    }
    
    // Evaluates the result of an expression provided as a Queue using the
    // following recursive algorithm:
    //
    // [A] + ([B] + ([C])) will be evaluated as:
    // [A] + computeExpression([B] + ([C]))
    // computeExpression([B] + ([C])) = [B] + computeExpression([C])
    private int computeExpression(Queue<Character> sQueue) {
        int prev = 0;
        int curr = 0;
        int result = 0;
        Character prevOp = '+';
        
        while (!sQueue.isEmpty()) {
            Character currChar = sQueue.poll();
            
            if (Character.isDigit(currChar)) {
                curr = 10 * curr + Character.getNumericValue(currChar);
            }
            else if (currChar == '(') {
                curr = computeExpression(sQueue);
            }
            else {
                switch (prevOp) {
                    case '+':
                        result = result + prev;
                        prev = curr;
                        break;
                    case '-':
                        result = result + prev;
                        prev = -curr;
                        break;
                    case '*':
                        prev = prev * curr;
                        break;
                    case '/':
                        prev = prev / curr;
                        break;
                }
                
                if (currChar == ')') {
                    break;
                }
                
                prevOp = currChar;
                curr = 0;
            }
        }
        
        return prev + result;
    }
}