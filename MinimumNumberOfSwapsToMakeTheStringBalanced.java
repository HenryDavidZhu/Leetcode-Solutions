class Solution {
    public int minSwaps(String s) {
        Stack<Character> unpaired = new Stack<Character>();
        int swaps = 0;
        
        for (Character character : s.toCharArray()) {
            // Left parentheses
            if (character == '[') {
                unpaired.add('[');
            }
            // Right parentheses
            else {
                if (!unpaired.isEmpty() && unpaired.peek() == '[') {
                    unpaired.pop();
                }
                else {
                    swaps++;
                }
            }
        }
        
        return swaps % 2 == 0 ? swaps / 2 : (swaps / 2) + 1;
    }
}