# 2. Add Two Numbers --- LeetCode (Java)

## Problem Statement

You are given two non-empty linked lists representing two non-negative
integers. The digits are stored in **reverse order**, and each node
contains a single digit.

Add the two numbers and return the result as a linked list. The result
must also store its digits in reverse order.

### Examples

**Example 1**

``` text
Input:
l1 = [2,4,3]
l2 = [5,6,4]

Output:
[7,0,8]

Explanation:
The lists represent 342 and 465.
342 + 465 = 807, so the answer is [7,0,8].
```

**Example 2**

``` text
Input:
l1 = [0]
l2 = [0]

Output:
[0]
```

**Example 3**

``` text
Input:
l1 = [9,9,9,9,9,9,9]
l2 = [9,9,9,9]

Output:
[8,9,9,9,0,0,0,1]
```

## Java Solution

``` java
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;

            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            carry = sum / 10;
            int digit = sum % 10;

            current.next = new ListNode(digit);
            current = current.next;
        }

        return dummy.next;
    }
}
```

## Approach: Iterative Addition with Carry

Because the digits are stored in reverse order, the head node contains
the units digit. This means we can add the lists from head to tail just
like normal column-by-column addition.

We use three important variables:

-   `carry`: stores the carry from the previous digit addition.
-   `sum`: stores the current digits plus the carry.
-   `current`: points to the last node in the result list, so we can
    append the next digit.

A **dummy node** is used before the result list. It makes insertion easy
because we do not need a separate special case for creating the first
result node.

For every position:

1.  Start `sum` with the previous `carry`.
2.  Add `l1.val` if `l1` still has a node.
3.  Add `l2.val` if `l2` still has a node.
4.  Calculate the new carry using `sum / 10`.
5.  Calculate the result digit using `sum % 10`.
6.  Append that digit to the result list.
7.  Move the available input pointers forward.

The loop continues while either list has nodes **or a carry remains**.
The carry condition is important because the final addition may create
one extra digit.

Finally, return `dummy.next`, not `dummy`, because the dummy node is
only a helper and is not part of the answer.

## Algorithm

1.  Create a dummy node with value `0`.
2.  Set `current` to the dummy node and initialize `carry = 0`.
3.  Repeat while `l1` is not null, `l2` is not null, or `carry` is not
    zero:
    -   Initialize `sum = carry`.
    -   If `l1` exists, add its value to `sum` and move `l1` to the next
        node.
    -   If `l2` exists, add its value to `sum` and move `l2` to the next
        node.
    -   Set `carry = sum / 10`.
    -   Set `digit = sum % 10`.
    -   Create a new node containing `digit` and attach it after
        `current`.
    -   Move `current` to the newly created node.
4.  Return `dummy.next`.

## Step-by-Step Traversal

Consider Example 1:

``` text
l1 = 2 -> 4 -> 3
l2 = 5 -> 6 -> 4
```

The lists represent `342` and `465`, because digits are stored in
reverse order.

  ------------------------------------------------------------------------------------
        Step   l1 digit   l2 digit   Carry in        Sum   Result digit      Carry out
                                                           (`sum % 10`)   (`sum / 10`)
  ---------- ---------- ---------- ---------- ---------- -------------- --------------
           1          2          5          0          7              7              0

           2          4          6          0         10              0              1

           3          3          4          1          8              8              0
  ------------------------------------------------------------------------------------

### Traversal visualization

``` text
Initial:
l1: 2 -> 4 -> 3
l2: 5 -> 6 -> 4
carry = 0

Step 1:
2 + 5 + 0 = 7
digit = 7, carry = 0
result: 7

Step 2:
4 + 6 + 0 = 10
digit = 0, carry = 1
result: 7 -> 0

Step 3:
3 + 4 + 1 = 8
digit = 8, carry = 0
result: 7 -> 0 -> 8

Both lists are finished and carry is 0.
Final result: 7 -> 0 -> 8
```

So the returned list is `[7,0,8]`, which represents `807`.

### Why do we use `sum / 10` and `sum % 10`?

For each digit position, the ones digit becomes part of the answer and
the tens part is carried to the next position.

For example, when `sum = 10`:

``` text
carry = 10 / 10 = 1
digit = 10 % 10 = 0
```

So we write `0` in the current position and carry `1` to the next
position.

## Pattern Used

**Pattern: Linked List Traversal + Two Pointers + Carry (Elementary
Addition)**

-   **Linked List Traversal:** We move through both linked lists using
    their `next` references.
-   **Two Pointers:** `l1` and `l2` independently track the current
    nodes in the two input lists. Either list may finish before the
    other.
-   **Carry:** We use the same carry technique as manual decimal
    addition.
-   **Dummy Head:** It simplifies building the result list and avoids
    special handling for the first node.

### Why this pattern?

The input digits are already in reverse order, so the least significant
digits are at the heads of the lists. We can add directly from left to
right through the linked lists without reversing them or converting the
numbers into integers.

This is efficient and also avoids integer overflow for long input lists.

## Complexity Analysis

Let:

-   `m` = number of nodes in `l1`
-   `n` = number of nodes in `l2`

### Time Complexity: `O(max(m, n))`

We traverse each input list once. Each iteration processes one digit
position, and the number of iterations is at most `max(m, n) + 1`, where
the extra iteration may be needed for a final carry.

Therefore, the time complexity is:

``` text
O(max(m, n))
```

### Auxiliary Space Complexity: `O(1)`

Only a fixed number of helper variables and pointers are used (`dummy`,
`current`, `carry`, `sum`, and `digit`). No extra array, stack, or
recursion is used.

Therefore, the **auxiliary space complexity** is:

``` text
O(1)
```

**Note:** The returned linked list itself requires `O(max(m, n))` space
in the worst case (including a possible extra carry node). If output
space is included, total space is `O(max(m, n))`.

## Edge Cases Covered

-   Both lists contain only zero: `[0] + [0] = [0]`.
-   One list is longer than the other.
-   Addition produces a carry at an intermediate digit.
-   Addition produces a final carry, such as `999 + 1 = 1000`.
-   Both lists contain the maximum allowed number of nodes.

## Key Takeaways

-   Reverse-order digits allow addition to begin directly at the head
    nodes.
-   Always include `carry` in the next digit's sum.
-   Use `sum % 10` for the current digit and `sum / 10` for the next
    carry.
-   Continue the loop while either list has nodes **or** a carry
    remains.
-   A dummy head makes result-list construction simple.
-   The solution runs in `O(max(m, n))` time with `O(1)` auxiliary
    space.

------------------------------------------------------------------------

**LeetCode Topic Tags:** Linked List, Math, Recursion (this solution is
iterative), Two Pointers
