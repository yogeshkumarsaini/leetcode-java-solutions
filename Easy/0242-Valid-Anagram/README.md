# 242. Valid Anagram

## 📌 Problem Statement

Given two strings `s` and `t`, return `true` if `t` is an anagram of `s`, and `false` otherwise.

An **anagram** is a word or string formed by rearranging the characters of another string, using all the original characters exactly the same number of times.

### Example 1

**Input:**
```text
s = "anagram", t = "nagaram"
```

**Output:**
```text
true
```

**Explanation:** Both strings contain the exact same characters with the same frequency.

### Example 2

**Input:**
```text
s = "rat", t = "car"
```

**Output:**
```text
false
```

**Explanation:** The strings contain different characters. `r` and `a` are common, but `t` and `c` are different.

### Constraints

- `1 <= s.length, t.length <= 5 * 10^4`
- `s` and `t` consist of lowercase English letters.

---

## 💡 Approach: Character Frequency Counting

We can solve this problem using a **Frequency Counting** technique with an integer array of size `26`.

Since the strings contain only lowercase English letters (`a` to `z`), we can use an array of length `26` to count the frequency of each character.

### How it works

1. First, check whether the lengths of both strings are equal. If they are not equal, return `false`.
2. Create an integer array `count` of size `26`.
3. Traverse both strings simultaneously.
4. Increase the count for each character in `s`.
5. Decrease the count for each corresponding character in `t`.
6. If all array values are `0`, both strings contain exactly the same characters.
7. Return `true` if all counts match; otherwise, return `false`.

---

## 💻 Java Solution

```java
class Solution {
    public boolean isAnagram(String s, String t) {

        // Check if both strings have the same length
        if (s.length() != t.length()) {
            return false;
        }

        // Array to store character frequencies
        int[] count = new int[26];

        // Count characters from s and subtract characters from t
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }

        // Check if all frequencies are zero
        for (int num : count) {
            if (num != 0) {
                return false;
            }
        }

        return true;
    }
}
```

---

## 🔍 Algorithm

1. Start the function `isAnagram(s, t)`.
2. Compare the lengths of `s` and `t`.
3. If the lengths are different, return `false`.
4. Initialize an integer array `count[26]` with all values set to `0`.
5. Loop through the strings using index `i`.
6. Increment the frequency of the character in `s` using `count[s.charAt(i) - 'a']++`.
7. Decrement the frequency of the character in `t` using `count[t.charAt(i) - 'a']--`.
8. After the loop, traverse the `count` array.
9. If any value is not `0`, return `false`.
10. If every value is `0`, return `true`.

---

## 🚶 Step-by-Step Traversal

### Example 1

```text
s = "anagram"
t = "nagaram"
```

Both strings have length `7`, so we continue.

Initially:

```text
count[26] = {0, 0, 0, ..., 0}
```

We process both strings character by character.

| Index | `s.charAt(i)` | `t.charAt(i)` | Operation | Result |
|---|---|---|---|---|
| 0 | `a` | `n` | `a +1`, `n -1` | `a=1, n=-1` |
| 1 | `n` | `a` | `n +1`, `a -1` | `a=0, n=0` |
| 2 | `a` | `g` | `a +1`, `g -1` | `a=1, g=-1` |
| 3 | `g` | `a` | `g +1`, `a -1` | `a=0, g=0` |
| 4 | `r` | `r` | `r +1`, `r -1` | `r=0` |
| 5 | `a` | `a` | `a +1`, `a -1` | `a=0` |
| 6 | `m` | `m` | `m +1`, `m -1` | `m=0` |

### Final Frequency Array

All character frequencies are `0`.

```text
a = 0
g = 0
m = 0
n = 0
r = 0
```

Therefore:

```text
Output: true
```

### Example 2

```text
s = "rat"
t = "car"
```

Both strings have length `3`.

| Index | `s.charAt(i)` | `t.charAt(i)` | Operation |
|---|---|---|---|
| 0 | `r` | `c` | `r +1`, `c -1` |
| 1 | `a` | `a` | `a +1`, `a -1` |
| 2 | `t` | `r` | `t +1`, `r -1` |

### Final Frequency Array (Relevant Characters)

| Character | Frequency |
|---|---:|
| `a` | 0 |
| `c` | -1 |
| `r` | 0 |
| `t` | +1 |

Since `c` and `t` have non-zero frequencies, the strings are not anagrams.

```text
Output: false
```

---

## 🧠 Pattern Used

### Pattern: Frequency Counting / Hashing

**Why did we use this pattern?**

- We need to compare the number of occurrences of each character in both strings.
- Character frequency helps us avoid sorting the strings.
- An integer array of size `26` is sufficient because the input contains lowercase English letters only.
- We increment the frequency for string `s` and decrement the frequency for string `t`.
- If all frequencies become `0`, both strings contain the same characters with identical frequencies.

### Pattern Recognition

Use **Frequency Counting** when:

- You need to compare character occurrences.
- You need to check whether two strings contain the same characters.
- You need to find duplicate or missing elements based on frequency.
- You need to count occurrences of elements in a limited range.

**Related LeetCode Problems:**

- Group Anagrams
- Find All Anagrams in a String
- Ransom Note
- Permutation in String
- First Unique Character in a String

---

## ⏱️ Complexity Analysis

Let `n` be the length of the strings and `26` be the fixed size of the frequency array.

### Time Complexity

**O(n)**

- The length comparison takes `O(1)`.
- The main loop traverses the string once: `O(n)`.
- The frequency array check takes `O(26)`, which is constant.

Therefore:

```text
O(n + 26) = O(n)
```

### Space Complexity

**O(1)**

- We use an integer array of size `26`.
- The array size remains constant regardless of the input length.

Therefore:

```text
O(26) = O(1)
```

---

## 🔄 Follow-Up: What if the inputs contain Unicode characters?

If strings contain Unicode characters, we cannot rely on a fixed array of size `26`.

We can use a `HashMap` to store character frequencies instead.

For Java, we can iterate over Unicode code points using `codePoints()` instead of `charAt()`, because some Unicode characters require two UTF-16 code units.

A HashMap-based approach can count the code points of one string and subtract the code points of the other, then verify that all counts are zero.

---

## 🎯 Key Takeaway

- **Pattern:** Frequency Counting
- **Data Structure:** Integer Array
- **Time Complexity:** `O(n)`
- **Space Complexity:** `O(1)`
- **Optimization:** Avoid sorting by counting characters directly.

Frequency counting is one of the most useful patterns for solving string and array problems efficiently.
