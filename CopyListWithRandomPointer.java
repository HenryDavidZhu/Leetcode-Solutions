class Solution {
    // O(n) time, O(n) space
    Map<Node, Node> createdNodes = new HashMap<Node, Node>();
    
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        if (createdNodes.containsKey(head)) {
            return createdNodes.get(head);
        }
        
        Node copiedHead = new Node(head.val);
        createdNodes.put(head, copiedHead);
        
        copiedHead.next = copyRandomList(head.next);
        copiedHead.random = copyRandomList(head.random);
        
        return copiedHead;
    }
}