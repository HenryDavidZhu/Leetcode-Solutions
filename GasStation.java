class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        /* 
           Time Complexity: O(n)
           Space Complexity: O(1)
        */
        
        int n = gas.length;
        
        // Go through possible start stations
        for (int i = 0; i < n; i++) {
            int gasLevel = 0;
            boolean canFinish = true;

            // Iterate clockwise through the gas stations:
            // Go from the current station to the nth station
            for (int j = i; j < n; j++) {
                // Fill up the gas.
                gasLevel += gas[j];

                // Update our gas level after we go to the next station
                gasLevel -= cost[j];

                // If we can't go to the next gas station
                if (gasLevel < 0) {
                    canFinish = false;
                    break;
                }
            }

            // Go from 1st station to the last station before the current station
            if (canFinish) {
                for (int k = 0; k < i; k++) {
                    // Fill up the gas.
                    gasLevel += gas[k];

                    // Update our gas level after we go to the next station
                    gasLevel -= cost[k];

                    // If we can't go to the next gas station
                    if (gasLevel < 0) {
                        canFinish = false;
                        break;
                    }
                }
            }

            // See if we have found the starting gas station index where we can
            // travel around the circuit once in a clockwise station
            if (canFinish) {
                return i;
            }
        }
        
        return -1;
    }
}