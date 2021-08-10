/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Node {
    int index;
    int val;
    
    public Node(int index, int val) {
        this.index = index;
        this.val = val;
    }
}

class Solution {
    public int[] nextLargerNodes(ListNode head) {
        Map<Integer, Integer> nxtLargerNodeMap = new HashMap<Integer, Integer>();
        Stack<Node> stack = new Stack<Node>();
        
        ListNode curr = head;
        int index = 0;
        while (curr != null) {
            Integer topElement = stack.isEmpty() ? 0 : stack.peek().val;
            nxtLargerNodeMap.put(index, 0);
            
            // Current node is a next greater element
            if (curr.val > topElement && topElement > 0) { 
                // Find all the nodes that the current node is a next greater element
                while (curr.val > topElement) {
                    Node prevNode = stack.pop();
                    nxtLargerNodeMap.put(prevNode.index, curr.val);
                    
                    if (stack.isEmpty()) {
                        break;
                    }
                    else {
                        topElement = stack.peek().val;
                    }
                }
            }
            stack.add(new Node(index, curr.val));
            
            index++;
            curr = curr.next;
        }
        
        int[] nxtLargerNodes = new int[nxtLargerNodeMap.size()];
        for (Map.Entry<Integer, Integer> entry : nxtLargerNodeMap.entrySet()) {
            nxtLargerNodes[entry.getKey()] = entry.getValue();
        }
        
        return nxtLargerNodes;
    }
}