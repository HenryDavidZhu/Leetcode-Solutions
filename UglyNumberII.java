import java.math.BigInteger;

class Solution {
    // Time Complexity: O(n log n)
    // Space Complexity: O(n)
    public int nthUglyNumber(int n) {
        Queue<BigInteger> minHeap = new PriorityQueue<BigInteger>();
        Set<BigInteger> numsVisited = new HashSet<BigInteger>();
        minHeap.add(BigInteger.valueOf(1));
        BigInteger currUglyNumber = BigInteger.valueOf(1);
        
        for (int i = 1; i <= n; i++) {
            currUglyNumber = minHeap.poll();
            
            BigInteger[] currUglyMultiples = new BigInteger[] {currUglyNumber.multiply(BigInteger.valueOf(2)), currUglyNumber.multiply(BigInteger.valueOf(3)), currUglyNumber.multiply(BigInteger.valueOf(5))};
            for (BigInteger currUglyMultiple : currUglyMultiples) {
                if (!numsVisited.contains(currUglyMultiple)) {
                    minHeap.add(currUglyMultiple);
                }
                numsVisited.add(currUglyMultiple);
            }
        }
        
        return currUglyNumber.intValue();
    }
}