# Isomorphic Strings

## Problem

Given two strings `s` and `t`, determine if they are **isomorphic**.

Two strings are isomorphic if the characters in `s` can be replaced to get `t`.

The following conditions must be satisfied:

- Every character in `s` must always map to the same character in `t`.
- Two different characters in `s` cannot map to the same character in `t`.
- A character can map to itself.
- The order of characters must remain unchanged.

### Example 1

```text
Input:
s = "egg"
t = "add"

Output:
true
```

Explanation:

```text
e → a
g → d
```

So:

```text
egg
 ↓↓↓
add
```

---

### Example 2

```text
Input:
s = "f11"
t = "b23"

Output:
false
```

Explanation:

The character `1` appears twice in `s`.

But it needs to map to:

```text
1 → 2
1 → 3
```

A single character cannot map to two different characters.

Therefore, the answer is `false`.

---

### Example 3

```text
Input:
s = "paper"
t = "title"

Output:
true
```

Mapping:

```text
p → t
a → i
p → t
e → l
r → e
```

Therefore, the strings are isomorphic.

---

## Approach

We use **two arrays** to maintain a mapping in both directions.

```java
int[] mapST = new int[256];
int[] mapTS = new int[256];
```

### 1. `mapST`

This array stores the mapping:

```text
character from s → character from t
```

For example:

```text
e → a
g → d
```

### 2. `mapTS`

This array stores the reverse mapping:

```text
character from t → character from s
```

For example:

```text
a → e
d → g
```

We need both mappings because the problem says:

> No two characters may map to the same character.

For example:

```text
s = "ab"
t = "cc"
```

If we only check `s → t`:

```text
a → c
b → c
```

It may look valid.

But it is actually invalid because two different characters (`a` and `b`) are mapping to the same character (`c`).

The reverse mapping prevents this.

---

## Java Solution

```java
class Solution {
    public boolean isIsomorphic(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        int[] mapST = new int[256];
        int[] mapTS = new int[256];

        for (int i = 0; i < s.length(); i++) {

            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            // Check s -> t mapping
            if (mapST[c1] != 0 && mapST[c1] != c2) {
                return false;
            }

            // Check t -> s mapping
            if (mapTS[c2] != 0 && mapTS[c2] != c1) {
                return false;
            }

            // Store both mappings
            mapST[c1] = c2;
            mapTS[c2] = c1;
        }

        return true;
    }
}
```

---

# Algorithm

### Step 1: Check Length

First check whether both strings have the same length.

```java
if (s.length() != t.length()) {
    return false;
}
```

If lengths are different, they cannot be isomorphic.

---

### Step 2: Create Two Mapping Arrays

```java
int[] mapST = new int[256];
int[] mapTS = new int[256];
```

Because the strings contain valid ASCII characters, an array of size `256` is enough.

---

### Step 3: Traverse Both Strings

Use one loop to process both strings at the same index.

```java
for (int i = 0; i < s.length(); i++)
```

At every index:

```java
char c1 = s.charAt(i);
char c2 = t.charAt(i);
```

---

### Step 4: Check `s → t`

```java
if (mapST[c1] != 0 && mapST[c1] != c2) {
    return false;
}
```

This checks whether the same character from `s` is trying to map to different characters in `t`.

Example:

```text
s = "f11"
t = "b23"
```

At first:

```text
1 → 2
```

Later:

```text
1 → 3
```

This is not allowed, so return `false`.

---

### Step 5: Check `t → s`

```java
if (mapTS[c2] != 0 && mapTS[c2] != c1) {
    return false;
}
```

This prevents two different characters from `s` mapping to the same character in `t`.

Example:

```text
s = "ab"
t = "cc"
```

We get:

```text
a → c
b → c
```

The reverse mapping detects that `c` was already mapped to `a`.

Therefore:

```text
false
```

---

### Step 6: Store the Mapping

After both checks pass:

```java
mapST[c1] = c2;
mapTS[c2] = c1;
```

Both directions are stored.

---

### Step 7: Return `true`

If the entire string is processed without finding any invalid mapping:

```java
return true;
```

The strings are isomorphic.

---

# Step-by-Step Traversal

Consider:

```text
s = "paper"
t = "title"
```

| Index | `s` | `t` | Mapping |
|------:|-----|-----|---------|
| 0 | p | t | p → t |
| 1 | a | i | a → i |
| 2 | p | t | p → t (already valid) |
| 3 | e | l | e → l |
| 4 | r | e | r → e |

All mappings are consistent.

Therefore:

```text
true
```

---

# Pattern Used

## Hashing / Character Mapping Pattern

The main pattern used is:

```text
Hashing / Direct Addressing / Bidirectional Mapping
```

We use arrays as a simple hash table for ASCII characters.

```java
int[] mapST = new int[256];
int[] mapTS = new int[256];
```

### Why this pattern?

We need to quickly answer two questions:

1. Has this character from `s` already been mapped?
2. Has this character from `t` already been mapped?

An array provides **O(1)** lookup.

Instead of searching through the string every time, we directly access:

```java
mapST[c1]
mapTS[c2]
```

This makes the solution efficient.

---

# Why Two Arrays?

This is the most important part of the solution.

Suppose:

```text
s = "ab"
t = "cc"
```

With only one mapping:

```text
a → c
b → c
```

We might incorrectly accept the strings.

But the problem says:

> No two characters may map to the same character.

Therefore we also maintain:

```text
c → a
```

When `b → c` is attempted, we find:

```text
c → a
```

already exists.

So the mapping is invalid.

That's why we need:

```java
mapST
```

and

```java
mapTS
```

---

# Complexity Analysis

Let `n` be the length of the string.

## Time Complexity

```text
O(n)
```

We traverse the strings only once.

For every character, we perform constant-time array operations:

```text
Lookup → O(1)
Check  → O(1)
Update → O(1)
```

Therefore:

```text
O(n)
```

---

## Space Complexity

```text
O(1)
```

We use two arrays of fixed size `256`:

```java
int[] mapST = new int[256];
int[] mapTS = new int[256];
```

Since `256` is constant and does not depend on `n`:

```text
O(256) = O(1)
```

Therefore, the auxiliary space complexity is:

```text
O(1)
```

---

# Final Complexity

| Complexity | Result |
|---|---|
| Time | **O(n)** |
| Space | **O(1)** |

---

# Key Takeaway

For isomorphic strings, always remember:

```text
One character → One character
One character cannot → Multiple characters
Multiple characters cannot → Same character
```

Therefore, we maintain **bidirectional mapping**:

```text
s → t
t → s
```

This gives an efficient:

```text
O(n) Time
O(1) Space
```

solution.