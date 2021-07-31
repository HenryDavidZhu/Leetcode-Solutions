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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode digit1 = l1;
        ListNode digit2 = l2;
        int carry = 0;
        ListNode prev = null;
        ListNode head = null;
        
        while (digit1 != null || digit2 != null) {
            int digit1Val = digit1 == null ? 0 : digit1.val;
            int digit2Val = digit2 == null ? 0 : digit2.val;
            int columnSum = digit1Val + digit2Val + carry;
            ListNode sumDigit = new ListNode(columnSum);
            
            if (columnSum >= 10) {
                sumDigit.val -= 10;
                carry = 1;
            }
            else {
                carry = 0;
            }
            
            if (head == null) {
                head = sumDigit;
                prev = head;
            }
            else {
                prev.next = sumDigit;
                prev = sumDigit;
            }
            
            // edge case: carry at the last column
            if (digit1 != null && digit1.next == null && digit2 == null ||
                digit1 == null && digit2 != null && digit2.next == null ||
                digit1 != null && digit1.next == null && digit2 != null && digit2.next == null) {
                if (columnSum >= 10) {
                    prev.next = new ListNode(1);
                }
            }
            
            if (digit1 != null) {
                digit1 = digit1.next;
            }
            if (digit2 != null) {
                digit2 = digit2.next;
            }
        }
        
        return head;
    }
}