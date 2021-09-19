class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<Integer>();
        for (int num1 : nums1) {
            set1.add(num1);
        }
        
        Set<Integer> sharedSet = new HashSet<Integer>();
        for (int num2 : nums2) {
            if (set1.contains(num2)) {
                sharedSet.add(num2);
            }
        }
        
        int[] sharedArr = new int[sharedSet.size()];
        int i = 0;
        for (int sharedNum : sharedSet) {
            sharedArr[i] = sharedNum;
            i++;
        }
        
        return sharedArr;
    }
}