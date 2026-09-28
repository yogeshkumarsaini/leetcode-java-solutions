# Linked List Cycle

## Problem

Given the `head` of a singly linked list, determine whether the linked list contains a cycle.

A cycle exists when a node can be reached again by continuously following the `next` pointer.

Return:
- `true` if the linked list contains a cycle.
- `false` if the linked list does not contain a cycle.

---

## Example 1

```text
Input:  head = [3,2,0,-4], pos = 1
Output: true
```

The tail node points back to the node at index `1`, so a cycle exists.

## Example 2

```text
Input:  head = [1,2], pos = 0
Output: true
```

The tail points back to the first node, creating a cycle.

## Example 3

```text
Input:  head = [1], pos = -1
Output: false
```

There is no cycle.

---

# Approach

We use **Floyd's Cycle Detection Algorithm**, also called the:

> **Tortoise and Hare Algorithm**

We use two pointers:

- `slow` moves **one node at a time**.
- `fast` moves **two nodes at a time**.

### Main Idea

If there is **no cycle**:

```text
slow -> -> -> null
fast -> -> -> null
```

Eventually `fast` will reach `null`.

If there **is a cycle**, both pointers enter the cycle.

Since `fast` moves faster than `slow`, `fast` will eventually catch `slow`.

Therefore:

```text
slow == fast
```

means a cycle exists.

---

# Why Does This Work?

Imagine two people running around a circular track.

- One person runs slowly.
- The other person runs faster.

If they are running on a circular track, the faster person will eventually catch the slower person.

A linked-list cycle works in the same way.

Once both pointers enter the cycle:

```text
slow -> A -> B -> C -> D
              ↑         |
              |---------|
```

`fast` moves faster and eventually reaches the same node as `slow`.

So:

```java
if (slow == fast) {
    return true;
}
```

---

# Algorithm

1. Create two pointers:
   - `slow = head`
   - `fast = head`

2. Continue while:
   - `fast != null`
   - `fast.next != null`

3. Move `slow` one step:

```java
slow = slow.next;
```

4. Move `fast` two steps:

```java
fast = fast.next.next;
```

5. Check whether both pointers are pointing to the same node:

```java
if (slow == fast) {
    return true;
}
```

6. If `fast` reaches `null`, there is no cycle.

7. Return `false`.

---

# Step-by-Step Traversal

Consider:

```text
3 -> 2 -> 0 -> -4
     ^         |
     |---------|
```

Here the last node `-4` points back to `2`.

Initial:

```text
slow = 3
fast = 3
```

### Step 1

Move:

```text
slow = 2
fast = 0
```

### Step 2

Move:

```text
slow = 0
fast = 2
```

### Step 3

Move:

```text
slow = -4
fast = -4
```

Now:

```text
slow == fast
```

Therefore:

```text
return true
```

A cycle exists.

---

# Code

```java
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }

        return false;
    }
}
```

---

# Code Explanation

### 1. Create two pointers

```java
ListNode slow = head;
ListNode fast = head;
```

Both pointers initially start from the head.

---

### 2. Check whether fast can move

```java
while (fast != null && fast.next != null)
```

We need to make sure:

```text
fast
```

and

```text
fast.next
```

are not `null`.

This prevents accessing a node that does not exist.

---

### 3. Move slow by one node

```java
slow = slow.next;
```

Example:

```text
3 -> 2 -> 0
     ↑
    slow
```

`slow` moves only one step.

---

### 4. Move fast by two nodes

```java
fast = fast.next.next;
```

Example:

```text
3 -> 2 -> 0 -> -4
          ↑
         fast
```

`fast` moves two steps at a time.

---

### 5. Check whether they meet

```java
if (slow == fast) {
    return true;
}
```

Important:

We compare the **node references**, not their values.

For example, two different nodes can have:

```text
val = 5
```

They are still different nodes.

We need:

```text
slow == fast
```

not:

```text
slow.val == fast.val
```

---

### 6. No cycle

If `fast` reaches `null`, the linked list ends.

Therefore:

```java
return false;
```

---

# Pattern Used

## Two Pointers Pattern

More specifically:

> **Floyd's Cycle Detection / Fast & Slow Pointer Pattern**

We use two pointers moving at different speeds.

```text
slow -> 1 step
fast -> 2 steps
```

---

# Why Use This Pattern?

A simple solution could use a `HashSet` to store every visited node.

For example:

```java
Set<ListNode> visited = new HashSet<>();
```

Then, if we see the same node again, we know there is a cycle.

But this requires **O(n) extra memory**.

The problem's follow-up asks for:

```text
O(1) extra space
```

Floyd's algorithm solves the problem using only two pointers:

```text
slow
fast
```

Therefore, no extra data structure is required.

---

# Complexity

Let `n` be the number of nodes.

## Time Complexity

```text
O(n)
```

Each pointer moves through the linked list, and in the cycle case they eventually meet.

## Space Complexity

```text
O(1)
```

Only two pointers are used:

```java
slow
fast
```

No HashSet, ArrayList, or other extra data structure is required.

---

# Complexity Summary

| Complexity | Result |
|---|---|
| Time | **O(n)** |
| Space | **O(1)** |

---

# Important Interview Point

### Why not use `slow.val == fast.val`?

Because values can be duplicated.

Example:

```text
1 -> 2 -> 3 -> 2 -> null
```

Two nodes may have the same value but they are different nodes.

We need to check whether both pointers point to the **same node object**:

```java
slow == fast
```

---

# Quick Revision

Remember this:

```text
slow = 1 step
fast = 2 steps
```

If:

```text
fast == null
```

→ No cycle

If:

```text
slow == fast
```

→ Cycle exists

### Pattern

```text
Fast & Slow Pointer
        ↓
Floyd's Cycle Detection
        ↓
O(n) Time
O(1) Space
```