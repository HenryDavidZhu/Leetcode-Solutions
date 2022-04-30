class Solution {
    public int numDecodings(String s) {
        // Bottom-Up Dynamic Programming
        // Linear O(n) time and space.
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;

        for (int i = 2; i <= n; i++) {
            dp[i] = 0;

            char lastChar = s.charAt(i - 1);
            if (lastChar != '0') {
                dp[i] += dp[i - 1];
            }

            String lastTwoChars = s.substring(i - 2, i);
            Integer lastTwoCharsInt = Integer.parseInt(lastTwoChars);
            if (lastTwoCharsInt >= 10 && lastTwoCharsInt <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }
}
