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
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

// O(n) time and space.
class Solution {
    private TreeNode dfsHelper(LinkedList<Integer> sortedList, int start, int end) {
        if (start > end) {
            return null;
        }
        
        int mid = (start + end)/2;
        TreeNode root = new TreeNode(sortedList.get(mid));
        
        if (start == end) {
            return root;
        }
        
        root.left = dfsHelper(sortedList, start, mid - 1);
        root.right = dfsHelper(sortedList, mid + 1, end);
        
        return root;
    }
        
    public TreeNode sortedListToBST(ListNode head) {
        LinkedList<Integer> sortedList = new LinkedList<Integer>();
        ListNode curr = head;
        while (curr != null) {
            sortedList.add(curr.val);
            curr = curr.next;
        }
        
        return dfsHelper(sortedList, 0, sortedList.size() - 1);
    }
}