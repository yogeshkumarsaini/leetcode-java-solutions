# Length of Last Word

## Problem Statement

Given a string `s` consisting of words and spaces, return the length of the **last word** in the string.

A word is a maximal substring consisting of non-space characters only.

### Example 1

```text
Input: s = "Hello World"
Output: 5
Explanation: The last word is "World" with length 5.
```

### Example 2

```text
Input: s = "   fly me   to   the moon  "
Output: 4
Explanation: The last word is "moon" with length 4.
```

### Example 3

```text
Input: s = "luffy is still joyboy"
Output: 6
Explanation: The last word is "joyboy" with length 6.
```

---

## Given Solution

```java
class Solution {
    public int lengthOfLastWord(String s) {
        int count = 0;

        for (int i = s.length() - 1; i >= 0; i--) {

            if (s.charAt(i) == ' ') {
                if (count > 0) {
                    break;
                }
            } else {
                count++;
            }
        }

        return count;
    }
}
```

---

# Approach

The best approach is to scan the string **from right to left**.

Why?

Because the problem asks for the **last word**, so the answer is located at the end of the string.

Instead of splitting the string into an array of words, we can directly find the last word by traversing backward.

### Main Idea

1. Start from the last character of the string.
2. Ignore trailing spaces.
3. Once a non-space character is found, start counting characters.
4. Continue moving from right to left.
5. When a space is found after counting at least one character, the last word has ended.
6. Return the count.

This avoids creating an extra array using `split()`.

---

# Algorithm

```text
1. Initialize count = 0.
2. Start a loop from s.length() - 1 to 0.
3. Check the current character:
   - If it is not a space:
       Increase count by 1.
   - If it is a space:
       If count > 0:
           Stop the loop.
       Otherwise:
           Continue skipping spaces.
4. Return count.
```

---

# Step-by-Step Traversal

Consider:

```text
s = "   fly me   to   the moon  "
```

We start from the right side.

```text
"   fly me   to   the moon  "
                         ↑
```

### Step 1: Skip trailing spaces

```text
"   fly me   to   the moon  "
                           ↑
```

Space → ignore it.

```text
"   fly me   to   the moon  "
                          ↑
```

Space → ignore it.

### Step 2: Find the last word

Now we reach:

```text
m
```

It is not a space.

```text
count = 1
```

Move left:

```text
n → count = 2
o → count = 3
o → count = 4
m → count = 4
```

Actually, traversing right-to-left gives:

```text
n → 1
o → 2
o → 3
m → 4
```

Then the next character is a space.

```text
"   fly me   to   the moon  "
                    ↑
```

Since `count > 0`, we stop.

```text
count = 4
```

### Final Answer

```text
4
```

---

# Another Example

```text
s = "Hello World"
```

Traversal:

```text
d → count = 1
l → count = 2
r → count = 3
o → count = 4
W → count = 5
space → stop
```

Result:

```text
5
```

---

# Pattern Used

## Pattern: Reverse Traversal / Right-to-Left Traversal

This solution uses the **Reverse Traversal** pattern.

### Why this pattern?

The question specifically asks for the **last word**.

Therefore, instead of traversing from the beginning and keeping track of every word, we start from the end where the required word is located.

```text
Start
  ↓
[ H ][ e ][ l ][ l ][ o ][ ][ W ][ o ][ r ][ l ][ d ]
                                                    ↑
                                             Start here
```

This makes the solution simple and efficient.

---

# Why Not Use `split()`?

A common solution is:

```java
String[] words = s.trim().split(" ");
return words[words.length - 1].length();
```

But this approach creates an array of words.

For this problem, we only need the **last word**, so creating all words is unnecessary.

The reverse traversal approach:

- Does not create an array
- Uses constant extra space
- Handles trailing spaces naturally
- Stops as soon as the last word is found

Therefore, reverse traversal is a better fit for this problem.

---

# Dry Run

Input:

```text
s = "   fly me   to   the moon  "
```

| Character | Action | Count |
|---|---|---:|
| `' '` | Skip trailing space | 0 |
| `' '` | Skip trailing space | 0 |
| `'n'` | Count character | 1 |
| `'o'` | Count character | 2 |
| `'o'` | Count character | 3 |
| `'m'` | Count character | 4 |
| `' '` | Last word completed → Break | 4 |

Output:

```text
4
```

---

# Complexity Analysis

Let `n` be the length of the string.

## Time Complexity

```text
O(n)
```

In the worst case, we may traverse the entire string.

For example:

```text
"aaaaaaaaaa"
```

or a string with many spaces before the last word.

However, the loop stops as soon as the last word is completely processed and the separating space is found.

## Space Complexity

```text
O(1)
```

Only one integer variable `count` and loop variables are used.

No additional array or string is created.

---

# Complexity Summary

| Complexity | Result |
|---|---|
| Time | `O(n)` |
| Space | `O(1)` |
| Pattern | Reverse Traversal |

---

# Key Takeaway

When a problem asks for something at the **end of a string or array**, consider whether traversing from **right to left** can avoid unnecessary work.

For this problem:

```text
Right → Left
```

is a natural and efficient solution because we only need the last word.

---

## Java Solution

```java
class Solution {
    public int lengthOfLastWord(String s) {
        int count = 0;

        for (int i = s.length() - 1; i >= 0; i--) {

            if (s.charAt(i) == ' ') {
                if (count > 0) {
                    break;
                }
            } else {
                count++;
            }
        }

        return count;
    }
}
```
