# 20. Valid Parentheses

## Problem Statement

Given a string `s` containing only the characters `'('`, `')'`, `'{'`, `'}'`, `'['`, and `']'`, determine whether the input string is **valid**.

A string is valid if:

- Every opening bracket has a corresponding closing bracket of the same type.
- Brackets are closed in the correct order.
- Every closing bracket matches the most recent unmatched opening bracket.

---

## Examples

### Example 1

**Input**

```text
s = "()"
```

**Output**

```text
true
```

---

### Example 2

**Input**

```text
s = "()[]{}"
```

**Output**

```text
true
```

---

### Example 3

**Input**

```text
s = "(]"
```

**Output**

```text
false
```

---

### Example 4

**Input**

```text
s = "([])"
```

**Output**

```text
true
```

---

### Example 5

**Input**

```text
s = "([)]"
```

**Output**

```text
false
```

---

# Approach

The idea is to use a **Stack** because brackets follow the **Last In, First Out (LIFO)** principle.

- Whenever an opening bracket appears, push it onto the stack.
- Whenever a closing bracket appears:
  - If the stack is empty, the string is invalid.
  - Otherwise, pop the top element.
  - Check whether the popped opening bracket matches the current closing bracket.
- After processing all characters:
  - If the stack is empty, every opening bracket has been matched.
  - Otherwise, the string is invalid.

This guarantees that brackets are closed in the correct order.

---

# Why Stack?

A Stack always removes the **most recently inserted** element first.

Example:

```text
Input: "([])"

Push '('
Stack: (

Push '['
Stack: ( [

Found ']'
Pop '['
Stack: (

Found ')'
Pop '('
Stack: Empty

Valid
```

If we used a Queue (FIFO), matching would fail because the oldest bracket would be removed first instead of the latest one.

---

# Algorithm

1. Create an empty stack.
2. Traverse every character in the string.
3. If the character is an opening bracket:
   - Push it into the stack.
4. Otherwise, it is a closing bracket.
   - If the stack is empty, return `false`.
   - Pop the top element.
   - Compare whether it matches the current closing bracket.
   - If it doesn't match, return `false`.
5. After traversal finishes:
   - If the stack is empty, return `true`.
   - Otherwise, return `false`.

---

# Step-by-Step Traversal

## Example

```text
s = "([])"
```

| Step | Character | Stack Before | Operation | Stack After |
|------|-----------|--------------|-----------|-------------|
| 1 | ( | Empty | Push | ( |
| 2 | [ | ( | Push | ( [ |
| 3 | ] | ( [ | Pop [ | ( |
| 4 | ) | ( | Pop ( | Empty |

Stack becomes empty.

Return:

```text
true
```

---

## Another Example

```text
s = "([)]"
```

| Step | Character | Stack | Action |
|------|-----------|-------|--------|
|1|(|Push|(|
|2|[|Push|([|
|3|)|Pop '[' → Doesn't match '('|Return false|

Since `)` cannot match `[`, the string is invalid.

---

# Dry Run

Input

```text
s = "()[]{}"
```

```
Stack = []

'('
Push

Stack = [(]

')'
Pop '('

Stack = []

'['
Push

Stack = [[ ]

']'
Pop '['

Stack = []

'{'
Push

Stack = [{]

'}'
Pop '{'

Stack = []

Finished

Stack Empty

Answer = true
```

---

# Correctness Proof

### Case 1: Opening Bracket

Every opening bracket is stored in the stack.

### Case 2: Closing Bracket

The latest unmatched opening bracket is removed and checked.

If it doesn't match, the string is invalid.

### Case 3: End of Traversal

If the stack is empty, every opening bracket has found its matching closing bracket.

Hence the algorithm correctly validates the parentheses.

---

# Complexity Analysis

## Time Complexity

Each character is processed exactly once.

Each bracket is pushed at most once and popped at most once.

```
Time Complexity = O(n)
```

where **n** is the length of the string.

---

## Space Complexity

In the worst case:

```text
((((((((
```

All opening brackets are stored in the stack.

```
Space Complexity = O(n)
```

---

# Pattern Used

## Stack Pattern (LIFO)

This problem belongs to the **Stack** pattern.

### Why?

Because:

- We always need the **most recent unmatched opening bracket**.
- Stack provides:
  - Push → O(1)
  - Pop → O(1)
  - Peek → O(1)

This makes Stack the optimal data structure.

---

# Java Solution

```java
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else {

                if (stack.isEmpty())
                    return false;

                char top = stack.pop();

                if ((ch == ')' && top != '(') ||
                    (ch == '}' && top != '{') ||
                    (ch == ']' && top != '['))
                    return false;
            }
        }

        return stack.isEmpty();
    }
}
```

---

# Key Takeaways

- ✔ Uses the **Stack (LIFO)** pattern.
- ✔ Push every opening bracket.
- ✔ Pop and verify every closing bracket.
- ✔ Return `false` immediately on mismatch.
- ✔ Stack must be empty at the end.
- ✔ Optimal solution with **O(n)** time and **O(n)** space complexity.
