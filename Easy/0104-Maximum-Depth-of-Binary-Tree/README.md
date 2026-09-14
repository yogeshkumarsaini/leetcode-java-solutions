# Maximum Depth of Binary Tree

## Problem

Given the `root` of a binary tree, return its **maximum depth**.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

### Example 1

```text
Input:  root = [3,9,20,null,null,15,7]
Output: 3
```

Tree:

```text
        3
       / \
      9  20
         / \
        15  7
```

The longest path is:

```text
3 -> 20 -> 15
```

So the maximum depth is `3`.

### Example 2

```text
Input:  root = [1,null,2]
Output: 2
```

---

## Approach

We use **Recursion (DFS - Depth First Search)**.

For every node:

1. Find the maximum depth of its left subtree.
2. Find the maximum depth of its right subtree.
3. Take the larger of the two depths.
4. Add `1` for the current node.

### Formula

```text
maxDepth(root) = max(maxDepth(root.left), maxDepth(root.right)) + 1
```

### Base Case

If the current node is `null`, there are no nodes in that subtree:

```java
if (root == null) {
    return 0;
}
```

---

## Algorithm

1. Start from the root node.
2. If the root is `null`, return `0`.
3. Recursively calculate the depth of the left subtree.
4. Recursively calculate the depth of the right subtree.
5. Take the maximum of the left and right depths.
6. Add `1` for the current node.
7. Return the result.

---

## Step-by-Step Traversal

For:

```text
        3
       / \
      9  20
         / \
        15  7
```

### Step 1
Start at `3`.

```text
maxDepth(3)
```

Calculate both subtrees.

### Step 2
Go left:

```text
maxDepth(9)
```

Node `9` has no children:

```text
left = 0
right = 0
depth = max(0, 0) + 1 = 1
```

Return `1`.

### Step 3
Go right:

```text
maxDepth(20)
```

Node `20` has two children: `15` and `7`.

### Step 4
For node `15`:

```text
depth = max(0, 0) + 1 = 1
```

Return `1`.

### Step 5
For node `7`:

```text
depth = max(0, 0) + 1 = 1
```

Return `1`.

### Step 6
At node `20`:

```text
leftDepth  = 1
rightDepth = 1

depth = max(1, 1) + 1
      = 2
```

Return `2`.

### Step 7
At node `3`:

```text
leftDepth  = 1
rightDepth = 2

depth = max(1, 2) + 1
      = 3
```

Final answer:

```text
3
```

---

## Java Solution

```java
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        return Math.max(leftDepth, rightDepth) + 1;
    }
}
```

---

## Pattern Used

### Pattern: Tree DFS + Recursion

This problem uses the **Depth First Search (DFS)** pattern with recursion.

Why?

Because we need to go down each branch of the tree and find the deepest path.

For every node, the same operation is repeated:

```text
left subtree depth
right subtree depth
take maximum
add current node
```

This is a classic **Tree Recursion** pattern.

### General Pattern

```java
if (root == null) {
    return baseValue;
}

left = solve(root.left);
right = solve(root.right);

return combine(left, right, root);
```

This pattern is useful for many binary-tree problems such as:

- Maximum Depth
- Minimum Depth
- Diameter of Binary Tree
- Balanced Binary Tree
- Maximum Path Sum
- Same Tree
- Symmetric Tree

---

## Complexity Analysis

Let `n` be the number of nodes in the binary tree and `h` be the height of the tree.

### Time Complexity

**O(n)**

Every node is visited exactly once.

```text
n nodes -> n recursive calls
```

So:

```text
Time = O(n)
```

### Space Complexity

**O(h)**

The recursion stack stores the current path from the root to the deepest node.

- Balanced tree: `h = O(log n)`
- Skewed tree: `h = O(n)`

Therefore:

```text
Space = O(h)
```

Worst case:

```text
O(n)
```

---

## Key Takeaway

The important idea is:

> The depth of a node is `1 + the maximum depth of its left and right subtrees`.

Remember this pattern:

```java
return Math.max(maxDepth(root.left),
                maxDepth(root.right)) + 1;
```

**Pattern:** Tree DFS / Recursion  
**Time:** O(n)  
**Space:** O(h), worst case O(n)
