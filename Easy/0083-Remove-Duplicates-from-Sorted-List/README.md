# Remove Duplicates from Sorted Linked List

## 📌 Problem

Given the `head` of a **sorted singly linked list**, delete all duplicate nodes so that each element appears only once.

Return the linked list after removing duplicates.

### Example 1

```text
Input:  [1,1,2]
Output: [1,2]
```

### Example 2

```text
Input:  [1,1,2,3,3]
Output: [1,2,3]
```

### Constraints

- Number of nodes: `0 <= n <= 300`
- `-100 <= Node.val <= 100`
- The linked list is sorted in ascending order.

---

## 💡 Approach

Because the linked list is **already sorted**, duplicate values will always be next to each other.

For example:

```text
1 → 1 → 2 → 3 → 3
    ↑
 duplicate
```

So, we do not need extra data structures such as a `HashSet`.

We maintain one pointer called `current`.

At every node:

1. Compare `current.val` with `current.next.val`.
2. If both values are equal:
   - The next node is a duplicate.
   - Skip it using:

```java
current.next = current.next.next;
```

3. If the values are different:
   - Move `current` to the next node.
4. Continue until the end of the list.

---

## 🧠 Pattern Used

### Pattern: Two-Pointer / Runner-Style Linked List Traversal

This solution uses a **single traversal pointer** (`current`) while looking at the current node and its next node.

```text
current
   ↓
[1] → [1] → [2] → [3] → [3] → null
        ↑
      next
```

### Why this pattern?

The list is sorted, so duplicates are always adjacent.

Therefore, we only need to compare:

```text
current.val == current.next.val
```

There is no need to search the complete list or store previously seen values.

This makes the solution:

- Simple
- In-place
- Efficient
- O(1) extra space

---

## 🔄 Algorithm

```text
1. Set current = head.
2. While current is not null AND current.next is not null:
   a. If current.val == current.next.val:
      - Remove the duplicate node.
      - Set current.next = current.next.next.
   b. Otherwise:
      - Move current to current.next.
3. Return head.
```

---

## 🚶 Step-by-Step Traversal

Consider:

```text
Input:

1 → 1 → 2 → 3 → 3 → null
```

### Step 1

```text
current
   ↓
1 → 1 → 2 → 3 → 3
```

Compare:

```text
1 == 1
```

They are equal, so remove the second `1`.

```text
1 ─────→ 2 → 3 → 3
↑
current
```

Notice that `current` does **not** move here. This is important because there could be more than two duplicates.

---

### Step 2

Now compare:

```text
1 == 2
```

They are different, so move `current`.

```text
      current
         ↓
1 → 2 → 3 → 3
```

---

### Step 3

Compare:

```text
2 == 3
```

Different, so move `current`.

```text
          current
             ↓
1 → 2 → 3 → 3
```

---

### Step 4

Compare:

```text
3 == 3
```

Equal, so remove the second `3`.

```text
1 → 2 → 3 → null
```

---

### Final Result

```text
[1,2,3]
```

---

## 💻 Java Solution

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
    public ListNode deleteDuplicates(ListNode head) {

        ListNode current = head;

        while (current != null && current.next != null) {

            if (current.val == current.next.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }

        return head;
    }
}
```

---

## 🔍 Code Explanation

### 1. Initialize the pointer

```java
ListNode current = head;
```

`current` starts from the first node.

---

### 2. Check valid nodes

```java
while (current != null && current.next != null)
```

We need both:

- `current != null`
- `current.next != null`

because we are comparing the current node with its next node.

---

### 3. Detect duplicate

```java
if (current.val == current.next.val)
```

Since the list is sorted, if the current value and next value are equal, the next node is definitely a duplicate.

---

### 4. Remove duplicate

```java
current.next = current.next.next;
```

Suppose the list is:

```text
1 → 1 → 2
```

After:

```java
current.next = current.next.next;
```

it becomes:

```text
1 → 2
```

The duplicate node is skipped.

---

### 5. Move when values are different

```java
else {
    current = current.next;
}
```

If the values are different, there is no duplicate at the next position, so move forward.

---

### 6. Return the original head

```java
return head;
```

The first node remains the same because duplicates are removed by changing links.

---

## ⚠️ Important Point

When a duplicate is found, **do not move `current` immediately**.

Correct:

```java
if (current.val == current.next.val) {
    current.next = current.next.next;
}
```

Why?

Consider:

```text
1 → 1 → 1 → 2
```

After removing one duplicate:

```text
1 → 1 → 2
```

We still need to check whether the next `1` is also a duplicate.

Therefore, `current` stays at the same node until the next value becomes different.

---

## ⏱️ Complexity Analysis

Let `n` be the number of nodes.

### Time Complexity

```text
O(n)
```

Each node is processed at most a constant number of times during the traversal.

Therefore:

```text
Time = O(n)
```

### Space Complexity

```text
O(1)
```

Only one pointer (`current`) is used.

No:

- Array
- HashSet
- Extra linked list
- Recursion stack

is required.

Therefore:

```text
Space = O(1)
```

---

## 📊 Complexity Summary

| Complexity | Value |
|---|---|
| Time | **O(n)** |
| Extra Space | **O(1)** |
| Pattern | **Linked List Traversal / Two-Pointer-style comparison** |
| In-place | **Yes** |
| Extra Data Structure | **No** |

---

## ✅ Why This Is Optimal

Because the input list is sorted, duplicates are adjacent.

For example:

```text
1 → 1 → 2 → 2 → 3 → 3
```

We can remove duplicates simply by changing links.

There is no need for:

```java
HashSet<Integer>
```

which would require additional `O(n)` space.

The given solution achieves:

```text
O(n) Time
O(1) Extra Space
```

which is optimal for this problem because we need to inspect the nodes to determine which duplicates exist.

---

## 🎯 Key Interview Takeaways

1. **Sorted linked list** → duplicates are adjacent.
2. Compare `current` with `current.next`.
3. Duplicate found → skip the duplicate node.
4. Do not move `current` after deleting a duplicate.
5. Different value → move `current`.
6. No extra data structure is required.
7. Time complexity: **O(n)**.
8. Space complexity: **O(1)**.
9. The list is modified **in-place**.
