// O(1) time and space, since integer has at most 32 bits.
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int bitMask = 1;
        int setBits = 0;
        
        for (int i = 0; i < 32; i++) {
            if ((n & bitMask) != 0) {
                setBits++;
            }
            bitMask <<= 1;
        }
        
        return setBits;
    }
}