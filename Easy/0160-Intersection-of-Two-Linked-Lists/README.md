# 160. Intersection of Two Linked Lists

## Problem

Given the heads of two singly linked lists `headA` and `headB`, return the node at which the two lists intersect.

If the two linked lists do not intersect, return `null`.

> Important: We need to find the **same node reference**, not just a node having the same value.

---

## Example

```text
List A: 4 → 1 → 8 → 4 → 5
              ↘
                8 → 4 → 5
              ↗
List B: 5 → 6 → 1
```

The intersection node is `8`.

The two `8` values must represent the **same object/reference** in memory.

---

## Given Code

```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;

        while (a != b) {
            if (a == null) {
                a = headB;
            } else {
                a = a.next;
            }

            if (b == null) {
                b = headA;
            } else {
                b = b.next;
            }
        }

        return a;
    }
}
```

---

# Approach

We use the **Two Pointer Switching** technique.

We start two pointers:

```text
a → headA
b → headB
```

Each pointer moves one node at a time.

When a pointer reaches the end of its list, we move it to the **head of the other list**.

```text
a: List A → List B
b: List B → List A
```

This makes both pointers travel the same total distance.

Eventually:

- If the lists intersect, `a` and `b` will point to the **same node**.
- If the lists do not intersect, both will become `null` at the same time.

---

# Why Does Switching Work?

Suppose:

```text
Length of A = a + c
Length of B = b + c
```

Where:

- `a` = nodes only in List A
- `b` = nodes only in List B
- `c` = common/intersection part

Pointer `a` travels:

```text
A + B
= (a + c) + (b + c)
```

Pointer `b` travels:

```text
B + A
= (b + c) + (a + c)
```

Both travel exactly the same total distance.

Therefore, when they reach the common part, they meet at the same node.

---

# Algorithm

1. Create pointer `a` at `headA`.
2. Create pointer `b` at `headB`.
3. Run a loop while `a != b`.
4. Move `a`:
   - If `a == null`, move it to `headB`.
   - Otherwise, move it to `a.next`.
5. Move `b`:
   - If `b == null`, move it to `headA`.
   - Otherwise, move it to `b.next`.
6. When `a == b`, stop.
7. Return `a`.
8. If there is no intersection, both pointers become `null`, so `null` is returned.

---

# Step-by-Step Traversal

Consider:

```text
A: 4 → 1 → 8 → 4 → 5
B: 5 → 6 → 1 → 8 → 4 → 5
```

Intersection starts at node `8`.

Initially:

```text
a = 4
b = 5
```

### Step 1

```text
a = 1
b = 6
```

### Step 2

```text
a = 8
b = 1
```

### Step 3

```text
a = 4
b = 8
```

### Step 4

```text
a = 5
b = 4
```

### Step 5

`a` reaches the end:

```text
a = null
```

So switch `a` to List B:

```text
a = headB
```

At the same time:

```text
b = 5
```

### Step 6

```text
a = 5
b = null
```

Now `b` reaches the end, so switch it to List A:

```text
b = headA
```

### Continue

Both pointers now travel the opposite list.

Eventually:

```text
a = 8
b = 8
```

Now:

```java
a == b
```

is `true`.

Therefore, return the node `8`.

---

# Important Point: `==` vs `.equals()`

We use:

```java
a == b
```

not:

```java
a.val == b.val
```

and not:

```java
a.equals(b)
```

The problem asks for the **same node reference**.

For example:

```text
List A: 1 → 8
List B: 5 → 8
```

The two `8` values may belong to different nodes.

```text
A's 8  → Object A
B's 8  → Object B
```

Even though:

```text
A's 8.val == B's 8.val
```

they are not the same node.

We need:

```java
a == b
```

which checks whether both pointers refer to the exact same object.

---

# No Intersection Case

Example:

```text
A: 2 → 6 → 4
B: 1 → 5
```

The pointers switch lists:

```text
a: A → B
b: B → A
```

After traveling both lists, they both become:

```text
null
```

Therefore:

```java
a == b
```

is true because both are `null`.

The method returns:

```java
null
```

---

# Pattern Used

## Two Pointers + Pointer Switching

This solution uses the **Two Pointer** pattern.

More specifically:

```text
Two Pointers with List Switching
```

### Why this pattern?

The two linked lists can have different lengths.

For example:

```text
A: 1 → 2 → 3 → 4
B: 5 → 6
```

If we simply move both pointers together, they are not aligned.

By switching:

```text
a: A → B
b: B → A
```

both pointers travel the same total distance.

This automatically handles the difference in list lengths without calculating the lengths first.

---

# Why This Approach Is Better

A common approach is:

1. Find length of List A.
2. Find length of List B.
3. Calculate the length difference.
4. Move the longer list ahead.
5. Compare nodes.

That works, but requires extra traversal logic.

The pointer-switching approach is simpler:

```java
while (a != b) {
    a = (a == null) ? headB : a.next;
    b = (b == null) ? headA : b.next;
}
```

It automatically balances the two lists.

---

# Complexity

Let:

- `m` = number of nodes in List A
- `n` = number of nodes in List B

## Time Complexity

```text
O(m + n)
```

Each pointer traverses the two lists at most once.

Therefore, the total work is linear:

```text
O(m + n)
```

## Space Complexity

```text
O(1)
```

Only two pointer variables are used:

```java
ListNode a;
ListNode b;
```

No array, HashSet, or extra data structure is required.

---

# Final Complexity

| Complexity | Result |
|---|---|
| Time | `O(m + n)` |
| Space | `O(1)` |

---

# Key Learning

The most important idea is:

```text
Pointer A: List A → List B
Pointer B: List B → List A
```

This makes both pointers travel equal total distances.

Remember:

> **When two linked lists have different lengths, switching each pointer to the other list automatically balances the distance.**

And for intersection, compare **node references**:

```java
a == b
```

not node values.

---

# Short Version

```java
class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode a = headA;
        ListNode b = headB;

        while (a != b) {

            a = (a == null) ? headB : a.next;
            b = (b == null) ? headA : b.next;
        }

        return a;
    }
}
```

### Pattern

**Two Pointers + Switching**

### Time

**O(m + n)**

### Space

**O(1)**
