class Solution {
    List<List<String>> partitionList = new LinkedList<List<String>>();
    
    public List<List<String>> partition(String s) {
        dfs(s, new LinkedList<String>());
        return partitionList;
    }
    
    private boolean isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        
        return true;
    }
    
    private void dfs(String remainingString, List<String> currPartition) {
        if (remainingString.length() == 0) {
            partitionList.add(currPartition);
        }
        
        for (int i = 0; i < remainingString.length(); i++) {
            String firstElement = remainingString.substring(0, i + 1);
            
            if (isPalindrome(firstElement)) {
                List<String> updatedPartition = new LinkedList<String>(currPartition);
                updatedPartition.add(firstElement);
                dfs(remainingString.substring(i + 1, remainingString.length()), updatedPartition);
            } 
        }
    }
}