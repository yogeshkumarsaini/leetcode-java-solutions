# Merge Two Sorted Lists

## Problem Statement

You are given the heads of two sorted linked lists, `list1` and `list2`.

Merge both linked lists into a single **sorted linked list**. The merged list should be created by directly connecting the existing nodes from both lists.

Return the head of the merged linked list.

---

## Examples

### Example 1

```text
Input:
list1 = [1,2,4]
list2 = [1,3,4]

Output:
[1,1,2,3,4,4]
```

### Example 2

```text
Input:
list1 = []
list2 = []

Output:
[]
```

### Example 3

```text
Input:
list1 = []
list2 = [0]

Output:
[0]
```

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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        while (list1 != null && list2 != null) {

            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }

            current = current.next;
        }

        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }

        return dummy.next;
    }
}
```

---

# Approach

We use the **Two Pointers + Dummy Node pattern**.

Since both linked lists are already sorted, we compare the current nodes of both lists and select the smaller value.

### Main Idea

* Create a `dummy` node to simplify the construction of the merged list.
* Use `current` to track the last node of the merged list.
* Compare `list1.val` and `list2.val`.
* Attach the smaller node to `current.next`.
* Move the pointer of the selected list forward.
* Move `current` forward.
* Continue until one list becomes `null`.
* Attach the remaining nodes from the other list.
* Return `dummy.next`.

---

# Pattern Used

## Two Pointers Pattern

This solution uses the **Two Pointers pattern**.

We maintain two pointers:

```text
list1 → Current node of first linked list
list2 → Current node of second linked list
```

At every step, we compare:

```text
list1.val
```

and

```text
list2.val
```

The smaller value is added to the merged linked list.

### Why Use Two Pointers?

Both lists are already sorted.

Therefore, we don't need to:

* Store all values in an array.
* Sort the values again.
* Create unnecessary extra data structures.

We can directly compare both current nodes and merge them in sorted order.

This makes the solution efficient.

---

# Dummy Node Pattern

We also use a **Dummy Node**.

```java
ListNode dummy = new ListNode(-1);
ListNode current = dummy;
```

The dummy node acts as a temporary starting point.

### Why Do We Use a Dummy Node?

Without a dummy node, we would need special handling for the first node of the merged list.

For example:

```text
Which node should become the head?
list1.val or list2.val?
```

Using a dummy node removes this special case.

At the end:

```java
return dummy.next;
```

The actual merged linked list starts from `dummy.next`.

---

# Algorithm

### Step 1: Create a Dummy Node

```java
ListNode dummy = new ListNode(-1);
```

This helps us build the result list easily.

---

### Step 2: Create a Current Pointer

```java
ListNode current = dummy;
```

`current` always points to the last node in the merged list.

---

### Step 3: Traverse Both Lists

Continue while both lists contain nodes:

```java
while (list1 != null && list2 != null)
```

---

### Step 4: Compare Both Values

If `list1` has a smaller or equal value:

```java
if (list1.val <= list2.val)
```

Attach `list1`:

```java
current.next = list1;
list1 = list1.next;
```

Otherwise, attach `list2`:

```java
current.next = list2;
list2 = list2.next;
```

---

### Step 5: Move Current Forward

After attaching a node:

```java
current = current.next;
```

---

### Step 6: Attach Remaining Nodes

When one list becomes empty, the remaining nodes in the other list are already sorted.

So we directly attach them:

```java
if (list1 != null) {
    current.next = list1;
} else {
    current.next = list2;
}
```

---

### Step 7: Return the Result

```java
return dummy.next;
```

We return `dummy.next` because `dummy` is only a helper node.

---

# Step-by-Step Traversal

Let's take:

```text
list1 = [1,2,4]
list2 = [1,3,4]
```

Initially:

```text
list1 → 1 → 2 → 4
list2 → 1 → 3 → 4

dummy → -1
current → dummy
```

---

## Traversal 1

Compare:

```text
list1.val = 1
list2.val = 1
```

Since:

```text
1 <= 1
```

Select the node from `list1`.

```text
Merged List:

dummy → 1

list1 → 2 → 4
list2 → 1 → 3 → 4
current → 1
```

---

## Traversal 2

Compare:

```text
list1.val = 2
list2.val = 1
```

Since `1` is smaller, select the node from `list2`.

```text
Merged List:

dummy → 1 → 1

list1 → 2 → 4
list2 → 3 → 4
current → 1
```

---

## Traversal 3

Compare:

```text
list1.val = 2
list2.val = 3
```

Since `2` is smaller, select the node from `list1`.

```text
Merged List:

dummy → 1 → 1 → 2

list1 → 4
list2 → 3 → 4
```

---

## Traversal 4

Compare:

```text
list1.val = 4
list2.val = 3
```

Since `3` is smaller, select the node from `list2`.

```text
Merged List:

dummy → 1 → 1 → 2 → 3

list1 → 4
list2 → 4
```

---

## Traversal 5

Compare:

```text
list1.val = 4
list2.val = 4
```

Since:

```text
4 <= 4
```

Select the node from `list1`.

```text
Merged List:

dummy → 1 → 1 → 2 → 3 → 4

list1 → null
list2 → 4
```

---

## Final Step

Now `list1` is `null`.

Attach the remaining nodes of `list2`.

```text
dummy → 1 → 1 → 2 → 3 → 4 → 4
```

Return:

```java
dummy.next
```

### Final Output

```text
[1,1,2,3,4,4]
```

---

# Visual Representation

```text
List 1:
1 → 2 → 4

List 2:
1 → 3 → 4
```

### Merging Process

```text
Compare 1 and 1  → Take 1
Compare 2 and 1  → Take 1
Compare 2 and 3  → Take 2
Compare 4 and 3  → Take 3
Compare 4 and 4  → Take 4
List 1 ends      → Attach remaining 4
```

### Result

```text
1 → 1 → 2 → 3 → 4 → 4
```

---

# Dry Run Table

| Step | list1 | list2 | Selected Node | Merged List     |
| ---- | ----- | ----- | ------------- | --------------- |
| 1    | 1     | 1     | 1 from list1  | `[1]`           |
| 2    | 2     | 1     | 1 from list2  | `[1,1]`         |
| 3    | 2     | 3     | 2 from list1  | `[1,1,2]`       |
| 4    | 4     | 3     | 3 from list2  | `[1,1,2,3]`     |
| 5    | 4     | 4     | 4 from list1  | `[1,1,2,3,4]`   |
| 6    | null  | 4     | Remaining 4   | `[1,1,2,3,4,4]` |

---

# Time Complexity

Let:

```text
m = Number of nodes in list1
n = Number of nodes in list2
```

Each node is visited exactly once.

Therefore:

```text
Time Complexity: O(m + n)
```

### Why?

We traverse:

* Every node of `list1` at most once.
* Every node of `list2` at most once.

So the total work is:

```text
O(m + n)
```

---

# Space Complexity

```text
Space Complexity: O(1)
```

### Why?

We only use two extra pointers:

```text
dummy
current
```

No array, list, stack, or other data structure is created.

Also, the existing nodes are reused instead of creating a new node for every value.

Therefore:

```text
Auxiliary Space: O(1)
```

> Note: The returned merged list contains the original nodes, so no additional space proportional to the input size is used.

---

# Complexity Summary

| Complexity                   | Value        |
| ---------------------------- | ------------ |
| Time Complexity              | `O(m + n)`   |
| Auxiliary Space Complexity   | `O(1)`       |
| Pattern                      | Two Pointers |
| Helper Technique             | Dummy Node   |
| Traversal                    | Iterative    |
| Sorting Required?            | No           |
| New Nodes Created for Merge? | No           |

---

# Why This Is an Optimal Approach?

This approach is optimal because:

* Both lists are already sorted.
* Every node is processed only once.
* No additional array or collection is required.
* No sorting is required.
* Existing nodes are directly reused.
* It handles empty lists automatically.
* The dummy node avoids special handling for the head node.

---

# Edge Cases

### Both Lists Empty

```text
list1 = []
list2 = []

Output:
[]
```

---

### First List Empty

```text
list1 = []
list2 = [0]

Output:
[0]
```

---

### Second List Empty

```text
list1 = [1,2,3]
list2 = []

Output:
[1,2,3]
```

---

### Equal Values

```text
list1 = [1,2,4]
list2 = [1,2,4]

Output:
[1,1,2,2,4,4]
```

---

### Negative Values

```text
list1 = [-5,-2,4]
list2 = [-3,0,10]

Output:
[-5,-3,-2,0,4,10]
```

---

# Key Takeaway

When you need to merge two already sorted linked lists:

```text
Use Two Pointers
        +
Use a Dummy Node
```

### Final Pattern

```text
Compare both nodes
        ↓
Attach smaller node
        ↓
Move selected list pointer
        ↓
Move current pointer
        ↓
Repeat until one list ends
        ↓
Attach remaining nodes
```

This gives an efficient solution with:

```text
Time:  O(m + n)
Space: O(1)
```
