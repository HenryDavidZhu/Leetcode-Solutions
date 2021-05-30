class Solution {
    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
        // Construct tree w/ root node being the parent region containing the
        // subregions represented by its children. Represent this tree as an
        // adjacency list.
        //
        // Find the root of the tree: Starting out, the root is going to be the
        // first key of our adjacency list, but as we go through the rest of 
        // the adjacency list's keys, if the current key we are analyzing contains 
        // the root node as its subregion, update the root to be the current key.
        
        Map<String, List<String>> adjacencyList = new HashMap<String, List<String>>();
        String root = regions.get(0).get(0);
        
        for (List<String> regionList : regions) {
            String parentRegion = regionList.get(0);
            List<String> subRegions = regionList.subList(1, regionList.size());
            
            if (subRegions.contains(root)) {
                root = parentRegion;
            }
            
            adjacencyList.put(parentRegion, subRegions);
        }

        // Find LCA (lowest common ancestor) of region 1 and region 2 from the tree:
        // Finding the paths from the root node to regions 1 and 2 and then finding
        // the last node shared by both paths.
        
        List<String> pathToRegion1 = new LinkedList<String>();
        List<String> pathToRegion2 = new LinkedList<String>();
        
        findPathToSubregion(adjacencyList, root, region1, pathToRegion1);
        findPathToSubregion(adjacencyList, root, region2, pathToRegion2);
        
        String smallestRegion = "";
        int minPathLength = Math.min(pathToRegion1.size(), pathToRegion2.size());
        
        for (int i = 0; i < minPathLength; i++) {
            // Check if we have found an ancestor
            if (pathToRegion1.get(i).equals(pathToRegion2.get(i))) {
                smallestRegion = pathToRegion1.get(i);
            }
        }
        
        return smallestRegion;
    }
    
    // Determines the path from the tree's root region to the target subregion using
    // a DFS (Depth-First Search) traversal
    private boolean findPathToSubregion(Map<String, List<String>> adjacencyList, String root, String target, List<String> pathToBuild) {
        pathToBuild.add(root);
        
        // Check if current root node is part of the path to the target:
        // Case 1: We have reached the target region
        if (root.equals(target)) {
            return true;
        }
        
        // Case 2: If any one of the root's subregions include the target region
        if (adjacencyList.containsKey(root)) {
            for (String subregion : adjacencyList.get(root)) {
                if (findPathToSubregion(adjacencyList, subregion, target, pathToBuild)) {
                    return true;
                }
            }
        }
        
        // If the current root node is NOT part of the path to the target, we need to
        // backtrack and remove it from the path we constructed.
        pathToBuild.remove(pathToBuild.size() - 1);
        
        return false;
    }
}