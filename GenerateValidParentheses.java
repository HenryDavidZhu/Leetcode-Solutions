class Solution {
    Set<String> validParentheses = new HashSet<String>();
    
    public List<String> generateParenthesis(int n) {
        recursiveDFS(new StringBuilder(), 0, 0, n);
        
        List<String> validParenthesis = new LinkedList<String>();
        for (String validStr : validParentheses) {
            validParenthesis.add(validStr);
        }
        
        return validParenthesis;
    }
    
    private void recursiveDFS(StringBuilder currString, int leftCount, int rightCount, int n) {
        // Base Case.
        if (currString.length() == 2 * n) {
            validParentheses.add(currString.toString());
            return;
        }
        
        // Only add a left parentheses if we haven't used up all the left parentheses.
        if (leftCount < n) {
            currString.append('(');
            recursiveDFS(currString, leftCount + 1, rightCount, n);
            currString.deleteCharAt(currString.length() - 1);
        }
        
        // Only add a right parentheses if we haven't used up all the right parentheses
        // AND we've used more left parentheses than right parentheses.
        if (rightCount < n && leftCount > rightCount) {
            currString.append(')');
            recursiveDFS(currString, leftCount, rightCount + 1, n);
            currString.deleteCharAt(currString.length() - 1);
        }
    }
}