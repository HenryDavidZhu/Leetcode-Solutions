class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }
        
        if (prices.length == 2) {
            return Math.max(0, prices[1] - prices[0]);
        }
        
        int localMinimum = -1;
        int profit = 0;
        
        for (int i = 0; i < prices.length; i++) {
            if (i == 0) {
                if (prices[i] < prices[i + 1]) {
                    localMinimum = prices[i];
                }
            }
            else if (i == prices.length - 1) {
                if (prices[i] > prices[i - 1]) {
                    profit += (prices[i] - localMinimum);
                    localMinimum = -1;
                }
            }
            else {
                // Local minimum
                if (prices[i] <= prices[i - 1] && prices[i] < prices[i + 1]) {
                    localMinimum = prices[i];
                }

                // Local maximum
                if (prices[i] > prices[i - 1] && prices[i] >= prices[i + 1]) {
                    profit += (prices[i] - localMinimum);
                    localMinimum = -1;
                }
            }
        }
        
        return profit;
    }
}