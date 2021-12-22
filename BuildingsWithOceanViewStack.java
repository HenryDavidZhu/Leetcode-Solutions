class Solution { 
    // O(n) time, O(n) space 
    public int[] findBuildings(int[] heights) {    
        Stack<Integer> buildingHeightsWOceanView = new Stack<Integer>(); 
        Stack<Integer> buildingIndexesWOceanView = new Stack<Integer>(); 
         
        for (int i = 0; i < heights.length; i++) { 
            // Current building does block building(s) in the Stack. 
            if (buildingHeightsWOceanView.size() > 0 && heights[i] >= buildingHeightsWOceanView.peek()) { 
                while (buildingHeightsWOceanView.size() > 0 && buildingHeightsWOceanView.peek() <= heights[i]) { 
                    buildingHeightsWOceanView.pop(); 
                    buildingIndexesWOceanView.pop(); 
                } 
            } 
                          
            buildingHeightsWOceanView.add(heights[i]); 
            buildingIndexesWOceanView.add(i); 
        } 
         
        int[] buildingsWithOceanView = new int[buildingIndexesWOceanView.size()]; 
        for (int i = buildingIndexesWOceanView.size() - 1; i >= 0; i--) { 
            buildingsWithOceanView[i] = buildingIndexesWOceanView.pop(); 
        } 
         
        return buildingsWithOceanView; 
    } 
} 