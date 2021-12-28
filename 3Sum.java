// O(n^2) time complexity, O(1) space, depending on what sorting algorithm is used.
class Solution {
    List<List<Integer>> tripletList = new LinkedList<List<Integer>>();
    
    // Find all non-duplicate triplets that sums up to 0
    public List<List<Integer>> threeSum(int[] nums) {
        // Step 1: Sort the array (O(n * log(n)) time complexity).
        Arrays.sort(nums);
        
        // Step 2: Have a pointer i that represents the possible first index
        // of a triplet.
        for (int i = 0; i < nums.length - 2; i++) {
            if (i >= 1 && nums[i] == nums[i - 1]) {
                continue;
            }

            // Step 3: Determine j and k, which point to the possible second
            // and third index of the triplet.
            //
            // As low as j < k (this ensures that we don't have any duplicates),
            // we can break the situation down into 3 cases:
            // Case 1: nums[j] + nums[k] = target - nums[i].
            //     Create the triplet (i, j, k) and add it to our triplet list.
            //     Increment j so we can continue searching for more corresonding pairs.
            //
            // Case 2: nums[j] + nums[k] < target - nums[i].
            //     Increment j so we can increase (nums[j] + nums[k])
            //
            // Case 3: nums[j] + nums[k] > target - nums[i].
            //     Decrement k so we can decrease (nums[j] + nums[k])
            //
            // In the end, if we don't find a valid j and k, we just continue on.
            int j = i + 1;
            int k = nums.length - 1;
            int pairTarget = -nums[i];
            
            while (j < k) {
                // Case 1
                if (nums[j] + nums[k] == pairTarget) {
                    List<Integer> triplet = new LinkedList<Integer>();
                    triplet.add(nums[i]);
                    triplet.add(nums[j]);
                    triplet.add(nums[k]);
                    tripletList.add(triplet);
          
                    j++;
                    while (j < k && nums[j] == nums[j - 1]) {
                        j++;
                    }
                }
                // Case 2
                if (nums[j] + nums[k] < pairTarget) {
                    j++;
                }
                // Case 3
                if (nums[j] + nums[k] > pairTarget) {
                    k--;
                }
            }
        }
        
        return tripletList;
    }
}