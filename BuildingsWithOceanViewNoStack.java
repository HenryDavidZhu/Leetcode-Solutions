class Solution { 
    public int[] findBuildings(int[] heights) { 
        int maxHeight = -1; 
        ArrayList<Integer> buildingsWOceanView = new ArrayList<Integer>(); 
         
        for (int i = heights.length - 1; i >= 0; i--) { 
            if (maxHeight == -1 || heights[i] > maxHeight) { 
                maxHeight = heights[i]; 
                buildingsWOceanView.add(i); 
            } 
        } 
         
        int[] result = new int[buildingsWOceanView.size()]; 
        for (int i = buildingsWOceanView.size() - 1; i >= 0; i--) { 
            result[buildingsWOceanView.size() - 1 - i] = buildingsWOceanView.get(i); 
        } 
         
        return result; 
    } 
} 
