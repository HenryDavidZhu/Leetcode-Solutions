class Solution {
    public int[][] kClosest(int[][] points, int k) {
        Map<Integer, Integer> pointIdx2DistSquared = new HashMap<Integer, Integer>();
        Queue<Integer> closestKPointIdxes = new PriorityQueue<Integer>((p1, p2) ->
                                            pointIdx2DistSquared.get(p2) - pointIdx2DistSquared.get(p1));
        
        for (int i = 0; i < points.length; i++) {
            int[] pt = points[i];
            pointIdx2DistSquared.put(i, pt[0] * pt[0] + pt[1] * pt[1]);
        }
        
        for (int j = 0; j < points.length; j++) {
            closestKPointIdxes.add(j);
            
            if (closestKPointIdxes.size() > k) {
                closestKPointIdxes.poll();
            }
        }
        
        int[][] kClosestPoints = new int[k][2];
        
        for (int m = 0; m < k; m++) {
            int[] point = points[closestKPointIdxes.poll()];
            kClosestPoints[m] = point;
        }
        
        return kClosestPoints;
    }
}