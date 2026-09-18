# Minimum Depth of Binary Tree

## Problem

Given the `root` of a binary tree, find its **minimum depth**.

The minimum depth is the number of nodes along the shortest path from the root node down to the **nearest leaf node**.

A **leaf node** is a node that has:

* No left child
* No right child

### Example 1

```text
Input: root = [3,9,20,null,null,15,7]
Output: 2
```

Explanation:

```text
        3
       / \
      9   20
         /  \
        15   7
```

The nearest leaf is `9`.

Path:

```text
3 → 9
```

Minimum depth = `2`

### Example 2

```text
Input: root = [2,null,3,null,4,null,5,null,6]
Output: 5
```

The tree is:

```text
2
 \
  3
   \
    4
     \
      5
       \
        6
```

The only leaf is `6`.

Path:

```text
2 → 3 → 4 → 5 → 6
```

Minimum depth = `5`

---

## Approach

We use **Recursion / Depth-First Search (DFS)**.

For every node, there are four important cases:

### Case 1: Node is `null`

If the tree is empty:

```java
if(root == null){
    return 0;
}
```

Return `0`.

---

### Case 2: Only Right Child Exists

```java
if(root.left == null){
    return 1 + minDepth(root.right);
}
```

We cannot return the minimum of left and right because the left side does not contain a leaf.

We must continue through the existing right subtree.

---

### Case 3: Only Left Child Exists

```java
if(root.right == null){
    return 1 + minDepth(root.left);
}
```

Similarly, if the right child does not exist, we must continue through the left subtree.

---

### Case 4: Both Children Exist

```java
return 1 + Math.min(
    minDepth(root.left),
    minDepth(root.right)
);
```

Both subtrees contain possible paths to a leaf, so we calculate the minimum depth of both and choose the smaller one.

`+1` is used to count the current node.

---

## Algorithm

1. If `root == null`, return `0`.
2. If the node has no left child:

   * Recursively calculate the minimum depth of the right subtree.
   * Add `1` for the current node.
3. If the node has no right child:

   * Recursively calculate the minimum depth of the left subtree.
   * Add `1` for the current node.
4. If both children exist:

   * Calculate the minimum depth of the left subtree.
   * Calculate the minimum depth of the right subtree.
   * Take the smaller value.
   * Add `1` for the current node.
5. Return the result.

---

## Step-by-Step Traversal

Consider:

```text
        3
       / \
      9   20
         /  \
        15   7
```

### Step 1

Start at root:

```text
3
```

Both children exist.

So:

```text
minDepth(3)
= 1 + min(minDepth(9), minDepth(20))
```

---

### Step 2

Go to `9`.

```text
9
```

`9` is a leaf because:

```text
left  = null
right = null
```

So:

```text
minDepth(9) = 1
```

---

### Step 3

Go to `20`.

```text
20
 / \
15  7
```

Both children exist.

So:

```text
minDepth(20)
= 1 + min(minDepth(15), minDepth(7))
```

---

### Step 4

`15` is a leaf:

```text
minDepth(15) = 1
```

`7` is also a leaf:

```text
minDepth(7) = 1
```

Therefore:

```text
minDepth(20)
= 1 + min(1, 1)
= 2
```

---

### Step 5

Now return to root `3`:

```text
minDepth(3)
= 1 + min(1, 2)
= 2
```

Therefore:

```text
Output = 2
```

---

## Java Solution

```java
class Solution {
    public int minDepth(TreeNode root) {

        // Empty tree
        if (root == null) {
            return 0;
        }

        // Only right subtree exists
        if (root.left == null) {
            return 1 + minDepth(root.right);
        }

        // Only left subtree exists
        if (root.right == null) {
            return 1 + minDepth(root.left);
        }

        // Both subtrees exist
        return 1 + Math.min(
            minDepth(root.left),
            minDepth(root.right)
        );
    }
}
```

---

## Why We Handle One-Child Nodes Separately

This is the most important part of the solution.

Suppose the tree is:

```text
    2
     \
      3
       \
        4
         \
          5
```

If we simply wrote:

```java
return 1 + Math.min(
    minDepth(root.left),
    minDepth(root.right)
);
```

For node `2`:

```text
minDepth(null) = 0
minDepth(3) = 3
```

Then:

```text
1 + min(0, 3)
= 1
```

But the correct answer is `4`.

Why?

Because `null` is **not a leaf node**.

A valid minimum-depth path must end at an actual leaf.

Therefore, when one child is missing, we must follow the child that exists.

---

## Pattern Used

### Pattern: Binary Tree Recursion + DFS

This solution uses the:

```text
Tree Recursion
        +
Depth-First Search (DFS)
        +
Divide and Conquer
```

### Why this pattern?

Every node has the same subproblem:

```text
Find minimum depth of my subtree.
```

For a node having both children, the problem can be divided into:

```text
Minimum depth of left subtree
Minimum depth of right subtree
```

Then:

```text
Current node + minimum of both
```

This is a classic **recursive tree problem**.

---

## Complexity

Let `N` be the number of nodes in the tree.

### Time Complexity

```text
O(N)
```

In the worst case, we may visit every node in the tree.

For example:

```text
1
 \
  2
   \
    3
     \
      4
       \
        5
```

Every node needs to be visited.

Therefore:

```text
Time = O(N)
```

### Space Complexity

```text
O(H)
```

where `H` is the height of the tree.

This space is used by the recursive call stack.

#### Balanced Tree

```text
H = log N
```

Therefore:

```text
Space = O(log N)
```

#### Skewed Tree

```text
H = N
```

Therefore:

```text
Space = O(N)
```

So the general recursive-space complexity is:

```text
O(H)
```

---

## Key Takeaway

The main idea is:

> Find the shortest path from the root to a **real leaf**, not to a `null` child.

For every node:

```text
No child
    → return 1

Only left child
    → 1 + left depth

Only right child
    → 1 + right depth

Both children
    → 1 + min(left depth, right depth)
```

This prevents the common mistake of treating a missing child as a valid minimum-depth path.
