# Same Tree

## Problem

Given the roots of two binary trees `p` and `q`, check whether the two trees are the same.

Two binary trees are considered the same when:

1. They have the **same structure**.
2. Corresponding nodes have the **same value**.

---

## Example 1

```text
Input:
p = [1,2,3]
q = [1,2,3]

Output:
true
```

Both trees have the same structure and the same node values.

```text
    1              1
   / \            / \
  2   3          2   3
```

---

## Example 2

```text
Input:
p = [1,2]
q = [1,null,2]

Output:
false
```

The node `2` exists on the left side in `p`, but on the right side in `q`.

```text
    1              1
   /                \
  2                  2
```

The structure is different, so the answer is `false`.

---

## Example 3

```text
Input:
p = [1,2,1]
q = [1,1,2]

Output:
false
```

The trees contain the same values but the corresponding positions are different.

Therefore, the trees are not the same.

---

# Approach

We compare both trees **node by node** using recursion.

For every pair of corresponding nodes:

1. If both nodes are `null`, they are the same.
2. If one node is `null` and the other is not, the trees are different.
3. If their values are different, the trees are different.
4. Otherwise:

   * Compare their left subtrees.
   * Compare their right subtrees.
5. Both left and right subtrees must be the same.

The important condition is:

```java
p.val == q.val
&& isSameTree(p.left, q.left)
&& isSameTree(p.right, q.right)
```

This ensures that both **value and structure** are checked.

---

# Algorithm

### Step 1

Start with the roots `p` and `q`.

### Step 2

Check whether either node is `null`.

```java
if (p == null || q == null) {
    return p == q;
}
```

* If both are `null` → return `true`.
* If only one is `null` → return `false`.

### Step 3

Compare the values of the current nodes.

```java
p.val == q.val
```

If the values are different, return `false`.

### Step 4

Recursively compare the left children.

```java
isSameTree(p.left, q.left)
```

### Step 5

Recursively compare the right children.

```java
isSameTree(p.right, q.right)
```

### Step 6

Return `true` only when:

* Current values are equal.
* Left subtrees are same.
* Right subtrees are same.

---

# Step-by-Step Traversal

Suppose:

```text
p = [1,2,3]
q = [1,2,3]
```

Tree:

```text
        1
       / \
      2   3
```

### 1. Compare root

```text
p = 1
q = 1
```

Values are equal.

```text
1 == 1 → true
```

Move to the left subtree.

### 2. Compare left nodes

```text
p = 2
q = 2
```

Values are equal.

```text
2 == 2 → true
```

Check their left children.

Both are `null`.

```text
null == null → true
```

Check their right children.

Both are `null`.

```text
null == null → true
```

So node `2` is the same.

### 3. Compare right nodes

```text
p = 3
q = 3
```

Values are equal.

```text
3 == 3 → true
```

Both left children are `null` and both right children are `null`.

Therefore, node `3` is also the same.

### 4. Final Result

```text
Root same
   ↓
Left subtree same
   ↓
Right subtree same
   ↓
true
```

Therefore:

```text
Output = true
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
    public boolean isSameTree(TreeNode p, TreeNode q) {

        // If either tree is empty
        if (p == null || q == null) {
            return p == q;
        }

        // Compare current node and both subtrees
        return p.val == q.val
                && isSameTree(p.left, q.left)
                && isSameTree(p.right, q.right);
    }
}
```

---

# Why `p == q` When Null?

This line:

```java
return p == q;
```

handles both cases:

### Case 1: Both are null

```text
p = null
q = null

p == q → true
```

Both trees have no node at this position, so they are structurally the same.

### Case 2: Only one is null

```text
p = node
q = null

p == q → false
```

One tree has a node while the other does not, so their structures are different.

This makes the null handling very concise.

---

# Pattern Used

## Pattern: Recursion / DFS (Depth-First Search)

This solution uses **recursive tree traversal**, which follows a DFS-style approach.

For every node, we recursively visit:

```text
Current Node
    ↓
Left Subtree
    ↓
Right Subtree
```

This is essentially checking the two trees in parallel.

---

# Why Use This Pattern?

A binary tree is naturally a recursive data structure because:

```text
Tree
 ├── Left Subtree
 └── Right Subtree
```

Each subtree is itself another binary tree.

Therefore, the same function can be used to compare smaller subtrees:

```java
isSameTree(p.left, q.left)
isSameTree(p.right, q.right)
```

This makes the solution:

* Simple
* Easy to understand
* Easy to implement
* Efficient
* Naturally suited for binary trees

---

# Complexity Analysis

Let `n` be the number of nodes that need to be compared.

## Time Complexity

```text
O(n)
```

### Why?

Each corresponding node is visited at most once.

For every node, we perform constant work:

```text
Compare null
Compare value
Compare left
Compare right
```

Therefore:

```text
Time = O(n)
```

If the two trees have `n` nodes each, we compare at most `n` corresponding nodes.

---

## Space Complexity

```text
O(h)
```

where `h` is the height of the tree.

The recursion stack can contain nodes from the current path.

### Balanced Tree

For a balanced tree:

```text
h = log(n)
```

Therefore:

```text
Space = O(log n)
```

### Skewed Tree

For a completely skewed tree:

```text
h = n
```

Therefore:

```text
Space = O(n)
```

### Final Space Complexity

```text
O(h)
```

or, in the worst case:

```text
O(n)
```

---

# Complexity Summary

| Complexity       | Result                          |
| ---------------- | ------------------------------- |
| Time             | `O(n)`                          |
| Space            | `O(h)`                          |
| Worst-case Space | `O(n)`                          |
| Pattern          | Recursion / DFS                 |
| Traversal        | Left + Right subtree comparison |

---

# Key Idea

The main idea is:

> **Two trees are the same only when their current nodes are equal and their corresponding left and right subtrees are also the same.**

In short:

```text
Same Tree =
    Same Root
    +
    Same Left Subtree
    +
    Same Right Subtree
```

---

# Code Logic in One Line

```java
return p.val == q.val
        && isSameTree(p.left, q.left)
        && isSameTree(p.right, q.right);
```

This single statement performs the complete recursive comparison.

---

# Pattern Recognition

When you see a binary tree problem asking to:

* Compare two trees
* Check whether two structures are identical
* Compare corresponding nodes
* Validate two subtrees

Think of:

```text
Recursive DFS
```

The general template is:

```java
if (both nodes are null)
    return true;

if (one node is null)
    return false;

if (values are different)
    return false;

compare left subtrees;
compare right subtrees;
```

This pattern is useful for many binary-tree problems.
