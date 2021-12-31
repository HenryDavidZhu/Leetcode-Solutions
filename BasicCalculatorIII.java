class Solution {
    public int calculate(String s) {
        // Time Complexity: O(n), Space Complexity: O(n)
        Queue<Character> q = new LinkedList<Character>();
        for (Character c : s.toCharArray()) {
            if (c != ' ') {
                q.add(c);
            }
        }
        q.add(' ');
        
        return computeExpression(q);
    }
    
    private int computeExpression(Queue<Character> sQueue) {
        int num = 0, prev = 0, sum = 0;
        Character prevOp = '+';
        
        while (!sQueue.isEmpty()) {
            Character c = sQueue.poll();
            
            if (Character.isDigit(c)) {
                num = num * 10 + Character.getNumericValue(c);
            }
            else if (c == '(') {
                num = computeExpression(sQueue);   
            }
            else {
                switch (prevOp) {
                    case '+':
                        sum += prev;
                        prev = num;
                        break;
                    case '-':
                        sum += prev;
                        prev = -num;
                        break;
                    case '*':
                        prev *= num;
                        break;
                    case '/':
                        prev /= num;
                        break;
                }
                
                if (c == ')') {
                    break;
                }
                prevOp = c;
                num = 0;
            }
        }
        
        return prev + sum;
    }
}