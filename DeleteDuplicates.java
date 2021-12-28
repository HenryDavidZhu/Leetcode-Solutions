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
// O(n) time, O(1) space
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        
        ListNode curr = head;
        while (curr != null) {
            // There is a duplicate node to the current node.
            if (curr.next != null && curr.next.val == curr.val) {
                // We find the next node that is greater than the current node.
                ListNode nextNode = curr.next;
                while (nextNode != null) {
                    if (nextNode.val == curr.val) {
                        nextNode = nextNode.next;
                    } else {
                        break;
                    }
                }
                
                curr.next = nextNode;
            }
            
            curr = curr.next;
        }
        
        return head;
    }
}