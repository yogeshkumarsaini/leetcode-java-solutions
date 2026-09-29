# Binary Tree Preorder Traversal

## Problem

Given the root of a binary tree, return the preorder traversal of its nodes' values.

### Preorder Traversal

Preorder follows this order:

**Root → Left → Right**

Example:

```text
        1
         \
          2
         /
        3
```

Preorder traversal:

```text
[1, 2, 3]
```

---

## Java Solution

```java
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> l = new ArrayList<>();
        preOrder(root, l);
        return l;
    }

    public void preOrder(TreeNode root, List<Integer> l) {
        if (root == null) {
            return;
        }

        l.add(root.val);
        preOrder(root.left, l);
        preOrder(root.right, l);
    }
}
```

---

## Approach

We use **Depth First Search (DFS)** with recursion.

The preorder pattern is:

```text
Root → Left → Right
```

For every node:

1. Add the current node's value to the result.
2. Traverse the left subtree.
3. Traverse the right subtree.
4. If the node is `null`, stop that recursive call.

---

## Step-by-Step Traversal

For this tree:

```text
        1
       / \
      2   3
     / \
    4   5
       / \
      6   7
```

Traversal:

```text
Visit 1
Result = [1]

Go left → 2
Result = [1, 2]

Go left → 4
Result = [1, 2, 4]

4 has no children → return

Go right → 5
Result = [1, 2, 4, 5]

Go left → 6
Result = [1, 2, 4, 5, 6]

Go right → 7
Result = [1, 2, 4, 5, 6, 7]

Return to 1

Go right → 3
Result = [1, 2, 4, 5, 6, 7, 3]
```

Final result:

```text
[1, 2, 4, 5, 6, 7, 3]
```

---

## Algorithm

```text
preOrder(root, result)

1. If root == null:
       return

2. Add root.val to result

3. Traverse root.left

4. Traverse root.right
```

---

## Pattern Used

### DFS — Depth First Search

More specifically:

**Preorder DFS**

```text
Root → Left → Right
```

### Why this pattern?

The problem requires nodes to be visited in this exact order:

```text
Root
↓
Left Subtree
↓
Right Subtree
```

Therefore, **Preorder DFS** is the natural pattern.

The three common DFS tree traversals are:

```text
Preorder  = Root → Left → Right
Inorder   = Left → Root → Right
Postorder = Left → Right → Root
```

---

## Why Recursion Works

A binary tree consists of smaller subtrees.

For every node, we perform the same operation:

```text
Current Node
     ↓
Left Subtree
     ↓
Right Subtree
```

When we reach `null`, that subtree is finished and recursion returns to the previous node.

---

## Complexity Analysis

Let `n` be the number of nodes.

### Time Complexity

**O(n)**

Every node is visited exactly once.

```text
n nodes → n visits → O(n)
```

### Space Complexity

The recursive call stack uses:

**O(h)**

where `h` is the height of the tree.

* Balanced tree → **O(log n)**
* Skewed tree → **O(n)**

The result list itself requires **O(n)** space because it stores every node value.

So:

```text
Auxiliary Space = O(h)
Output Space    = O(n)
```

---

## Example

### Input

```text
root = [1, null, 2, 3]
```

Tree:

```text
    1
     \
      2
     /
    3
```

Preorder:

```text
Root → Left → Right

1 → 2 → 3
```

Output:

```text
[1, 2, 3]
```

---

## Edge Cases

### Empty Tree

```text
root = []
```

Since:

```java
root == null
```

the function returns immediately.

Output:

```text
[]
```

### Single Node

```text
root = [1]
```

Output:

```text
[1]
```

---

## Iterative Follow-Up

The problem asks whether preorder traversal can be done without recursion.

Yes.

For the iterative solution, we can use a **Stack**.

Basic idea:

```text
1. Push root
2. Pop a node
3. Add its value
4. Push right child
5. Push left child
```

We push the **right child first** because Stack follows:

```text
LIFO
Last In → First Out
```

Therefore, the left child is processed before the right child.

The iterative solution also takes:

```text
Time  = O(n)
Space = O(h) auxiliary
```

---

## Key Takeaways

* Preorder = **Root → Left → Right**
* Pattern used = **DFS**
* Implementation = **Recursion**
* Every node is visited once.
* Time Complexity = **O(n)**
* Recursive Auxiliary Space = **O(h)**
* Output Space = **O(n)**
* Iterative follow-up can be solved using a **Stack**.
