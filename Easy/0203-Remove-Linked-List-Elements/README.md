# Remove Linked List Elements

## Problem

Given the `head` of a singly linked list and an integer `val`, remove all nodes whose value is equal to `val`.

Return the new head of the linked list.

### Example

```text
Input:  head = [1,2,6,3,4,5,6], val = 6
Output: [1,2,3,4,5]
```

---

## Java Solution

```java
class Solution {
    public ListNode removeElements(ListNode head, int val) {

        while (head != null && head.val == val) {
            head = head.next;
        }

        ListNode current = head;

        while (current != null && current.next != null) {

            if (current.next.val == val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }

        return head;
    }
}
```

## Approach

We use **iterative linked-list traversal**.

There are two cases:

1. Remove matching nodes from the **beginning** of the list.
2. Remove matching nodes from the **middle or end**.

First, we move `head` forward while `head.val == val`.

Then we use `current` to traverse the remaining list.

If:

```java
current.next.val == val
```

we skip that node:

```java
current.next = current.next.next;
```

Otherwise, we move `current` forward.

### Why don't we move `current` after deletion?

For:

```text
1 -> 6 -> 6 -> 3
```

After deleting the first `6`:

```text
1 -> 6 -> 3
```

`current` is still at `1`, so it can check the next `6` again.

This correctly handles consecutive duplicate values.

---

## Algorithm

1. Check whether `head` is `null` or contains `val`.
2. While `head.val == val`, move `head` to `head.next`.
3. Set `current = head`.
4. Traverse the remaining list.
5. If `current.next.val == val`:
   ```java
   current.next = current.next.next;
   ```
6. Otherwise move:
   ```java
   current = current.next;
   ```
7. Return `head`.

---

## Step-by-Step Traversal

For:

```text
head = [1,2,6,3,4,5,6]
val = 6
```

Initial:

```text
1 -> 2 -> 6 -> 3 -> 4 -> 5 -> 6
^
current
```

`current.next = 2`, so move forward.

```text
1 -> 2 -> 6 -> 3 -> 4 -> 5 -> 6
     ^
   current
```

Now `current.next = 6`.

Remove it:

```java
current.next = current.next.next;
```

List becomes:

```text
1 -> 2 -> 3 -> 4 -> 5 -> 6
     ^
   current
```

Continue traversal.

At the final `6`, remove it:

```text
1 -> 2 -> 3 -> 4 -> 5
```

Return `head`.

---

## Pattern Used

### Pattern: Pointer Traversal / Two-Pointer Linked List Technique

The solution uses:

- `head` → points to the first valid node.
- `current` → traverses the linked list.

To delete a node from a singly linked list, we need access to the node **before** the node being deleted.

For example:

```text
current -> node_to_delete -> next
```

We bypass the unwanted node using:

```java
current.next = current.next.next;
```

Result:

```text
current ----------------> next
```

### Why use this pattern?

Because it allows us to:

- Traverse the list in-place.
- Delete nodes without creating another list.
- Handle consecutive matching nodes.
- Handle deletion from the beginning separately.
- Use constant extra space.

---

## Edge Cases

### Empty List

```text
head = []
val = 1
```

Result:

```text
[]
```

### All Nodes Match

```text
head = [7,7,7,7]
val = 7
```

Result:

```text
[]
```

### Matching Node at Beginning

```text
head = [6,1,2,3]
val = 6
```

Result:

```text
[1,2,3]
```

### Consecutive Matching Nodes

```text
head = [1,6,6,6,2]
val = 6
```

Result:

```text
[1,2]
```

### No Matching Node

```text
head = [1,2,3,4]
val = 6
```

Result:

```text
[1,2,3,4]
```

---

## Complexity Analysis

Let `n` be the number of nodes.

### Time Complexity

```text
O(n)
```

Every node is processed at most a constant number of times.

### Space Complexity

```text
O(1)
```

Only pointer variables such as `head` and `current` are used.

No extra array, list, recursion, or new linked-list structure is created.

---

## Why This Solution Is Efficient

| Complexity | Value |
|---|---|
| Time | `O(n)` |
| Space | `O(1)` |

This is an efficient solution because we traverse the linked list once and modify the existing list directly.

---

## Important Code Line

```java
current.next = current.next.next;
```

This is the key operation.

Before:

```text
current -> node_to_remove -> next
```

After:

```text
current -----------------> next
```

The unwanted node is skipped.

---

## GitHub Topics

```text
leetcode
linked-list
java
data-structures
two-pointers
linked-list-traversal
leetcode-solutions
```

## Pattern Summary

```text
Problem Type : Linked List
Pattern      : Pointer Traversal / Two-Pointer Technique
Traversal    : Iterative
Time         : O(n)
Space        : O(1)
```