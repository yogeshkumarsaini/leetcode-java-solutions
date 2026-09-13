# 🌳 Symmetric Tree (LeetCode 101)

Check whether a binary tree is a **mirror of itself** around its center — i.e., the left subtree is a reflection of the right subtree.

---

## 📌 Problem Statement

Given the `root` of a binary tree, return `true` if it is symmetric around its center, otherwise return `false`.

**Constraints**
- Number of nodes: `[1, 1000]`
- `-100 <= Node.val <= 100`

---

## 🔍 Examples

**Example 1 — Symmetric ✅**
```
Input: root = [1,2,2,3,4,4,3]

            1
          /   \
         2     2
        / \   / \
       3   4 4   3

Output: true
```

**Example 2 — Not Symmetric ❌**
```
Input: root = [1,2,2,null,3,null,3]

            1
          /   \
         2     2
          \      \
           3      3

Output: false
```
Here both `3`s are on the *right side* of their parent — so it's not a true mirror reflection, even though the values match.

---

## 💡 Approach & Intuition

A tree is symmetric if its **left subtree is a mirror reflection of its right subtree**.

Two trees are mirror reflections of each other if:
1. Their root values are equal.
2. The **left** subtree of tree A is a mirror of the **right** subtree of tree B.
3. The **right** subtree of tree A is a mirror of the **left** subtree of tree B.

So instead of comparing a tree to itself, we compare **two pointers** — one starting at `root.left`, the other at `root.right` — and walk them in **opposite directions** at every step (left↔right, right↔left).

---

## 🧩 Pattern Used — Why?

**Pattern: Two-Pointer Tree Traversal (Mirrored DFS / BFS)**

- This is a specialized case of the general **"two-pointer"** pattern, adapted to trees instead of arrays.
- Instead of one traversal, we run **two synchronized traversals** — one going left-then-right, the other going right-then-left — and compare nodes pairwise at each step.
- **Recursive version** → uses **DFS (Depth-First Search)**, since each `isMirror()` call dives straight down one pair of branches before backtracking.
- **Iterative version** → uses **BFS (Breadth-First Search)** with a `Queue`, pushing mirrored node-pairs and comparing them level by level.

This pattern is chosen (over, say, just serializing both subtrees and comparing strings) because it:
- Runs in a single pass — **O(N) time**, no extra tree construction.
- Short-circuits immediately on the first mismatch (no wasted work).
- Naturally handles `null` children without special-casing.

---

## ⚙️ Algorithm

**Recursive (DFS):**
1. If `root` is `null` → tree is trivially symmetric → return `true`.
2. Call a helper `isMirror(left, right)` starting with `root.left` and `root.right`.
3. Inside `isMirror(left, right)`:
   - If both `left` and `right` are `null` → return `true` (matched, nothing more to compare).
   - If only one of them is `null`, OR their values differ → return `false`.
   - Otherwise, recursively check:
     - `isMirror(left.left, right.right)` (outer pair)
     - `isMirror(left.right, right.left)` (inner pair)
   - Return `true` only if **both** recursive calls return `true`.

**Iterative (BFS with Queue):**
1. If `root` is `null` → return `true`.
2. Create a `Queue<TreeNode>` and push `root.left`, then `root.right`.
3. While the queue is not empty:
   - Pop two nodes `t1` and `t2` at a time.
   - If both `null` → continue (this pair matches, skip to next pair).
   - If only one is `null`, OR `t1.val != t2.val` → return `false`.
   - Push `t1.left`, `t2.right` (outer pair) and `t1.right`, `t2.left` (inner pair).
4. If the loop finishes without returning `false` → return `true`.

---

## 🪜 Step-wise Traversal (Dry Run)

Using **Example 1**: `[1,2,2,3,4,4,3]`

```
Call: isMirror(2, 2)              [root.left, root.right]
  → values match (2 == 2)
  ├─ isMirror(left.left=3, right.right=3)
  │     → values match (3 == 3)
  │     ├─ isMirror(null, null) → true
  │     └─ isMirror(null, null) → true
  │     → returns true
  └─ isMirror(left.right=4, right.left=4)
        → values match (4 == 4)
        ├─ isMirror(null, null) → true
        └─ isMirror(null, null) → true
        → returns true

Both branches returned true → isMirror(2,2) = true
Final Result: true ✅
```

For **Example 2**: `[1,2,2,null,3,null,3]`, the call `isMirror(node.left=null, node.right=3)` hits the case where one side is `null` and the other isn't → returns `false` immediately.

---

## ⏱ Complexity Analysis

| Approach   | Time Complexity | Space Complexity | Notes |
|------------|:---------------:|:-----------------:|-------|
| Recursive  | `O(N)`           | `O(H)`             | Every node is visited exactly once → O(N) time. Space is the recursion call stack, bounded by tree height `H`. Worst case (skewed tree) → `O(N)`; best case (balanced tree) → `O(log N)`. |
| Iterative  | `O(N)`           | `O(N)`             | Every node is visited exactly once → O(N) time. Space is the queue, which in the worst case (last level of a balanced tree) can hold up to `O(N)` nodes. |

`N` = number of nodes in the tree, `H` = height of the tree.

---

## 💻 Code

Both approaches are combined in a single `Solution` class — pick whichever method you want to call.

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
import java.util.LinkedList;
import java.util.Queue;

class Solution {

    // ---------------------------------------------------------
    // Approach 1: Recursive (DFS)
    // Time: O(N) | Space: O(H) -> recursion stack
    // ---------------------------------------------------------
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null || left.val != right.val) {
            return false;
        }
        return isMirror(left.left, right.right)
            && isMirror(left.right, right.left);
    }

    // ---------------------------------------------------------
    // Approach 2: Iterative (BFS using Queue)
    // Time: O(N) | Space: O(N) -> queue
    // ---------------------------------------------------------
    public boolean isSymmetricIterative(TreeNode root) {
        if (root == null) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);

        while (!queue.isEmpty()) {
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();

            if (t1 == null && t2 == null) {
                continue;
            }
            if (t1 == null || t2 == null || t1.val != t2.val) {
                return false;
            }

            queue.add(t1.left);
            queue.add(t2.right);
            queue.add(t1.right);
            queue.add(t2.left);
        }

        return true;
    }
}
```

---

## 🤔 Recursive vs Iterative — Which to use?

| | Recursive | Iterative |
|---|---|---|
| **Readability** | Very clean, mirrors the problem definition directly | Slightly more verbose (manual queue management) |
| **Stack overflow risk** | Possible on very deep/skewed trees (deep recursion) | None — uses heap-allocated queue instead of call stack |
| **Best for** | Balanced or moderately-sized trees (constraint here: ≤1000 nodes, so it's safe) | Very deep/unbalanced trees, or environments with limited stack size |

Given the constraint `N ≤ 1000`, **both approaches work fine** — the recursive one is the more idiomatic and commonly preferred solution for interviews, while the iterative one is good to know as a follow-up / stack-safe alternative.

--