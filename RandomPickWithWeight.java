// O(n) space 
class Solution { 
    private ArrayList<Double> probPercentages = new ArrayList<Double>(); 
    private ArrayList<Pair<Double, Double>> randomRanges = new ArrayList<Pair<Double, Double>>(); 
     
    // O(n) time, O(n) space 
    public Solution(int[] w) { 
        // Step 1: Generate the probability percentages for each index. 
        // 
        // E.x. [1, 3] -> [0.25, 0.75] 
        // Probability for i: w[i]/sum(w) 
        double sum = 0.0d; 
        for (int wI : w) { 
            sum += wI; 
        } 
        for (int wI : w) { 
            probPercentages.add(wI / sum); 
        } 
         
        // Step 2: Generate the range of possible percentages that 
        // each index falls under when we generate a random percentage 
        // from 0 to 100% to determine 
        // 
        // E.x. [0.25, 0.75] -> [[0, 0.25], [0.25, 1]] 
        Double start = 0.0d; 
        Double end = 0.0d; 
         
        for (Double probPercentage : probPercentages) { 
            start = end; 
            end += probPercentage; 
            randomRanges.add(new Pair<Double, Double>(start, end)); 
        } 
    } 
     
    // O(n) time, O(1) space 
    public int pickIndex() { 
        Double randomDouble = new Random().nextDouble(); 
        int index = 0; 
        for (Pair<Double, Double> randomRange : randomRanges) { 
            if (randomDouble >= randomRange.getKey() && randomDouble <= randomRange.getValue()) { 
                return index; 
            } 
             
            index++; 
        } 
         
        return -1; 
    } 
} 
 
/** 
 * Your Solution object will be instantiated and called as such: 
 * Solution obj = new Solution(w); 
 * int param_1 = obj.pickIndex(); 
 */ 