# Binary Tree Inorder Traversal

## Problem

Given the `root` of a binary tree, return the **inorder traversal** of its nodes' values.

### Inorder Traversal

Inorder traversal follows this order:

```text
Left → Root → Right
```

---

## Example 1

### Input

```text
root = [1,null,2,3]
```

Tree:

```text
    1
     \
      2
     /
    3
```

### Output

```text
[1,3,2]
```

### Explanation

We visit nodes in this order:

```text
Left → Root → Right

1 → 3 → 2
```

So the result is:

```text
[1, 3, 2]
```

---

## Example 2

### Input

```text
root = [1,2,3,4,5,null,8,null,null,6,7,9]
```

### Output

```text
[4,2,6,5,7,1,3,9,8]
```

---

## Example 3

### Input

```text
root = []
```

### Output

```text
[]
```

Because the tree is empty, there is nothing to traverse.

---

## Example 4

### Input

```text
root = [1]
```

### Output

```text
[1]
```

---

# Approach

We use **Recursion** to perform the inorder traversal.

For every node, we perform three steps:

1. Traverse the **left subtree**
2. Add the **current node's value**
3. Traverse the **right subtree**

In short:

```text
Left → Root → Right
```

The recursive function keeps going to the left until it reaches a `null` node.

After reaching `null`, it returns to the previous node, adds that node's value, and then moves to the right subtree.

---

# Algorithm

1. Create an empty `List<Integer>` called `result`.
2. Call the recursive `inorder()` function with the root node.
3. If the current node is `null`, return.
4. Recursively traverse the left subtree.
5. Add the current node's value to `result`.
6. Recursively traverse the right subtree.
7. Return the `result` list.

---

# Step-by-Step Traversal

Consider this tree:

```text
        1
       / \
      2   3
     / \
    4   5
       / \
      6   7
```

We need:

```text
Left → Root → Right
```

### Step 1

Start at:

```text
1
```

Go to the left:

```text
2
```

### Step 2

From `2`, go to the left:

```text
4
```

`4` has no left child.

So add:

```text
result = [4]
```

### Step 3

Return to `2`.

Add `2`:

```text
result = [4, 2]
```

### Step 4

Now go to the right of `2`:

```text
5
```

Go to the left:

```text
6
```

Add `6`:

```text
result = [4, 2, 6]
```

Return to `5`.

Add `5`:

```text
result = [4, 2, 6, 5]
```

### Step 5

Go to the right of `5`:

```text
7
```

Add `7`:

```text
result = [4, 2, 6, 5, 7]
```

### Step 6

Return to root `1`.

Add `1`:

```text
result = [4, 2, 6, 5, 7, 1]
```

### Step 7

Now traverse the right subtree:

```text
3
```

Add `3`:

```text
result = [4, 2, 6, 5, 7, 1, 3]
```

Final answer:

```text
[4, 2, 6, 5, 7, 1, 3]
```

---

# Java Solution

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {

    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> result = new ArrayList<>();

        inorder(root, result);

        return result;
    }

    public void inorder(TreeNode root, List<Integer> result) {

        // Base Case
        if (root == null) {
            return;
        }

        // 1. Left
        inorder(root.left, result);

        // 2. Root
        result.add(root.val);

        // 3. Right
        inorder(root.right, result);
    }
}
```

---

# Code Explanation

### 1. Create Result List

```java
List<Integer> result = new ArrayList<>();
```

This list stores the nodes in inorder sequence.

---

### 2. Call Recursive Function

```java
inorder(root, result);
```

We start traversal from the root node.

---

### 3. Base Case

```java
if (root == null) {
    return;
}
```

If there is no node, we stop that recursive call.

This is important to prevent infinite recursion.

---

### 4. Traverse Left

```java
inorder(root.left, result);
```

First, we completely process the left subtree.

---

### 5. Process Root

```java
result.add(root.val);
```

After the left subtree is completed, we add the current node.

---

### 6. Traverse Right

```java
inorder(root.right, result);
```

Finally, we process the right subtree.

Therefore:

```text
Left → Root → Right
```

---

# Pattern Used

## Binary Tree Traversal Pattern

This solution uses the **Tree Traversal / DFS (Depth First Search)** pattern.

Specifically:

```text
Inorder Traversal
```

The three common DFS tree traversal patterns are:

```text
Preorder  → Root → Left → Right

Inorder   → Left → Root → Right

Postorder → Left → Right → Root
```

For this problem, we need:

```text
Inorder → Left → Root → Right
```

---

# Why Use Inorder Traversal?

Because the problem specifically asks for the **inorder traversal** of the binary tree.

The required order is:

```text
Left Subtree
      ↓
Current Node
      ↓
Right Subtree
```

Recursion is a natural fit because every subtree is itself a smaller binary tree.

So the same operation can be repeatedly applied to:

```text
root.left
root.right
```

---

# Recursion Flow

For every node:

```text
inorder(node)
    |
    ├── inorder(node.left)
    |
    ├── add node.val
    |
    └── inorder(node.right)
```

This continues until:

```text
node == null
```

---

# Complexity Analysis

Let `n` be the number of nodes in the binary tree.

## Time Complexity

```text
O(n)
```

### Why?

Every node is visited exactly once.

For example, if the tree contains:

```text
n = 100
```

then each of the 100 nodes is processed once.

Therefore:

```text
Time = O(n)
```

---

## Space Complexity

```text
O(n)
```

There are two sources of space:

### 1. Result List

The result list stores all `n` node values:

```text
O(n)
```

### 2. Recursion Stack

In the worst case, the tree can be completely skewed:

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

The recursion depth becomes `n`:

```text
O(n)
```

Therefore overall auxiliary/result-related space is:

```text
O(n)
```

More precisely:

```text
Result List = O(n)
Recursion Stack = O(h)
```

where `h` is the height of the tree.

So:

```text
Total = O(n + h)
```

Since `h <= n`:

```text
Overall Space = O(n)
```

---

# Follow-Up: Iterative Solution

The problem asks:

> Recursive solution is trivial, could you do it iteratively?

Yes.

For the iterative solution, we can use a **Stack** to simulate recursion.

The basic idea is:

```text
Go as far left as possible
        ↓
Process node
        ↓
Move to right subtree
        ↓
Repeat
```

The pattern remains:

```text
Left → Root → Right
```

but instead of the system's recursion stack, we manually use:

```java
Stack<TreeNode>
```

### Iterative Complexity

```text
Time Complexity:  O(n)
Space Complexity: O(n)
```

---

# Key Takeaway

Remember inorder traversal with this simple rule:

```text
INORDER = Left → Root → Right
```

For a binary tree:

```text
        Root
       /    \
    Left    Right
```

visit:

```text
Left
  ↓
Root
  ↓
Right
```

### Traversal Patterns to Remember

```text
Preorder  = Root → Left → Right
Inorder   = Left → Root → Right
Postorder = Left → Right → Root
```

This is a fundamental **Binary Tree + DFS + Recursion** pattern and is useful for many tree-based problems.
