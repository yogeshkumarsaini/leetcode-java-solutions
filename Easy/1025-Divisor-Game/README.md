# Divisor Game

## Problem

Alice and Bob take turns playing a game. Alice starts first.

Initially, an integer `n` is written on the chalkboard.

On each player's turn:

* Choose an integer `x` such that:

  * `0 < x < n`
  * `n % x == 0`
* Replace `n` with `n - x`.

If a player cannot make a move, that player loses.

Return `true` if Alice wins when both players play optimally.

---

## Examples

### Example 1

```text
Input: n = 2
Output: true
```

Explanation:

Alice can choose `x = 1`.

```text
n = 2
2 - 1 = 1
```

Now Bob has `n = 1`.

There is no integer `x` satisfying:

```text
0 < x < 1
```

So Bob cannot make a move and loses.

Therefore, Alice wins.

---

### Example 2

```text
Input: n = 3
Output: false
```

Explanation:

For `n = 3`, the only possible divisor smaller than `3` is `1`.

Alice chooses:

```text
3 - 1 = 2
```

Now Bob has `n = 2`.

Bob chooses:

```text
2 - 1 = 1
```

Now Alice cannot make a move.

Therefore, Alice loses.

---

# Approach

The key to solving this problem is to identify the **winning and losing pattern**.

Let's check some small values:

```text
n = 1 → false
n = 2 → true
n = 3 → false
n = 4 → true
n = 5 → false
n = 6 → true
n = 7 → false
n = 8 → true
```

The pattern is:

```text
Odd  → false
Even → true
```

Therefore, we only need to check whether `n` is even or odd.

---

# Why Does the Even/Odd Pattern Work?

## Case 1: n is Odd

Suppose `n` is odd.

Every divisor of an odd number is also odd.

Therefore, the selected `x` will always be odd.

When we subtract:

```text
odd - odd = even
```

So after Alice's move, the number becomes even.

This gives Bob a winning position.

Therefore:

```text
Odd n → Alice loses
```

---

## Case 2: n is Even

Suppose `n` is even.

Alice can always choose:

```text
x = 1
```

because `1` is a divisor of every positive integer.

After subtracting `1`:

```text
even - 1 = odd
```

So Alice can always move the game to an odd number for Bob.

Since an odd number is a losing position, Bob will eventually lose.

Therefore:

```text
Even n → Alice wins
```

---

# Algorithm

The algorithm is very simple.

### Step 1

Check whether `n` is divisible by `2`.

```java
n % 2 == 0
```

### Step 2

If `n` is even, return `true`.

```java
return true;
```

### Step 3

If `n` is odd, return `false`.

```java
return false;
```

---

# Java Solution

```java
class Solution {
    public boolean divisorGame(int n) {
        if (n % 2 != 0) {
            return false;
        }

        return true;
    }
}
```

---

# Shorter Version

The same solution can be written in one line:

```java
class Solution {
    public boolean divisorGame(int n) {
        return n % 2 == 0;
    }
}
```

This works because the answer is:

```text
Even → true
Odd  → false
```

---

# Step-by-Step Traversal

Let's understand the game with `n = 4`.

### Initial State

```text
n = 4
```

Alice's turn.

Possible divisors smaller than `4`:

```text
1, 2
```

Alice can choose `2`.

```text
4 - 2 = 2
```

Now:

```text
n = 2
```

Bob's turn.

Bob can choose:

```text
x = 1
```

So:

```text
2 - 1 = 1
```

Now:

```text
n = 1
```

Alice's turn.

There is no valid `x` because:

```text
0 < x < 1
```

is impossible.

Therefore:

```text
Alice loses this path
```

But Alice should play optimally.

Instead, from `n = 4`, Alice can choose:

```text
x = 1
```

Then:

```text
4 - 1 = 3
```

Now Bob has `n = 3`.

Bob must choose:

```text
3 - 1 = 2
```

Alice then gets:

```text
n = 2
```

Alice chooses:

```text
2 - 1 = 1
```

Bob cannot move.

Therefore:

```text
Alice wins
```

This demonstrates why the **even starting position is winning**.

---

# Pattern Used

## Mathematical / Game Theory Pattern

This solution uses a:

**Mathematical Observation + Game Theory / Winning-Losing State Pattern**

We do not need to simulate every possible move.

Instead, we identify which states are winning and which are losing.

```text
Odd  → Losing State
Even → Winning State
```

Once this pattern is identified, the complete problem becomes an `O(1)` check.

---

# Why Not Use Recursion or Dynamic Programming?

We could try to calculate every possible move using recursion or DP.

For example:

```text
n = 10
  ↓
try all divisors
  ↓
check every resulting state
```

But this is unnecessary.

The mathematical pattern already tells us the answer directly:

```java
n % 2 == 0
```

Therefore, recursion, backtracking, and DP would make the solution more complicated without providing any benefit.

---

# Complexity Analysis

## Time Complexity

```text
O(1)
```

We perform only one modulo operation:

```java
n % 2
```

The input size does not affect the number of operations.

---

## Space Complexity

```text
O(1)
```

No additional array, list, recursion stack, or data structure is used.

Only a constant amount of memory is required.

---

# Final Complexity

| Complexity | Value  |
| ---------- | ------ |
| Time       | `O(1)` |
| Space      | `O(1)` |

---

# Key Takeaway

The most important observation is:

```text
Even n → Alice wins
Odd n  → Alice loses
```

Therefore, the entire problem can be solved with:

```java
return n % 2 == 0;
```

This is a good example of recognizing a **mathematical pattern** instead of simulating the complete game.


