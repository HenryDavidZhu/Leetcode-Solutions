// O(k) time and O(1) space, with k being the number of set bits.
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int setBits = 0;
        
        while (n != 0) {
            setBits++;
            n &= (n - 1);
        }
        
        return setBits;
    }
}