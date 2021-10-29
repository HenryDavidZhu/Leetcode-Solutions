class Solution {
    // O(n^2) time, O(n) space, where n is number of points.
    public int minAreaRect(int[][] points) {
        // We need at least 4 points to form a rectangle.
        if (points.length < 4) {
            return 0;
        }
        
        // HashSet storing the list of points, represented as a Pair<Integer, Integer>, so we can check if a point exists in O(1) time.
        HashSet<Pair<Integer, Integer>> pointSet = new HashSet<Pair<Integer, Integer>>();
        for (int[] point : points) {
            pointSet.add(new Pair<Integer, Integer>(point[0], point[1]));
        }
        
        boolean foundRectangle = false;
        int minArea = Integer.MAX_VALUE;
        
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                // Check that the points do not lie on the same line.
                int[] point1 = points[i];
                int[] point2 = points[j];
                
                if (point1[0] != point2[0] && point1[1] != point2[1]) {
                    // Point 3 = [p2(x), p1(y)]
                    Pair<Integer, Integer> point3 = new Pair<Integer, Integer>(point2[0], point1[1]);
                    // Point 4 = [p1(x), p2(y)]
                    Pair<Integer, Integer> point4 = new Pair<Integer, Integer>(point1[0], point2[1]);
                    
                    // Check if Points 3 and 4 exist.
                    if (pointSet.contains(point3) && pointSet.contains(point4)) {
                        // Compute the area and update the minimum area if necessary.
                        int area = Math.abs(point3.getKey() - point1[0]) * Math.abs(point3.getValue() - point2[1]);
                        minArea = Math.min(minArea, area);
                        
                        foundRectangle = true;
                    }
                }
            }
        }
        
        return foundRectangle ? minArea : 0;
    }
}