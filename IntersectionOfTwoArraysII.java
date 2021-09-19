class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> nums1Frequencies = new HashMap<Integer, Integer>();
        for (int num1 : nums1) {
            int num1Frequency = nums1Frequencies.getOrDefault(num1, 0);
            num1Frequency++;
            nums1Frequencies.put(num1, num1Frequency);
        }
        
        Map<Integer, Integer> nums2Frequencies = new HashMap<Integer, Integer>();
        for (int num2 : nums2) {
            int num2Frequency = nums2Frequencies.getOrDefault(num2, 0);
            num2Frequency++;
            nums2Frequencies.put(num2, num2Frequency);
        }
        
        Set<Integer> num1Set = nums1Frequencies.keySet();
        Set<Integer> num2Set = nums2Frequencies.keySet();
        
        List<Integer> sharedElements = new LinkedList<Integer>();
        for (int number2 : num2Set) {
            if (num1Set.contains(number2)) {
                int sharedOccurences = Math.min(nums1Frequencies.get(number2), nums2Frequencies.get(number2));
                for (int i = 0; i < sharedOccurences; i++) {
                    sharedElements.add(number2);
                }
            }
        }
        
        int[] sharedElementsArr = new int[sharedElements.size()];
        for (int j = 0; j < sharedElements.size(); j++) {
            sharedElementsArr[j] = sharedElements.get(j);
        }
        
        return sharedElementsArr;
    }
}