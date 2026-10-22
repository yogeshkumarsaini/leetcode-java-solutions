# Reverse Linked List

## Problem

Given the `head` of a singly linked list, reverse the list and return the reversed list.

### Example 1

```text
Input:  head = [1,2,3,4,5]
Output: [5,4,3,2,1]
```

### Example 2

```text
Input:  head = [1,2]
Output: [2,1]
```

### Example 3

```text
Input:  head = []
Output: []
```

---

## Java Solution — Iterative

```java
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

        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {

            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
}
```

---

## Approach

We reverse the linked list by changing the direction of each node's `next` pointer.

We use three pointers:

- `prev` → stores the previous node.
- `curr` → stores the current node being processed.
- `next` → temporarily stores the next node so that we do not lose the remaining list.

Initially:

```text
prev = null
curr = head
```

For every node:

1. Save the next node.
2. Reverse the current node's pointer.
3. Move `prev` to the current node.
4. Move `curr` to the saved next node.

At the end, `prev` becomes the new head of the reversed list.

---

## Step-by-Step Traversal

For:

```text
1 -> 2 -> 3 -> 4 -> 5 -> null
```

### Step 1

```text
prev = null
curr = 1
next = 2

Reverse:
1 -> null
```

Now:

```text
prev = 1
curr = 2
```

### Step 2

```text
next = 3

Reverse:
2 -> 1 -> null
```

Now:

```text
prev = 2
curr = 3
```

### Step 3

```text
next = 4

Reverse:
3 -> 2 -> 1 -> null
```

Now:

```text
prev = 3
curr = 4
```

### Step 4

```text
next = 5

Reverse:
4 -> 3 -> 2 -> 1 -> null
```

Now:

```text
prev = 4
curr = 5
```

### Step 5

```text
next = null

Reverse:
5 -> 4 -> 3 -> 2 -> 1 -> null
```

Now:

```text
prev = 5
curr = null
```

The loop stops because `curr == null`.

Return:

```text
prev
```

Final result:

```text
5 -> 4 -> 3 -> 2 -> 1 -> null
```

---

## Why Do We Store `next` First?

This line is very important:

```java
ListNode next = curr.next;
```

If we directly change:

```java
curr.next = prev;
```

we would lose the reference to the remaining part of the list.

For example:

```text
1 -> 2 -> 3 -> 4
```

When processing `1`, after:

```java
curr.next = prev;
```

the list becomes:

```text
1 -> null
```

Without storing `2` first, we would no longer know where the remaining list starts.

Therefore:

```java
ListNode next = curr.next;
```

must happen before changing `curr.next`.

---

## Algorithm

1. Initialize `prev = null`.
2. Initialize `curr = head`.
3. Repeat while `curr != null`:
   - Store `curr.next` in `next`.
   - Point `curr.next` to `prev`.
   - Move `prev` to `curr`.
   - Move `curr` to `next`.
4. Return `prev`.

---

## Pattern Used

### Pattern: Three-Pointer Linked List Traversal

This solution uses a **three-pointer pattern**:

```text
prev
curr
next
```

This pattern is commonly used when modifying links in a singly linked list while traversing it.

### Why this pattern?

Because while reversing a linked list, we need to:

- Remember the previous node.
- Modify the current node's pointer.
- Remember the remaining list.

The three pointers handle all three requirements safely.

---

## Dry Run

Input:

```text
1 -> 2 -> 3 -> null
```

| Iteration | prev | curr | next | Change |
|---|---|---|---|---|
| Start | null | 1 | - | - |
| 1 | 1 | 2 | 2 | `1 -> null` |
| 2 | 2 | 3 | 3 | `2 -> 1` |
| 3 | 3 | null | null | `3 -> 2` |

Final:

```text
3 -> 2 -> 1 -> null
```

---

## Complexity Analysis

Let `n` be the number of nodes.

### Time Complexity

```text
O(n)
```

Each node is visited exactly once.

### Space Complexity

```text
O(1)
```

Only a few pointer variables are used. No extra array, list, or data structure is created.

This is an **in-place reversal**.

---

# Recursive Approach

The linked list can also be reversed recursively.

```java
class Solution {
    public ListNode reverseList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverseList(head.next);

        head.next.next = head;
        head.next = null;

        return newHead;
    }
}
```

## Recursive Approach Explanation

For:

```text
1 -> 2 -> 3 -> null
```

The recursion first reaches the last node:

```text
3
```

Then while returning from recursion:

```text
3 -> 2
3 -> 2 -> 1
```

The last node becomes the new head.

### Base Case

```java
if (head == null || head.next == null) {
    return head;
}
```

If the list is empty or contains only one node, it is already reversed.

### Reverse the Remaining List

```java
ListNode newHead = reverseList(head.next);
```

This recursively reverses everything after the current node.

### Change the Link

```java
head.next.next = head;
```

This makes the next node point back to the current node.

### Remove the Old Forward Link

```java
head.next = null;
```

This prevents a cycle.

---

## Recursive Complexity

### Time Complexity

```text
O(n)
```

Every node is processed once.

### Space Complexity

```text
O(n)
```

Because recursion uses the call stack. There can be up to `n` recursive calls.

---

## Iterative vs Recursive

| Approach | Time | Extra Space | Recommended |
|---|---:|---:|---|
| Iterative | O(n) | O(1) | Yes |
| Recursive | O(n) | O(n) | Good for learning |

The **iterative approach is generally preferred** because it reverses the list in-place with constant extra space.

---

## Key Interview Point

Remember this sequence:

```java
ListNode next = curr.next;
curr.next = prev;
prev = curr;
curr = next;
```

A simple way to remember it:

```text
SAVE → REVERSE → MOVE PREV → MOVE CURR
```

This is the core pattern for iterative linked-list reversal.

---

## LeetCode Pattern

**Pattern:** Linked List / Pointer Manipulation

**Technique:** Three-Pointer Traversal

**Important Concept:** In-place pointer reversal

---

## Tags

`Linked List` `Pointers` `Three Pointer` `In-Place` `Recursion` `Traversal` `LeetCode`

---

## Conclusion

The iterative solution is the best choice when we want:

- `O(n)` time
- `O(1)` extra space
- In-place reversal
- Simple and efficient code

The recursive solution is useful for understanding recursion and linked-list pointer manipulation, but it requires `O(n)` call-stack space.
