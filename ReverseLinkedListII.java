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
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // Edge Cases
        if (head == null || head.next == null || left == right) {
            return head;
        }
        
        // Determine node before left, left, right, and after right nodes
        ListNode beforeLeftNode = head;
        ListNode leftNode = head;
        for (int i = 1; i < left; i++) {
            leftNode = leftNode.next;
            
            if (i == left - 2) {
                beforeLeftNode = leftNode;
            }
        }
        
        ListNode rightNode = head;
        for (int j = 1; j < right; j++) {
            rightNode = rightNode.next;
        }
        
        ListNode afterRightNode = rightNode;
        afterRightNode = afterRightNode.next;
        
        // Initialize the previous, current, and next pointers
        ListNode prev = leftNode;
        ListNode curr = prev.next;
        ListNode next = curr.next;
        
        // After pointer initialization, left node points to null
        prev.next = null;
        
        int numReversals = right - left;
        int reversalCount = 0;
        
        while (reversalCount < numReversals) {
            // Reverse the current connection
            curr.next = prev;
            
            // Updating the pointers
            prev = curr;
            curr = next;
            
            if (next != null) {
                next = next.next;
            }
            
            reversalCount++;
        }
        
        // Node before the left node points to the right node
        beforeLeftNode.next = rightNode;
        
        // Left node points to the node after the right node
        leftNode.next = afterRightNode;
        
        // If head is not included in the portion we want to modify
        if (left > 1) {
            return head;
        }
        // If head is included in the portion we want to modify
        else {
            return rightNode;
        }
    }
}