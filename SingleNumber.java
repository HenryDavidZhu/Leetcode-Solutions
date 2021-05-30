class Solution {
    public int singleNumber(int[] nums) {
        // A ^ A = 0
        // 0 ^ 0 = 0
        // A ^ 0 = A
        //
        // Example:
        // [A, A, C, C, D, B, D, E, E]
        // 
        //   A ^ A ^ C ^ C ^ D ^ B ^ D ^ E ^ E
        // = [(A ^ A) ^ (C ^ C) ^ (D ^ D) ^ (E ^ E)] ^ B
        // = (0 ^ 0 ^ 0 ^ 0) ^ B
        // = 0 ^ B
        // = B
        //
        // Time Complexity: 
        // We need to go through N elements O(N), and XOR each of them O(1),
        // yielding a total time complexity of O(N * 1) = O(N)
        //
        // Space Complexity:
        // Constant O(1), because we just store the unique element.
        
        int uniqueElement = 0;
        
        for (int num : nums) {
            uniqueElement ^= num;
        }
        
        return uniqueElement;
    }
}