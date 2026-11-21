# 234. Palindrome Linked List

## Problem Statement

Given the head of a singly linked list, return `true` if the linked list is a palindrome, otherwise return `false`.

A palindrome is a sequence that reads the same forward and backward.

### Example 1

**Input:**
```text
head = [1,2,2,1]
```

**Output:**
```text
true
```

### Example 2

**Input:**
```text
head = [1,2]
```

**Output:**
```text
false
```

### Constraints

- The number of nodes is in the range `[1, 10^5]`.
- `0 <= Node.val <= 9`

---

## Java Solution

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) {
 *         this.val = val;
 *         this.next = next;
 *     }
 * }
 */

class Solution {
    public boolean isPalindrome(ListNode head) {

        // Step 1: Find the middle of the linked list
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse the second half
        ListNode prev = null;
        ListNode curr = slow;

        while (curr != null) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }

        // Step 3: Compare both halves
        ListNode first = head;
        ListNode second = prev;

        while (second != null) {
            if (first.val != second.val) {
                return false;
            }

            first = first.next;
            second = second.next;
        }

        return true;
    }
}
```

---

## Approach

We solve this problem using three steps without using any extra array or collection.

### Step 1: Find the Middle of the Linked List

We use the **Slow and Fast Pointer** technique.

- `slow` moves one node at a time.
- `fast` moves two nodes at a time.
- When `fast` reaches the end, `slow` reaches the middle.

### Step 2: Reverse the Second Half

We reverse the linked list starting from `slow`.

We use three pointers:

- `prev` – stores the previous node.
- `curr` – stores the current node.
- `nextNode` – temporarily stores the next node so we don't lose the remaining list.

### Step 3: Compare Both Halves

After reversing the second half:

- `first` starts from the head.
- `second` starts from the reversed second half.
- Compare each node's value.
- If any values are different, return `false`.
- If all values match, return `true`.

---

## Algorithm

1. Initialize `slow` and `fast` pointers at the head.
2. Move `slow` one step and `fast` two steps until `fast` reaches the end.
3. Start reversing the second half using `prev`, `curr`, and `nextNode`.
4. Set `first` to the head and `second` to the reversed half.
5. Compare the values of both pointers.
6. If any values differ, return `false`.
7. If all compared values match, return `true`.

---

## Step-by-Step Traversal

### Example 1

**Input:**
```text
head = [1,2,2,1]
```

### Step 1: Find the Middle

Initially:

```text
slow = 1
fast = 1
```

| Iteration | Slow | Fast |
|---|---|---|
| Initial | 1 | 1 |
| 1 | 2 | 2 |
| 2 | 2 (3rd node) | null (end reached) |

The `slow` pointer reaches the 3rd node.

### Step 2: Reverse the Second Half

Original linked list:

```text
1 → 2 → 2 → 1 → null
        ↑
       slow
```

Reverse the portion starting from `slow`:

```text
Original:  1 → 2 → 2 → 1 → null

Reversed second half: 1 → 2 → 1 → null
```

In this implementation, the list is reversed starting from the node at `slow`, so the pointers become:

```text
First:  1 → 2 → null
Second: 1 → 2 → null
```

### Step 3: Compare Both Halves

| First Pointer | Second Pointer | Comparison |
|---|---|---|
| 1 | 1 | Match |
| 2 | 2 | Match |

All values match.

**Output:**
```text
true
```

---

### Example 2

**Input:**
```text
head = [1,2]
```

### Step 1: Find the Middle

```text
slow = 2
fast = null
```

### Step 2: Reverse the Second Half

```text
Original: 1 → 2 → null

Reversed from slow: 2 → null
```

### Step 3: Compare

| First Pointer | Second Pointer | Comparison |
|---|---|---|
| 1 | 2 | Mismatch |

The values are different.

**Output:**
```text
false
```

---

## Visual Representation

### Before Reversing

```text
1 → 2 → 2 → 1 → null
        ↑
       slow
```

### After Reversing the Second Half

```text
First half:   1 → 2

Second half:  1 → 2
```

### Comparison

```text
1 == 1  ✓
2 == 2  ✓
```

Result: `true`

---

## Pattern Used

### 1. Fast and Slow Pointer Pattern

**Why did we use it?**

We need to find the middle of a linked list without knowing its length beforehand.

- Slow pointer moves one step.
- Fast pointer moves two steps.
- When fast reaches the end, slow is at the middle.

This allows us to find the middle in `O(n)` time and `O(1)` extra space.

### 2. In-place Linked List Reversal

**Why did we use it?**

A singly linked list cannot be traversed backward. Instead of storing all values in an array or stack, we reverse the second half of the linked list.

This makes backward comparison possible while using constant extra space.

### 3. Two Pointer Comparison

**Why did we use it?**

After reversing the second half, we compare both halves node by node.

If every corresponding value matches, the list is a palindrome.

---

## Complexity Analysis

### Time Complexity: `O(n)`

- Finding the middle: `O(n)`
- Reversing the second half: `O(n)`
- Comparing both halves: `O(n)`

These operations are sequential, so the overall time complexity is:

```text
O(n) + O(n) + O(n) = O(n)
```

### Space Complexity: `O(1)`

We use only a few pointer variables:

- `slow`
- `fast`
- `prev`
- `curr`
- `nextNode`
- `first`
- `second`

No extra array, stack, or collection is used.

Therefore, the extra space complexity is:

```text
O(1)
```

---

## Key Takeaways

- Use the **Fast and Slow Pointer** pattern to find the middle.
- Reverse the second half of the linked list in place.
- Compare both halves using two pointers.
- Avoid extra arrays or collections.
- Achieve `O(n)` time complexity and `O(1)` auxiliary space.

---

## LeetCode

- **Problem:** 234. Palindrome Linked List
- **Difficulty:** Easy
- **Topic:** Linked List
- **Patterns:** Fast & Slow Pointers, In-place Reversal, Two Pointers
- **Time Complexity:** `O(n)`
- **Space Complexity:** `O(1)`
