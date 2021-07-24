class Solution {
    public void moveZeroes(int[] nums) {
        Queue<Integer> zeroIndexes = new LinkedList<Integer>();
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroIndexes.add(i);
            }
            else {
                if (!zeroIndexes.isEmpty()) {
                    int newPos = zeroIndexes.poll();
                    nums[newPos] = nums[i];
                    nums[i] = 0;
                    
                    zeroIndexes.add(i);
                }
            }
        }
    }
}