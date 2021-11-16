// Greedy Algorithm: O(n) time and O(1) space.
class Solution {
    public boolean canThreePartsEqualSum(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        
        if (sum % 3 != 0) {
            return false;
        }
        
        int partSum = sum / 3;
        int currSum = 0;
        int numParts = 0;
        
        for (int i = 0; i < arr.length; i++) {
            currSum += arr[i];
            
            if (currSum == partSum) {
                numParts++;
                currSum = 0;
            }
        }
        
        if (numParts >= 3) {
            return true;
        }
        return false;
    }
}