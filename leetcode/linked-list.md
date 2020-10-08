# Linked-list

+ [Reverse Linked List](#reverse-linked-list)

## Reverse Linked List

https://leetcode.com/problems/reverse-linked-list/

```java
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode nextNode = head.next;
        ListNode newHead = reverseList(nextNode);
        nextNode.next = head;
        head.next = null;
        return newHead;
    }
}
```