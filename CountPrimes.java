class Solution {
    public int countPrimes(int n) {
        // O(sqrt(n) log log(n)) time, O(n) space
        if (n <= 2) {
            return 0;
        }
        
        // Step 1: Figure out all composite numbers.
        boolean[] isComposite = new boolean[n];
        for (int i = 2; i <= (int) Math.sqrt(n - 1); i++) {
            if (!isComposite[i]) {
                // Mark all multiples of i as composites
                int iMultiple = 2 * i;

                while (iMultiple <= n - 1) {
                    isComposite[iMultiple] = true;
                    iMultiple += i;
                }
            }
        }
        
        // Figure out all prime numbers
        int numPrimes = 0;
        for (int i = 2; i < n; i++) {
            if (!isComposite[i]) {
                numPrimes++;
            }
        }
        
        return numPrimes;
    }
}