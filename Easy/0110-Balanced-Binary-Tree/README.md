# Balanced Binary Tree

## Problem

Given the root of a binary tree, determine whether the tree is **height-balanced**.

A binary tree is height-balanced if, for **every node**, the difference between the height of its left subtree and right subtree is at most `1`.

### Condition

```text
|height(left) - height(right)| <= 1
```

### Examples

```text
Input:  [3,9,20,null,null,15,7]
Output: true
```

```text
Input:  [1,2,2,3,3,null,null,4,4]
Output: false
```

```text
Input:  []
Output: true
```

---

## Approach

The given solution uses **Recursion + Height Calculation**.

For every node:

1. Find the height of its left subtree.
2. Find the height of its right subtree.
3. Calculate the absolute difference between the two heights.
4. If the difference is greater than `1`, return `false`.
5. Otherwise, recursively check the left and right subtrees.
6. If all nodes satisfy the condition, return `true`.

The important point is that a tree can look balanced at the root but still be unbalanced deeper inside, so we must check **every node**.

---

## Algorithm

### `isBalanced(root)`

1. If `root == null`, return `true`.
2. Calculate:
   - `left = height(root.left)`
   - `right = height(root.right)`
3. If `abs(left - right) > 1`, return `false`.
4. Recursively check:
   - `isBalanced(root.left)`
   - `isBalanced(root.right)`
5. Return the logical AND of both results.

### `height(node)`

1. If `node == null`, return `0`.
2. Recursively calculate the height of the left subtree.
3. Recursively calculate the height of the right subtree.
4. Return:

```text
1 + max(leftHeight, rightHeight)
```

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

### Step 1: Start at node `3`

Calculate the height of:

```text
3.left  -> subtree rooted at 9
3.right -> subtree rooted at 20
```

```text
height(9)  = 1
height(20) = 2
```

Difference:

```text
|1 - 2| = 1
```

So node `3` is balanced.

### Step 2: Check left subtree

Node `9`:

```text
left  = 0
right = 0

|0 - 0| = 0
```

Balanced.

### Step 3: Check right subtree

Node `20`:

```text
        20
       /  \
      15   7
```

```text
height(15) = 1
height(7)  = 1

|1 - 1| = 0
```

Balanced.

### Step 4: Check leaf nodes

Nodes `15` and `7` have no children:

```text
|0 - 0| = 0
```

Therefore every node is balanced.

```text
Output: true
```

---

## Why `Math.abs()` Is Used

The difference can be positive or negative.

For example:

```text
left = 3
right = 1

left - right = 2
```

or:

```text
left = 1
right = 3

left - right = -2
```

We only care about the size of the difference, so:

```java
Math.abs(left - right)
```

is used.

If:

```text
Math.abs(left - right) > 1
```

the current node is unbalanced.

---

## Code

```java
class Solution {
    public boolean isBalanced(TreeNode root) {

        if (root == null) {
            return true;
        }

        int left = height(root.left);
        int right = height(root.right);

        if (Math.abs(left - right) > 1) {
            return false;
        }

        return isBalanced(root.left) && isBalanced(root.right);
    }

    private static int height(TreeNode node) {

        if (node == null) {
            return 0;
        }

        return 1 + Math.max(
            height(node.left),
            height(node.right)
        );
    }
}
```

---

## Pattern Used

### Pattern: Recursion + Tree Height

This problem uses a **Binary Tree Recursive DFS** pattern.

The same pattern is useful for problems involving:

- Tree height/depth
- Tree balance
- Subtree properties
- Diameter of a binary tree
- Maximum/minimum path calculations
- Checking properties of left and right subtrees

### Why this pattern?

A binary tree naturally breaks into smaller binary trees:

```text
          Root
         /    \
      Left    Right
```

So recursion lets us solve the same problem for the left and right subtrees.

The `height()` function follows the same recursive tree structure:

```text
height(node)
    |
    +-- height(left)
    |
    +-- height(right)
```

---

## Complexity of the Given Solution

Let `n` be the number of nodes.

### Time Complexity: `O(n²)` worst case

The reason is that `height()` traverses a subtree again for many different nodes.

For example, in a highly skewed tree:

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

`height()` is called at multiple levels and repeatedly visits nodes that were already visited.

Therefore, the worst-case time complexity is:

```text
O(n²)
```

For a reasonably balanced tree, the repeated height calculations are smaller, but the standard worst-case analysis remains `O(n²)`.

### Space Complexity: `O(h)`

The recursive calls use the call stack.

Where `h` is the height of the tree:

```text
Space = O(h)
```

For a balanced tree:

```text
h = O(log n)
```

For a skewed tree:

```text
h = O(n)
```

So the worst-case auxiliary space is:

```text
O(n)
```

---

## Important Optimization

The given solution is correct, but it recalculates heights repeatedly.

A more efficient solution can calculate the height and balance status **in one DFS traversal**.

The optimized pattern is:

```text
- Return height if subtree is balanced
- Return -1 if subtree is unbalanced
```

This avoids recalculating the same subtree heights.

### Optimized Complexity

```text
Time:  O(n)
Space: O(h)
```

Worst-case space is still `O(n)` because of recursion depth.

---

## Key Takeaways

- A binary tree is balanced only when **every node** satisfies the height difference condition.
- The condition is:

```text
|leftHeight - rightHeight| <= 1
```

- `Math.abs()` handles both positive and negative differences.
- The given solution uses recursive DFS and height calculation.
- The given solution has **O(n²) worst-case time** because heights are recalculated.
- An optimized bottom-up DFS can solve the problem in **O(n)** time.
