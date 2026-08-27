# Distribute Candies

## Problem

We have `candies` candies and `num_people` people standing in a row.

Candies are distributed in increasing order:

* 1 candy to the first person
* 2 candies to the second person
* 3 candies to the third person
* ...
* `num_people` candies to the last person

After reaching the last person, we start again from the first person and continue increasing the number of candies.

For example, with `num_people = 4`:

```text
1 → Person 0
2 → Person 1
3 → Person 2
4 → Person 3

5 → Person 0
6 → Person 1
7 → Person 2
8 → Person 3
...
```

If the remaining candies are less than the next required amount, the last person receives all remaining candies.

---

## Example

### Input

```text
candies = 7
num_people = 4
```

### Output

```text
[1, 2, 3, 1]
```

### Explanation

Initially:

```text
ans = [0, 0, 0, 0]
```

#### Step 1

Give `1` candy to Person `0`.

```text
ans = [1, 0, 0, 0]
remaining = 6
```

#### Step 2

Give `2` candies to Person `1`.

```text
ans = [1, 2, 0, 0]
remaining = 4
```

#### Step 3

Give `3` candies to Person `2`.

```text
ans = [1, 2, 3, 0]
remaining = 1
```

#### Step 4

The next required amount is `4`, but only `1` candy remains.

So we give:

```text
min(4, 1) = 1
```

to Person `3`.

```text
ans = [1, 2, 3, 1]
remaining = 0
```

Final answer:

```text
[1, 2, 3, 1]
```

---

# Approach

The simplest and safest approach is to simulate the distribution process.

We maintain three variables:

```text
person
give
candies
```

### `person`

Stores which person should receive candies next.

```java
int person = 0;
```

After every distribution:

```java
person++;
```

When we reach `num_people`, we go back to person `0`.

```java
if (person == num_people) {
    person = 0;
}
```

### `give`

Stores the number of candies we want to give in the current step.

It starts from `1`:

```java
int give = 1;
```

After every distribution:

```java
give++;
```

So the sequence becomes:

```text
1, 2, 3, 4, 5, 6, ...
```

### Remaining candies

Before distributing, we must make sure we don't give more candies than we have.

Therefore:

```java
int current = Math.min(give, candies);
```

This handles the final partial distribution automatically.

---

# Algorithm

1. Create an answer array of size `num_people`.
2. Set `person = 0`.
3. Set `give = 1`.
4. Repeat while candies are available:

   * Calculate the candies to distribute:

     ```text
     current = min(give, candies)
     ```
   * Add `current` candies to the current person.
   * Subtract `current` from the remaining candies.
   * Increase `give` by `1`.
   * Move to the next person.
   * If the last person is reached, move back to person `0`.
5. Return the answer array.

---

# Step-by-Step Traversal

Consider:

```text
candies = 10
num_people = 3
```

Initial state:

```text
ans = [0, 0, 0]
person = 0
give = 1
candies = 10
```

### Iteration 1

```text
current = min(1, 10)
       = 1
```

Person `0` gets `1`.

```text
ans = [1, 0, 0]
candies = 9
give = 2
person = 1
```

---

### Iteration 2

```text
current = min(2, 9)
       = 2
```

Person `1` gets `2`.

```text
ans = [1, 2, 0]
candies = 7
give = 3
person = 2
```

---

### Iteration 3

```text
current = min(3, 7)
       = 3
```

Person `2` gets `3`.

```text
ans = [1, 2, 3]
candies = 4
give = 4
person = 0
```

The last person was reached, so we start again from person `0`.

---

### Iteration 4

```text
current = min(4, 4)
       = 4
```

Person `0` gets `4`.

```text
ans = [5, 2, 3]
candies = 0
```

Final result:

```text
[5, 2, 3]
```

---

# Why `Math.min()` Is Used

The important part of the solution is:

```java
int current = Math.min(give, candies);
```

Suppose:

```text
candies = 2
give = 5
```

We cannot give `5` candies because only `2` are available.

So:

```text
Math.min(5, 2) = 2
```

The remaining `2` candies are given to the current person and the process ends.

This eliminates the need for a separate condition for the final distribution.

---

# Pattern Used

## Pattern: Simulation

This problem uses the **Simulation Pattern**.

Simulation means directly following the operations described in the problem statement.

Here, the problem gives a clear sequence:

```text
1 → 2 → 3 → 4 → 5 → ...
```

and people follow a circular pattern:

```text
0 → 1 → 2 → ... → num_people - 1 → 0 → 1 → ...
```

Instead of trying to derive a complicated mathematical formula, we simulate exactly what happens.

---

# Why Simulation Is Suitable

Simulation is a good choice here because:

1. The distribution order is straightforward.
2. Each operation affects only one person.
3. The person index follows a simple circular pattern.
4. The amount given increases by exactly `1`.
5. `Math.min()` handles the final incomplete distribution.
6. The constraints are small enough that direct simulation is efficient.

The maximum number of candies is:

```text
10^9
```

and the number of people is at most:

```text
1000
```

Even though the candy count can be large, the number of distribution operations is approximately proportional to the square root of the number of candies, because the amount distributed increases as:

```text
1 + 2 + 3 + ... + k
```

and this sum grows as `k²`.

---

# Java Solution

```java
class Solution {
    public int[] distributeCandies(int candies, int num_people) {

        int[] ans = new int[num_people];

        int person = 0;
        int give = 1;

        while (candies > 0) {

            int current = Math.min(give, candies);

            ans[person] += current;

            candies -= current;

            give++;
            person++;

            if (person == num_people) {
                person = 0;
            }
        }

        return ans;
    }
}
```

---

# Code Explanation

### 1. Create result array

```java
int[] ans = new int[num_people];
```

This stores the final candies received by every person.

For example:

```text
num_people = 4

ans = [0, 0, 0, 0]
```

---

### 2. Initialize person

```java
int person = 0;
```

We start from the first person.

---

### 3. Initialize candies to give

```java
int give = 1;
```

The first distribution always starts with `1` candy.

---

### 4. Continue while candies remain

```java
while (candies > 0)
```

The loop continues until all candies are distributed.

---

### 5. Calculate current distribution

```java
int current = Math.min(give, candies);
```

This ensures we never distribute more candies than are available.

---

### 6. Add candies to the person

```java
ans[person] += current;
```

The current person receives the calculated number of candies.

---

### 7. Reduce remaining candies

```java
candies -= current;
```

Remove the distributed candies from the total.

---

### 8. Increase the next distribution

```java
give++;
```

If the current distribution was:

```text
1
```

the next one becomes:

```text
2
```

then:

```text
3
4
5
...
```

---

### 9. Move to next person

```java
person++;
```

---

### 10. Restart from first person

```java
if (person == num_people) {
    person = 0;
}
```

This makes the people behave like a circular array.

For example, with `4` people:

```text
0 → 1 → 2 → 3 → 0 → 1 → 2 → 3 → ...
```

---

# Complexity Analysis

Let `k` be the number of distribution operations.

Since the amount given increases by `1` each time:

```text
1 + 2 + 3 + ... + k <= candies
```

Using the arithmetic series formula:

```text
k(k + 1) / 2 <= candies
```

Therefore:

```text
k = O(√candies)
```

### Time Complexity

```text
O(√candies)
```

For the given constraint:

```text
candies <= 10^9
```

the number of iterations is roughly at most around:

```text
44,721
```

So the simulation is efficient.

### Space Complexity

The result array contains `num_people` elements:

```text
O(num_people)
```

Other variables use constant space:

```text
O(1)
```

Therefore the total auxiliary/result space is:

```text
O(num_people)
```

If only auxiliary space excluding the returned result array is considered:

```text
O(1)
```

---

# Complexity Summary

| Complexity      | Value           |
| --------------- | --------------- |
| Time            | `O(√candies)`   |
| Space           | `O(num_people)` |
| Auxiliary Space | `O(1)`          |
| Pattern         | Simulation      |

---

# Key Takeaways

* Use **Simulation** because the distribution process is sequential and easy to reproduce.
* `person` tracks the current person.
* `give` tracks the next number of candies to distribute.
* `person = 0` after reaching `num_people` creates circular traversal.
* `Math.min(give, candies)` handles the final incomplete distribution.
* The algorithm runs in `O(√candies)` time.
* The returned result requires `O(num_people)` space.

---

## Example 2

### Input

```text
candies = 10
num_people = 3
```

### Distribution

```text
Person 0 → 1
Person 1 → 2
Person 2 → 3
Person 0 → 4
```

Final:

```text
Person 0 = 1 + 4 = 5
Person 1 = 2
Person 2 = 3
```

### Output

```text
[5, 2, 3]
```

---

## Conclusion

The solution directly simulates the candy distribution process using three simple variables:

```text
person
give
candies
```

The combination of **circular indexing** and **incremental distribution** makes the implementation simple, readable, and efficient.

The key line:

```java
int current = Math.min(give, candies);
```

ensures that the last person receives exactly the remaining candies when fewer candies are available than the required distribution.
