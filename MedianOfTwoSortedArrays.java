class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int mergedLen = m + n;

        if (m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int start = 0;
        int end = m;

        while (start <= end) {
            int i = (start + end) / 2;
            int j = (mergedLen + 1)/2 - i;

            int maxLeftA = Integer.MIN_VALUE;
            if (i != 0) {
                maxLeftA = nums1[i - 1];
            }

            int maxLeftB = Integer.MIN_VALUE;
            if (j != 0) {
                maxLeftB = nums2[j - 1];
            }

            int minRightA = Integer.MAX_VALUE;
            if (i != m) {
                minRightA = nums1[i];
            }

            int minRightB = Integer.MAX_VALUE;
            if (j != n) {
                minRightB = nums2[j];
            }

            if (maxLeftA <= minRightB && maxLeftB <= minRightA) {
                if (mergedLen % 2 != 0) {
                    return (double) Math.max(maxLeftA, maxLeftB);
                }
                else {
                    int maxLH = Math.max(maxLeftA, maxLeftB);
                    int minRH = Math.min(minRightA, minRightB);

                    return ((double) (maxLH + minRH)) / 2.0;
                }
            }
            else if (maxLeftA > minRightB) {
                end = i - 1;
            }
            else {
                start = i + 1;
            }
        }

        return -1;
    }
}
