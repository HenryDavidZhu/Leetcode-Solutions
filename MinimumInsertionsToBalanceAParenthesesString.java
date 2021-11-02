class Solution {
    // O(n) time, O(1) space
    public int minInsertions(String s) {
        int unpairedLefts = 0;
        int insertions = 0;
        
        for (int i = 0; i < s.length(); i++) {
            Character character = s.charAt(i);
            
            if (character == '(') {
                unpairedLefts++;
            }
            
            if (character == ')') {
                // Next character is also ), so we have ))
                if (i < s.length() - 1 && s.charAt(i + 1) == ')') {
                    // We pair )) with the (
                    if (unpairedLefts > 0) {
                        unpairedLefts--;
                    }
                    // We have an unpaired )), but we only need to insert (
                    // to have the )) paired up.
                    else {
                        insertions++;
                    }
                    
                    // We analyzed two characters, so skip a character.
                    i++;
                }
                // Next character is not )
                else {
                    // We can cancel out a waiting (, but we need one more )
                    if (unpairedLefts > 0) {
                        unpairedLefts--;
                        insertions++;
                    }
                    // If we have a standalone ), we need to add a () before it
                    // to cancel it out.
                    else {
                        insertions += 2;
                    }
                }
            }
        }
        
        // For an unpaired (, we need to add )) to pair it up.
        insertions += 2 * unpairedLefts;
        return insertions;
    }
}