# 412. Fizz Buzz

## Problem Statement

Given an integer `n`, return a string array `answer` (1-indexed) where:

- `answer[i] == "FizzBuzz"` if `i` is divisible by both `3` and `5`.
- `answer[i] == "Fizz"` if `i` is divisible by `3`.
- `answer[i] == "Buzz"` if `i` is divisible by `5`.
- `answer[i] == i` (as a string) if none of the above conditions are true.

---

## Examples

### Example 1

**Input**

```text
n = 3
```

**Output**

```text
["1","2","Fizz"]
```

---

### Example 2

**Input**

```text
n = 5
```

**Output**

```text
["1","2","Fizz","4","Buzz"]
```

---

### Example 3

**Input**

```text
n = 15
```

**Output**

```text
["1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","14","FizzBuzz"]
```

---

## Java Solution

```java
class Solution {

    public List<String> fizzBuzz(int n) {

        List<String> list = new ArrayList<>();

        for (int i = 1; i <= n; i++) {

            if (i % 3 == 0 && i % 5 == 0) {
                list.add("FizzBuzz");
            }

            else if (i % 3 == 0) {
                list.add("Fizz");
            }

            else if (i % 5 == 0) {
                list.add("Buzz");
            }

            else {
                list.add(String.valueOf(i));
            }
        }

        return list;
    }
}
```

---

# Approach

We traverse all numbers from `1` to `n`.

For every number:

1. Check whether it is divisible by both `3` and `5`.
2. If yes, add `"FizzBuzz"` to the list.
3. Otherwise, check whether it is divisible by `3`.
4. If yes, add `"Fizz"`.
5. Otherwise, check whether it is divisible by `5`.
6. If yes, add `"Buzz"`.
7. Otherwise, convert the number into a string and add it to the list.

Finally, return the list.

---

# Step-by-Step Traversal

Suppose:

```text
n = 15
```

| Number | Condition | Output |
|--------|--------|--------|
| 1 | None | "1" |
| 2 | None | "2" |
| 3 | Divisible by 3 | "Fizz" |
| 4 | None | "4" |
| 5 | Divisible by 5 | "Buzz" |
| 6 | Divisible by 3 | "Fizz" |
| 7 | None | "7" |
| 8 | None | "8" |
| 9 | Divisible by 3 | "Fizz" |
| 10 | Divisible by 5 | "Buzz" |
| 11 | None | "11" |
| 12 | Divisible by 3 | "Fizz" |
| 13 | None | "13" |
| 14 | None | "14" |
| 15 | Divisible by 3 and 5 | "FizzBuzz" |

Final Result:

```text
["1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","14","FizzBuzz"]
```

---

# Algorithm

```text
1. Create an empty list.

2. Loop from i = 1 to n.

3. If i is divisible by both 3 and 5:
       add "FizzBuzz"

4. Else if i is divisible by 3:
       add "Fizz"

5. Else if i is divisible by 5:
       add "Buzz"

6. Else:
       convert i to string and add it

7. Return the list.
```

---

# Pattern Used

## Pattern Name

**Simulation Pattern**

---

## Why Simulation?

We simply simulate the rules given in the problem statement:

- Check divisibility by 3.
- Check divisibility by 5.
- Generate output accordingly.

There is no recursion, dynamic programming, stack, queue, or hashing involved.

Therefore, this problem follows the **Simulation Pattern**.

---

# Complexity Analysis

## Time Complexity

```text
O(n)
```

Reason:

- We traverse from `1` to `n` exactly once.

---

## Space Complexity

```text
O(n)
```

Reason:

- We store `n` strings in the answer list.

---

# Dry Run

```text
n = 5
```

Initial:

```text
list = []
```

### i = 1

```text
list = ["1"]
```

### i = 2

```text
list = ["1", "2"]
```

### i = 3

```text
list = ["1", "2", "Fizz"]
```

### i = 4

```text
list = ["1", "2", "Fizz", "4"]
```

### i = 5

```text
list = ["1", "2", "Fizz", "4", "Buzz"]
```

Final Output:

```text
["1","2","Fizz","4","Buzz"]
```

---

# Key Points

✅ Single traversal

✅ Easy implementation

✅ Uses modulo operator

✅ Simulation pattern

✅ Linear time complexity

✅ Suitable for beginners

---

## Constraints

```text
1 <= n <= 10^4
```