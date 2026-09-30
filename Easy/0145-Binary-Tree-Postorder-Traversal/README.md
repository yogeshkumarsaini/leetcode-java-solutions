# Binary Tree Postorder Traversal

## Problem

Given the root of a binary tree, return the **postorder traversal** of its nodes' values.

Postorder traversal follows this order:

```text
Left → Right → Root
```

### Example

```text
Input:  root = [1,null,2,3]
Output: [3,2,1]
```

---

## 1. Recursive Approach

The recursive solution is the simplest way to understand postorder traversal.

### Java Code

```java
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        postorder(root, result);

        return result;
    }

    public void postorder(TreeNode root, List<Integer> result) {

        if (root == null) {
            return;
        }

        postorder(root.left, result);
        postorder(root.right, result);
        result.add(root.val);
    }
}
```

---

## 2. Approach Details

For every node, we perform three operations:

1. Traverse the **left subtree**.
2. Traverse the **right subtree**.
3. Add the **current node** to the result.

This gives:

```text
Left → Right → Root
```

For example:

```text
        1
         \
          2
         /
        3
```

Traversal:

```text
1
└── Right → 2
    └── Left → 3
```

So:

```text
3 → 2 → 1
```

Result:

```text
[3, 2, 1]
```

---

## 3. Step-by-Step Traversal

Consider:

```text
        1
       / \
      2   3
     / \
    4   5
       / \
      6   7
```

Postorder means:

```text
Left → Right → Root
```

### Step 1

Start at `1`.

Go to the left subtree:

```text
1 → 2
```

### Step 2

At `2`, go left:

```text
2 → 4
```

Node `4` has no children.

Add:

```text
4
```

### Step 3

Return to `2`.

Go right:

```text
2 → 5
```

At `5`, go left:

```text
5 → 6
```

Add:

```text
6
```

### Step 4

Return to `5`.

Go right:

```text
5 → 7
```

Add:

```text
7
```

Now both children of `5` are processed.

Add:

```text
5
```

### Step 5

Return to `2`.

Both children are processed.

Add:

```text
2
```

### Step 6

Return to `1`.

Go right:

```text
1 → 3
```

Node `3` has no children.

Add:

```text
3
```

### Step 7

Now both children of `1` are processed.

Add:

```text
1
```

Final traversal:

```text
[4, 6, 7, 5, 2, 3, 1]
```

---

## 4. Algorithm

### Recursive Algorithm

```text
postorder(root):

    if root is null:
        return

    postorder(root.left)

    postorder(root.right)

    add root.val to result
```

---

## 5. Iterative Approach

The follow-up asks:

> Could you do it iteratively?

Yes.

A stack can be used instead of recursion.

One easy iterative method uses **one stack + a previous/visited node reference**.

### Java Code

```java
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        TreeNode previous = null;

        while (current != null || !stack.isEmpty()) {

            // Go as far left as possible
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            TreeNode peekNode = stack.peek();

            // If right child exists and has not been processed
            if (peekNode.right != null && previous != peekNode.right) {
                current = peekNode.right;
            } else {
                // Both left and right are processed
                result.add(peekNode.val);
                previous = stack.pop();
            }
        }

        return result;
    }
}
```

---

## 6. Why `previous` Is Used in Iterative Solution

Postorder requires:

```text
Left → Right → Root
```

When we reach a node, we need to know whether its right subtree has already been processed.

`previous` stores the node that was processed most recently.

For example:

```text
      1
     / \
    2   3
```

After processing node `3`:

```text
previous = 3
```

When we return to `1`, we check:

```java
previous == peekNode.right
```

If true, the right subtree is already processed.

Therefore, we can safely process `1`.

---

## 7. Iterative Step-by-Step Traversal

For:

```text
        1
       / \
      2   3
     / \
    4   5
```

### Step 1

Start:

```text
current = 1
```

Push `1`.

Go left.

```text
stack = [1]
```

### Step 2

Push `2`.

Go left.

```text
stack = [1, 2]
```

### Step 3

Push `4`.

`4` has no left child.

```text
stack = [1, 2, 4]
```

`4` has no right child.

So process `4`.

```text
result = [4]
```

### Step 4

Return to `2`.

Its right child is `5`.

Go to `5`.

```text
stack = [1, 2, 5]
```

Process `5`.

```text
result = [4, 5]
```

### Step 5

Return to `2`.

Both left and right are processed.

Process `2`.

```text
result = [4, 5, 2]
```

### Step 6

Return to `1`.

Its right child is `3`.

Go to `3`.

Process `3`.

```text
result = [4, 5, 2, 3]
```

### Step 7

Both subtrees of `1` are processed.

Process `1`.

Final result:

```text
[4, 5, 2, 3, 1]
```

---

## 8. Complexity

Let `n` be the number of nodes.

### Recursive Solution

**Time Complexity:**

```text
O(n)
```

Every node is visited once.

**Space Complexity:**

```text
O(h)
```

where `h` is the height of the tree.

The recursive call stack can contain up to `h` nodes.

Worst case for a skewed tree:

```text
O(n)
```

For a balanced tree:

```text
O(log n)
```

---

### Iterative Solution

**Time Complexity:**

```text
O(n)
```

Every node is pushed and popped once.

**Space Complexity:**

```text
O(h)
```

The stack can contain up to the height of the tree.

Worst case:

```text
O(n)
```

Balanced tree:

```text
O(log n)
```

---

## 9. Pattern Used

### Recursive Pattern: Tree DFS

The recursive solution uses:

```text
Depth First Search (DFS)
```

More specifically, it is **Postorder DFS**.

The pattern is:

```text
Left
↓
Right
↓
Root
```

This pattern is useful when the current node should be processed **after both of its children**.

---

### Iterative Pattern: Stack-Based DFS

The iterative solution uses:

```text
Stack + DFS + Previous/Visited State
```

Why?

Because recursion internally uses a call stack.

When recursion is removed, we manually create a stack to remember the nodes that still need to be processed.

The `previous` variable helps us determine whether the right subtree has already been visited.

---

## 10. Important Difference Between Tree Traversals

There are three common DFS traversals:

### Preorder

```text
Root → Left → Right
```

Example:

```text
[1, 2, 4, 5, 3]
```

### Inorder

```text
Left → Root → Right
```

Example:

```text
[4, 2, 5, 1, 3]
```

### Postorder

```text
Left → Right → Root
```

Example:

```text
[4, 5, 2, 3, 1]
```

The main thing to remember:

```text
PREORDER   = Root first
INORDER    = Root middle
POSTORDER  = Root last
```

---

## 11. Edge Cases

### Empty Tree

```text
Input:  []
Output: []
```

Because:

```java
if (root == null) {
    return;
}
```

handles the empty tree.

### Single Node

```text
Input:
[1]

Output:
[1]
```

### Only Left Child

```text
    1
   /
  2
 /
3
```

Postorder:

```text
[3, 2, 1]
```

### Only Right Child

```text
1
 \
  2
   \
    3
```

Postorder:

```text
[3, 2, 1]
```

---

## 12. Final Takeaway

The easiest way to remember postorder traversal is:

```text
POSTORDER = LEFT → RIGHT → ROOT
```

Recursive solution:

```java
postorder(root.left, result);
postorder(root.right, result);
result.add(root.val);
```

For the iterative follow-up, use:

```text
Stack + previous/visited state
```

The key idea is:

> A node can be added to the answer only after both its left and right subtrees have been processed.
