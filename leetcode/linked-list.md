# Linked-list

+ [Reverse Linked List](#reverse-linked-list)
+ [Middle of the Linked List](#middle-of-the-linked-list)

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

## Middle of the Linked List

https://leetcode.com/problems/middle-of-the-linked-list/

```java
class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
```
