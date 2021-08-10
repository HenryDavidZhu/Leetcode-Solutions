class Solution {
    public String removeDuplicateLetters(String s) {
        // c, b, c
        char[] sChars = s.toCharArray();
            
        // Compute the last occurence of each character in the string
        Map<Character, Integer> lastOccurence = new HashMap<Character, Integer>();
        for (int i = 0; i < sChars.length; i++) {
            lastOccurence.put(sChars[i], i);
        }
        
        Stack<Character> alphabetStack = new Stack<Character>();
        for (int j = 0; j < s.length(); j++) {
            Character sChar = sChars[j];
            Character topCharacter = null;
            if (!alphabetStack.isEmpty()) {
                topCharacter = alphabetStack.peek();
            }
            
            if (!alphabetStack.contains(sChar)) {
                if (topCharacter != null) {
                    while (sChar < topCharacter) {
                        if (lastOccurence.get(topCharacter) > j) {
                            alphabetStack.pop();

                            if (!alphabetStack.isEmpty()) {
                                topCharacter = alphabetStack.peek();
                            } else {
                                break;
                            }
                        }
                        else {
                            break;
                        }
                    }
                }
                
                alphabetStack.add(sChar);
            }
        }
        
        StringBuilder alphabet = new StringBuilder();
        while (!alphabetStack.isEmpty()) {
            alphabet.insert(0, alphabetStack.pop());
        }
        
        return alphabet.toString();
    }
}