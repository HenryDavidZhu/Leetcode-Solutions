class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        Queue<Long> uglyNumbers = new PriorityQueue<Long>();
        uglyNumbers.add(new Long(1));
        Long currUglyNumber = new Long(1);
        
        Set<Long> visitedUglyNumbers = new HashSet<Long>();
        
        for (int i = 0; i < n; i++) {
            currUglyNumber = uglyNumbers.poll();
                
            for (int prime : primes) {
                Long nextUglyNumber = currUglyNumber * prime;
                    
                if (!visitedUglyNumbers.contains(nextUglyNumber)) {
                    uglyNumbers.add(nextUglyNumber);
                    visitedUglyNumbers.add(nextUglyNumber);
                }
            }
        }
        
        return currUglyNumber.intValue();
    }
}