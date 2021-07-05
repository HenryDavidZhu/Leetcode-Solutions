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
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode originalHead = head;
        ListNode prev = head;
        ListNode curr = head.next;
        ListNode newHead = curr;
        
        originalHead.next = null;
        
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            
            newHead = curr;
            prev = curr;
            curr = next;
        }
        
        return newHead;
    }
}