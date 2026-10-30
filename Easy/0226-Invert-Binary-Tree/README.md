# Invert Binary Tree

## Problem

Given the `root` of a binary tree, invert the tree and return its root.

Inverting a binary tree means swapping the **left child** and **right child** of every node.

### Example

```text
Input:
        4
       / \
      2   7
     / \ / \
    1  3 6  9

Output:
        4
       / \
      7   2
     / \ / \
    9  6 3  1
```

**Input:** `[4,2,7,1,3,6,9]`  
**Output:** `[4,7,2,9,6,3,1]`

---

## Java Solution

```java
class Solution {
    public TreeNode invertTree(TreeNode root) {

        // Base case: if tree is empty
        if (root == null) {
            return null;
        }

        // Swap left and right child
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // Invert left subtree
        invertTree(root.left);

        // Invert right subtree
        invertTree(root.right);

        return root;
    }
}
```

---

## Approach

We use **Recursion + Depth First Search (DFS)**.

At every node:

1. Check whether the node is `null`.
2. If it is `null`, return `null`.
3. Swap the node's left and right children.
4. Recursively invert the left subtree.
5. Recursively invert the right subtree.
6. Return the root.

The main idea is:

```text
Swap
  ↓
Invert Left Subtree
  ↓
Invert Right Subtree
```

Because the same operation is required for every node, recursion is a natural fit.

---

## Detailed Explanation

Suppose the tree is:

```text
        4
       / \
      2   7
     / \ / \
    1  3 6  9
```

### Step 1: Start at root `4`

Swap its children:

```text
        4
       / \
      7   2
```

### Step 2: Invert the left subtree

The left child is now `7`.

Before:

```text
    7
   / \
  6   9
```

After swapping:

```text
    7
   / \
  9   6
```

### Step 3: Invert the right subtree

The right child is `2`.

Before:

```text
    2
   / \
  1   3
```

After swapping:

```text
    2
   / \
  3   1
```

### Final Tree

```text
        4
       / \
      7   2
     / \ / \
    9  6 3  1
```

Therefore:

```text
[4,2,7,1,3,6,9]
        ↓
[4,7,2,9,6,3,1]
```

---

## Step-by-Step Traversal

For this problem, the recursive traversal follows:

```text
Node
 ↓
Swap Left & Right
 ↓
Go Left
 ↓
Go Right
```

For the example:

```text
Start: 4

4 → swap
    |
    ├── 7 → swap
    │    ├── 9
    │    └── 6
    │
    └── 2 → swap
         ├── 3
         └── 1
```

A useful way to remember it:

> **At every node, swap first, then recursively process both children.**

---

## Algorithm

```text
invertTree(root):

1. If root == null:
       return null

2. Store root.left in temp

3. Set root.left = root.right

4. Set root.right = temp

5. Call invertTree(root.left)

6. Call invertTree(root.right)

7. Return root
```

---

## Why Recursion?

A binary tree is naturally recursive:

```text
Tree
├── Left Subtree
└── Right Subtree
```

Each subtree is itself a binary tree.

So the same operation can be applied to:

```text
root
left subtree
right subtree
```

until we reach `null`.

This makes the recursive solution simple and easy to understand.

---

## Pattern Used

### Pattern: Tree DFS + Recursion

**Why this pattern?**

We need to visit every node and perform the same operation on each node.

DFS is suitable because we can:

- Visit the current node.
- Swap its children.
- Recursively visit both subtrees.

This is a **Depth First Search (DFS)** traversal using recursion.

---

## Complexity Analysis

Let `n` be the number of nodes.

### Time Complexity: `O(n)`

Every node is visited exactly once.

```text
n nodes
   ↓
Each node processed once
   ↓
O(n)
```

Therefore:

**Time = O(n)**

### Space Complexity: `O(h)`

The recursive calls use the call stack.

Where `h` is the height of the tree.

- Balanced tree → `O(log n)`
- Skewed tree → `O(n)`

Therefore:

**Space = O(h)**

Worst case:

```text
O(n)
```

---

## Edge Cases

### Empty Tree

```text
Input:  []
Output: []
```

Since `root == null`, the method immediately returns `null`.

### Single Node

```text
Input:
    1

Output:
    1
```

There are no children to swap.

### Skewed Tree

```text
    1
     \
      2
       \
        3
```

After inversion:

```text
        1
       /
      2
     /
    3
```

---

## Key Learning

The main operation is:

```java
TreeNode temp = root.left;
root.left = root.right;
root.right = temp;
```

But this operation must be performed for **every node**.

The recursive calls:

```java
invertTree(root.left);
invertTree(root.right);
```

make sure every subtree is also inverted.

### Remember

```text
Invert Binary Tree
       ↓
Swap children
       ↓
DFS left
       ↓
DFS right
```

---

## LeetCode Pattern

- **Data Structure:** Binary Tree
- **Traversal:** DFS
- **Technique:** Recursion
- **Pattern:** Recursive Tree Traversal
- **Time Complexity:** `O(n)`
- **Space Complexity:** `O(h)`, worst case `O(n)`

---

## Repository Structure

```text
Invert-Binary-Tree/
└── README.md
```

---

## Conclusion

The recursive DFS solution is clean and efficient because each node only needs one operation: **swap its left and right children**.

Final implementation:

```java
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}
```