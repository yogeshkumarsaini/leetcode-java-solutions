# 🌳 Path Sum

## 📌 Problem

Given the root of a binary tree and an integer `targetSum`, determine whether there exists a **root-to-leaf path** where the sum of all node values is equal to `targetSum`.

A **leaf node** is a node that has:

* No left child
* No right child

---

## 📝 Example

### Example 1

```text
Input:
root = [5,4,8,11,null,13,4,7,2,null,null,null,1]
targetSum = 22

Output:
true
```

Path:

```text
5 → 4 → 11 → 2
```

Sum:

```text
5 + 4 + 11 + 2 = 22
```

Therefore, the answer is `true`.

---

### Example 2

```text
Input:
root = [1,2,3]
targetSum = 5

Output:
false
```

Possible root-to-leaf paths:

```text
1 → 2 = 3
1 → 3 = 4
```

No path has sum `5`.

---

### Example 3

```text
Input:
root = []
targetSum = 0

Output:
false
```

The tree is empty, so there is no root-to-leaf path.

---

# 💡 Approach

We use **Depth-First Search (DFS)** with **recursion**.

Instead of keeping a separate variable for the current path sum, we reduce the `targetSum` while moving down the tree.

For every node:

```text
remaining target = targetSum - current node value
```

When we reach a leaf node, we simply check:

```text
targetSum == root.val
```

If it is equal, we found a valid root-to-leaf path.

---

# 🔍 Why Target Reduction Works?

Suppose:

```text
targetSum = 22
```

and the path is:

```text
5 → 4 → 11 → 2
```

At `5`:

```text
22 - 5 = 17
```

At `4`:

```text
17 - 4 = 13
```

At `11`:

```text
13 - 11 = 2
```

At `2`:

```text
2 == 2
```

So the path sum is exactly `22`.

---

# 🧠 Algorithm

1. If `root == null`, return `false`.
2. Check whether the current node is a **leaf**.
3. If it is a leaf:

   * Check whether `targetSum == root.val`.
   * If yes, return `true`.
   * Otherwise return `false`.
4. Subtract the current node's value from `targetSum`.
5. Recursively check the left subtree.
6. Recursively check the right subtree.
7. If either subtree returns `true`, return `true`.
8. Otherwise return `false`.

---

# 🚶 Step-by-Step Traversal

Consider:

```text
        5
       / \
      4   8
     /   / \
   11   13  4
   / \
  7   2
```

Target:

```text
22
```

### Step 1

Start at root:

```text
5
```

Remaining target:

```text
22 - 5 = 17
```

---

### Step 2

Move to left child:

```text
4
```

Remaining target:

```text
17 - 4 = 13
```

---

### Step 3

Move to:

```text
11
```

Remaining target:

```text
13 - 11 = 2
```

---

### Step 4

Check left child:

```text
7
```

Remaining target:

```text
2 - 7 = -5
```

`7` is a leaf, so:

```text
2 == 7
```

is false.

Return `false`.

---

### Step 5

Now check right child:

```text
2
```

`2` is a leaf.

Check:

```text
2 == 2
```

This is `true`.

Therefore:

```text
true
```

is returned all the way back.

---

# 💻 Java Solution

```java
class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {

        // Empty tree
        if (root == null) {
            return false;
        }

        // Check if current node is a leaf
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }

        // Search left and right subtree
        return hasPathSum(root.left, targetSum - root.val)
                || hasPathSum(root.right, targetSum - root.val);
    }
}
```

---

# 🔎 Code Explanation

### 1. Empty Tree

```java
if (root == null) {
    return false;
}
```

If there is no node, there cannot be a root-to-leaf path.

---

### 2. Check Leaf Node

```java
if (root.left == null && root.right == null) {
    return targetSum == root.val;
}
```

This is the most important condition.

We only check the sum when we reach a **leaf**.

For example:

```text
targetSum = 2
node = 2
```

Then:

```text
2 == 2
```

So we return `true`.

---

### 3. Search Left and Right

```java
return hasPathSum(root.left, targetSum - root.val)
        || hasPathSum(root.right, targetSum - root.val);
```

First subtract the current node:

```text
targetSum - root.val
```

Then continue searching in both subtrees.

The `||` means:

```text
left path is valid
OR
right path is valid
```

If either one is `true`, the final answer is `true`.

---

# 🔄 Recursion Pattern

The recursive function follows this pattern:

```text
Current Node
     ↓
Subtract Current Value
     ↓
   /     \
Left     Right
 ↓         ↓
Recursive Recursive
Call      Call
```

This continues until a leaf node is reached.

---

# 🧩 Pattern Used

## DFS (Depth-First Search)

The main pattern used is:

> **DFS + Recursion**

Why?

Because we need to explore each **root-to-leaf path**.

DFS naturally goes deep into one path before exploring another path.

For example:

```text
5
 ↓
4
 ↓
11
 ↓
7
```

Then it backtracks and checks:

```text
11
 ↓
2
```

Then it explores other branches.

---

## Why Recursion?

A binary tree is naturally recursive:

```text
Tree
 ├── Left Subtree
 └── Right Subtree
```

Every subtree is itself a smaller binary tree.

Therefore, recursion makes the solution simple and readable.

---

# 🔄 Backtracking-Style Target Reduction

We don't explicitly maintain a `currentSum`.

Instead, we reduce the remaining target:

```java
targetSum - root.val
```

Example:

```text
Original target = 22

22 → 17 → 13 → 2 → 2
```

At the leaf:

```text
remaining target == node value
```

means the complete path has the required sum.

This is similar to a **backtracking decision-tree pattern**, although no separate `undo` operation is required because `targetSum - root.val` is passed as a new method argument.

---

# ⏱️ Complexity Analysis

Let:

```text
N = number of nodes
H = height of the tree
```

## Time Complexity

```text
O(N)
```

In the worst case, we may visit every node in the tree.

For example:

```text
        1
       /
      2
     /
    3
   /
  4
 /
5
```

We may have to visit all `N` nodes before finding the answer.

Therefore:

```text
Time = O(N)
```

---

## Space Complexity

```text
O(H)
```

Because we use recursion, the recursion stack can contain nodes along the current root-to-leaf path.

### Balanced Tree

If the tree is balanced:

```text
H = log N
```

Therefore:

```text
Space = O(log N)
```

### Skewed Tree

If the tree is completely skewed:

```text
H = N
```

Therefore:

```text
Space = O(N)
```

So the general space complexity is:

```text
O(H)
```

and worst case:

```text
O(N)
```

---

# 📊 Complexity Summary

| Complexity           | Value           |
| -------------------- | --------------- |
| Time                 | `O(N)`          |
| Space                | `O(H)`          |
| Worst-case Space     | `O(N)`          |
| Pattern              | DFS + Recursion |
| Extra Data Structure | None            |

---

# 🎯 Key Takeaways

1. Use **DFS** to explore root-to-leaf paths.
2. Use recursion because every subtree is another binary tree.
3. Check the sum **only at a leaf node**.
4. Reduce the target using:

```java
targetSum - root.val
```

5. Use `||` because either the left or right subtree can contain the valid path.
6. Time complexity is `O(N)`.
7. Space complexity is `O(H)` because of the recursion stack.

