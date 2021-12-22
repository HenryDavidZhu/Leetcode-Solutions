class Solution { 
    // O(n) time and O(n) space 
    public String minRemoveToMakeValid(String s) { 
        if (s.isEmpty()) { 
            return s; 
        } 
             
        Stack<Character> unpairedParentheses = new Stack<Character>(); 
        Stack<Integer> unpairedIndexes = new Stack<Integer>(); 
         
        int index = 0; 
        for (Character character : s.toCharArray()) { 
            if (character == '(') { 
                unpairedParentheses.add(character); 
                unpairedIndexes.add(index); 
            } 
            if (character == ')') { 
                // Can I pair up this ) with a ( 
                if (!unpairedParentheses.isEmpty() && unpairedParentheses.peek() == '(') { 
                    unpairedParentheses.pop(); 
                    unpairedIndexes.pop(); 
                } 
                else { 
                    unpairedParentheses.add(character); 
                    unpairedIndexes.add(index); 
                } 
            } 
             
            index++; 
        } 
         
        HashSet<Integer> unpairedIndexSet = new HashSet<Integer>(); 
        for (Integer unpairedIndex : unpairedIndexes) { 
            unpairedIndexSet.add(unpairedIndex); 
        } 
         
        StringBuilder correctString = new StringBuilder(); 
        for (int i = 0; i < s.length(); i++) { 
            if (!unpairedIndexSet.contains(i)) { 
                correctString.append(s.charAt(i)); 
            } 
        } 
         
        return correctString.toString(); 
    } 
} 
