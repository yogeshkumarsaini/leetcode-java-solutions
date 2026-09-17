# Valid Palindrome

## Problem

A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward.

Alphanumeric characters include:

- Letters (`a-z`, `A-Z`)
- Numbers (`0-9`)

### Example 1

```text
Input:  s = "A man, a plan, a canal: Panama"
Output: true

Explanation:
"amanaplanacanalpanama" is a palindrome.
```

### Example 2

```text
Input:  s = "race a car"
Output: false

Explanation:
"raceacar" is not a palindrome.
```

### Example 3

```text
Input:  s = " "
Output: true

Explanation:
After removing non-alphanumeric characters, the string becomes empty.
An empty string is considered a palindrome.
```

---

## Approach

We use the **Two Pointer** pattern.

Instead of creating a new string after removing spaces and special characters, we compare characters directly from the original string.

We maintain two pointers:

- `left` → starts from the beginning
- `right` → starts from the end

For every comparison:

1. Skip characters from the left that are not alphanumeric.
2. Skip characters from the right that are not alphanumeric.
3. Convert both characters to lowercase.
4. Compare them.
5. If they are different, return `false`.
6. Otherwise, move both pointers toward the center.

If all valid characters match, return `true`.

---

## Why Two Pointers?

A palindrome reads the same from both directions.

For example:

```text
racecar
^^^^^^^
```

We can compare:

```text
r == r
a == a
c == c
e == e
```

There is no need to check the complete string against its reverse.

The two-pointer technique allows us to compare the corresponding characters directly.

---

## Algorithm

```text
1. Set left = 0.
2. Set right = s.length() - 1.
3. While left < right:
   a. Move left forward while s[left] is not alphanumeric.
   b. Move right backward while s[right] is not alphanumeric.
   c. Convert both characters to lowercase.
   d. If the characters are different:
      return false.
   e. Move left forward.
   f. Move right backward.
4. If all characters match, return true.
```

---

## Step-by-Step Traversal

Consider:

```text
s = "A man, a plan, a canal: Panama"
```

After ignoring non-alphanumeric characters, we compare:

```text
A  <----------------->  a
m  <----------------->  m
a  <----------------->  a
n  <----------------->  n
a  <----------------->  a
p  <----------------->  p
...
```

### Step 1

```text
left  = 'A'
right = 'a'
```

Convert to lowercase:

```text
'a' == 'a'
```

Match → move both pointers.

### Step 2

```text
left  = 'm'
right = 'm'
```

Match → move both pointers.

### Step 3

```text
left  = 'a'
right = 'a'
```

Match → move both pointers.

### Special Characters

If a pointer reaches a character such as:

```text
' '
','
':'
'!'
```

it skips that character because it is not alphanumeric.

For example:

```text
"A man"
  ^
  left
```

The space is skipped and the pointer moves to the next valid character.

### Final Result

All valid characters match:

```text
A man, a plan, a canal: Panama
```

Therefore:

```text
true
```

---

## Java Solution

```java
class Solution {
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {

            // Skip non-alphanumeric characters from left
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }

            // Skip non-alphanumeric characters from right
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            // Compare characters ignoring case
            if (Character.toLowerCase(s.charAt(left)) !=
                Character.toLowerCase(s.charAt(right))) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}
```

---

## Pattern Used

### Two Pointers

**Pattern:** Two Pointer

We use two pointers because we need to compare characters from opposite ends of the string.

```text
left →                    ← right
 A  m  a  n  a  p  l  a  n  a
```

Both pointers move toward the center.

### Why this pattern?

A palindrome has a natural left-to-right and right-to-left comparison.

Two pointers help us:

- Compare both ends simultaneously.
- Avoid reversing the string.
- Avoid creating an additional cleaned string.
- Skip unwanted characters efficiently.
- Keep the solution simple and memory efficient.

---

## Complexity Analysis

### Time Complexity

**O(n)**

Each character is visited at most a constant number of times by the pointers.

Even though there are nested `while` loops for skipping characters, the pointers only move forward/backward and never move back.

Therefore, total traversal is:

```text
O(n)
```

where `n` is the length of the string.

### Space Complexity

**O(1)**

We only use:

```java
int left
int right
```

No additional string, array, or collection is created.

Therefore:

```text
Space = O(1)
```

---

## Complexity Summary

| Complexity | Result |
|---|---|
| Time | **O(n)** |
| Space | **O(1)** |

---

## Key Learning

The important idea is that we **do not need to clean the string first**.

Instead of:

```text
Original String
      ↓
Remove special characters
      ↓
Convert to lowercase
      ↓
Create new String
      ↓
Check palindrome
```

We directly process the original string:

```text
Original String
      ↓
Two Pointers
      ↓
Skip invalid characters
      ↓
Compare lowercase characters
      ↓
Palindrome
```

This gives an efficient **O(n) time and O(1) space** solution.

---


## Pattern

**Two Pointers**
