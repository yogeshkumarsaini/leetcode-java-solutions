# Convert Sorted Array to Binary Search Tree

## Problem

Given an integer array `nums` where the elements are sorted in ascending order, convert it into a **height-balanced Binary Search Tree (BST)**.

A height-balanced binary tree is a tree where the height difference between the left and right subtrees of every node is at most `1`.

### Example

```text
Input:
nums = [-10, -3, 0, 5, 9]

Output:
[0, -3, 9, -10, null, 5]
```

Another valid answer is:

```text
[0, -10, 5, null, -3, null, 9]
```

Both are height-balanced BSTs.

---

# Approach

Because the input array is already sorted, we can use the **middle element** as the root of the BST.

Why?

* Elements smaller than the middle value belong to the left subtree.
* Elements greater than the middle value belong to the right subtree.
* Choosing the middle element keeps the number of elements on both sides approximately equal.
* Therefore, the resulting BST remains height-balanced.

We recursively apply the same process to the left and right portions of the array.

### Basic Idea

```text
Sorted Array
     |
     ↓
Choose Middle Element
     |
     ↓
    Root
   /    \
Left    Right
Part     Part
 |        |
 ↓        ↓
Recursively create subtrees
```

---

# Algorithm

1. Start with the complete array.
2. Find the middle index.
3. Create a `TreeNode` using the middle element.
4. Recursively create the left subtree using the elements before the middle.
5. Recursively create the right subtree using the elements after the middle.
6. If `left > right`, return `null`.
7. Return the root node.

---

# Step-by-Step Traversal / Tree Creation

For:

```text
nums = [-10, -3, 0, 5, 9]
```

### Step 1: Create Root

Array range:

```text
[-10, -3, 0, 5, 9]
```

Middle element:

```text
0
```

So:

```text
        0
```

---

### Step 2: Create Left Subtree

Left portion:

```text
[-10, -3]
```

Middle element:

```text
-3
```

So:

```text
        0
       /
     -3
```

---

### Step 3: Create Right Subtree

Right portion:

```text
[5, 9]
```

Middle element:

```text
5
```

So:

```text
        0
       / \
     -3   5
```

---

### Step 4: Continue Recursively

For `[-10]`:

```text
-10
```

becomes the left child of `-3`.

For `[9]`:

```text
9
```

becomes the right child of `5`.

Final tree:

```text
        0
       / \
     -3   5
     /     \
   -10      9
```

This tree is a valid **height-balanced BST**.

---

# Java Solution

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *
 *     TreeNode() {}
 *
 *     TreeNode(int val) {
 *         this.val = val;
 *     }
 *
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {

    public TreeNode sortedArrayToBST(int[] nums) {
        return createBST(nums, 0, nums.length - 1);
    }

    private TreeNode createBST(int[] nums, int left, int right) {

        // Base case
        if (left > right) {
            return null;
        }

        // Find middle index
        int mid = left + (right - left) / 2;

        // Create root node
        TreeNode root = new TreeNode(nums[mid]);

        // Create left subtree
        root.left = createBST(nums, left, mid - 1);

        // Create right subtree
        root.right = createBST(nums, mid + 1, right);

        return root;
    }
}
```

---

# Dry Run

For:

```text
nums = [-10, -3, 0, 5, 9]
```

Initial call:

```text
createBST(nums, 0, 4)
```

Middle:

```text
mid = 0 + (4 - 0) / 2
    = 2
```

So:

```text
root = 0
```

### Left Call

```text
createBST(nums, 0, 1)
```

Middle:

```text
mid = 0
```

So:

```text
root.left = -10
```

Depending on the chosen middle convention, this can produce a different but still valid balanced BST.

### Right Call

```text
createBST(nums, 3, 4)
```

Middle:

```text
mid = 3
```

So:

```text
root.right = 5
```

Then the remaining elements become child nodes.

---

# Base Case

```java
if (left > right) {
    return null;
}
```

This is important because when there are no elements left in a particular range, there is no node to create.

For example:

```text
createBST(nums, 2, 1)
```

Here:

```text
left > right
```

Therefore:

```text
return null;
```

---

# Why Use Recursion?

The problem naturally contains smaller versions of itself.

For example:

```text
Complete Array
      |
      ↓
    Middle
   /      \
 Left      Right
Array      Array
  |          |
  ↓          ↓
Same       Same
Problem    Problem
```

The left and right portions are again sorted arrays that need to be converted into balanced BSTs.

Therefore, recursion is a natural solution.

---

# Pattern Used

## Divide and Conquer

This solution uses the **Divide and Conquer** pattern along with **Recursion**.

### Why Divide and Conquer?

We divide the array around the middle:

```text
[-10, -3, 0, 5, 9]
          |
          ↓
          0
        /   \
[-10,-3]   [5,9]
```

Then solve each half independently.

```text
Left Half  → Left Subtree
Right Half → Right Subtree
```

This process continues until all elements are converted into tree nodes.

---

# Complexity Analysis

## Time Complexity

```text
O(n)
```

There are `n` elements in the array, and every element is processed exactly once to create one `TreeNode`.

Therefore:

```text
Time = O(n)
```

---

## Space Complexity

### Auxiliary Space

```text
O(log n)
```

Because the tree is height-balanced, its height is approximately:

```text
log₂(n)
```

The recursive function call stack therefore requires:

```text
O(log n)
```

### Output Tree Space

The resulting BST contains `n` nodes:

```text
O(n)
```

So we can describe it as:

```text
Auxiliary Space = O(log n)
Output Space     = O(n)
```

---

# Important Point

The following line:

```java
int mid = left + (right - left) / 2;
```

is preferred over:

```java
int mid = (left + right) / 2;
```

because `left + right` can potentially overflow for very large integer indexes.

The safer standard formula is:

```java
left + (right - left) / 2
```

---

# Key Takeaway

Whenever you see:

* Sorted array
* Need to create a balanced BST
* Need approximately equal elements on both sides

Think:

```text
SORTED ARRAY
     ↓
MIDDLE ELEMENT
     ↓
    ROOT
   ↙    ↘
 LEFT   RIGHT
  ↓       ↓
RECURSION
```

### Pattern

**Divide & Conquer + Recursion**

### Time

**O(n)**

### Auxiliary Space

**O(log n)**

### Output Space

**O(n)**
