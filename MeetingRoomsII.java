class Solution {
    public int minMeetingRooms(int[][] intervals) {
        List<int[]> sortedIntervals = new LinkedList<int[]>();
        for (int[] interval : intervals) {
            sortedIntervals.add(interval);
        }
        Collections.sort(sortedIntervals, new Comparator<int[]>() {    
            @Override
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }               
        });

        Queue<Integer> roomEndMinHeap = new PriorityQueue<Integer>();
        int numRooms = 0;
        
        for (int[] interval : sortedIntervals) {
            if (roomEndMinHeap.size() == 0) {
                roomEndMinHeap.add(interval[1]);
                numRooms++;
            }
            else {
                Integer roomEndEarliestTime = roomEndMinHeap.peek();
                
                // Insert current meeting into the room that ends earliest
                if (roomEndEarliestTime <= interval[0]) {
                    roomEndMinHeap.poll();
                    roomEndMinHeap.add(interval[1]);
                }
                // Need to create new room for current meeting
                else {
                    roomEndMinHeap.add(interval[1]);
                    numRooms++;
                }
            }
        }
        
        return numRooms;
    }
}