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
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        int[] ans = {-1, -1};
        ListNode prev = head;
        ListNode curr = head.next;
        int index = 1; // curr is at position 1

        int firstCritical = -1;
        int prevCritical = -1;

        while (curr.next != null) {
            // Check if curr is a critical point
            boolean isLocalMin = curr.val < prev.val && curr.val < curr.next.val;
            boolean isLocalMax = curr.val > prev.val && curr.val > curr.next.val;

            if (isLocalMin || isLocalMax) {
                if (firstCritical == -1) {
                    firstCritical = index;
                } else {
                    // Update min distance
                    int dist = index - prevCritical;
                    if (ans[0] == -1 || dist < ans[0]) {
                        ans[0] = dist;
                    }
                    // Update max distance
                    ans[1] = index - firstCritical;
                }
                prevCritical = index;
            }

            prev = curr;
            curr = curr.next;
            index++;
        }

        return ans;
    

    }
}