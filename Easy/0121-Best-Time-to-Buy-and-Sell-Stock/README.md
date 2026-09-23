# Best Time to Buy and Sell Stock

## Problem

You are given an array `prices` where `prices[i]` represents the price of a stock on the `ith` day.

You need to choose:
- One day to **buy** the stock.
- A different day in the **future** to **sell** the stock.

The goal is to maximize the profit.

If no profit can be made, return `0`.

---

## Example 1

### Input

```text
prices = [7,1,5,3,6,4]
```

### Output

```text
5
```

### Explanation

Buy on day 2 at price `1` and sell on day 5 at price `6`.

```text
Profit = 6 - 1 = 5
```

We cannot buy at day 5 and sell at day 2 because selling must happen after buying.

---

## Example 2

### Input

```text
prices = [7,6,4,3,1]
```

### Output

```text
0
```

### Explanation

The stock price continuously decreases, so no profitable transaction is possible.

Therefore, the maximum profit is `0`.

---

# Approach

We can solve this problem using a **Greedy approach** with a single traversal of the array.

The main idea is:

1. Keep track of the **minimum price** seen so far.
2. For every current price, calculate the profit if we sell today.
3. Keep track of the **maximum profit** found so far.
4. If the current price is smaller than the minimum price, update the minimum price.

We don't need to check every possible buy/sell pair.

---

# Why Greedy?

For every day, we want to know:

> "If I sell today, what is the maximum profit I can get?"

To maximize today's profit, we should have bought at the **lowest price before today**.

So we maintain:

```text
minPrice = lowest price seen so far
```

Then:

```text
profit = currentPrice - minPrice
```

And update:

```text
maxProfit = maximum profit found so far
```

This greedy choice works because for any selling day, the best possible buying price is simply the minimum price encountered before that day.

---

# Algorithm

1. Initialize `minPrice` with the first day's price.
2. Initialize `maxProfit = 0`.
3. Traverse the array from index `1`.
4. For every price:
   - If the current price is smaller than `minPrice`, update `minPrice`.
   - Otherwise, calculate the current profit:
     ```text
     profit = currentPrice - minPrice
     ```
   - Update `maxProfit` if the current profit is greater.
5. Return `maxProfit`.

---

# Step-by-Step Traversal

Consider:

```text
prices = [7,1,5,3,6,4]
```

### Initial

```text
minPrice = 7
maxProfit = 0
```

### Day 2 → price = 1

`1 < 7`

So update:

```text
minPrice = 1
```

```text
maxProfit = 0
```

### Day 3 → price = 5

Current price is greater than `minPrice`.

```text
profit = 5 - 1
       = 4
```

Update:

```text
maxProfit = 4
```

### Day 4 → price = 3

```text
profit = 3 - 1
       = 2
```

`2 < 4`, so:

```text
maxProfit = 4
```

### Day 5 → price = 6

```text
profit = 6 - 1
       = 5
```

Update:

```text
maxProfit = 5
```

### Day 6 → price = 4

```text
profit = 4 - 1
       = 3
```

`3 < 5`, so:

```text
maxProfit = 5
```

### Final Answer

```text
5
```

---

# Dry Run Table

| Day | Price | minPrice | Current Profit | maxProfit |
|-----|------:|---------:|---------------:|----------:|
| 1 | 7 | 7 | - | 0 |
| 2 | 1 | 1 | - | 0 |
| 3 | 5 | 1 | 4 | 4 |
| 4 | 3 | 1 | 2 | 4 |
| 5 | 6 | 1 | 5 | 5 |
| 6 | 4 | 1 | 3 | 5 |

Final:

```text
Maximum Profit = 5
```

---

# Java Solution

```java
class Solution {

    public int maxProfit(int[] prices) {

        int minPrice = prices[0];

        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {

            if (prices[i] < minPrice) {

                minPrice = prices[i];

            } else {

                int profit = prices[i] - minPrice;

                maxProfit = Math.max(maxProfit, profit);
            }
        }

        return maxProfit;
    }
}
```

---

# Important Logic

## 1. `minPrice`

```java
int minPrice = prices[0];
```

It stores the **lowest stock price found so far**.

Whenever we find a smaller price:

```java
if (prices[i] < minPrice) {
    minPrice = prices[i];
}
```

we update it.

---

## 2. `maxProfit`

```java
int maxProfit = 0;
```

It stores the **maximum profit found so far**.

For every selling price:

```java
int profit = prices[i] - minPrice;
```

we calculate the possible profit.

Then:

```java
maxProfit = Math.max(maxProfit, profit);
```

keeps the best profit.

---

# Pattern Used

## Greedy Algorithm

This solution uses the **Greedy** pattern.

### Why?

At every index, we make the best possible local decision:

> Keep the smallest buying price seen so far.

Then we check whether selling at today's price gives a better profit.

We don't need to store previous prices or try every possible combination.

---

## One-Pass / Single Traversal Pattern

The array is traversed only once:

```java
for (int i = 1; i < prices.length; i++)
```

During this traversal, we maintain only two pieces of information:

```text
minimum buying price
maximum profit
```

Therefore, the solution is very efficient.

---

# Why Not Brute Force?

A brute-force solution would try every possible pair:

```text
Buy on day 1 → Sell on day 2
Buy on day 1 → Sell on day 3
Buy on day 1 → Sell on day 4
...
```

For `n` prices, this can take:

```text
O(n²)
```

With:

```text
n = 100,000
```

that would be inefficient.

Our greedy solution reduces it to:

```text
O(n)
```

---

# Complexity Analysis

## Time Complexity

```text
O(n)
```

We traverse the array only once.

Where `n` is the number of prices.

---

## Space Complexity

```text
O(1)
```

We only use a few variables:

```text
minPrice
maxProfit
profit
```

No additional array or data structure is used.

---

# Final Complexity

| Complexity | Value |
|-----------|-------|
| Time | **O(n)** |
| Space | **O(1)** |

---

# Key Takeaway

The main idea is very simple:

```text
Find the lowest buying price so far
        ↓
Calculate today's profit
        ↓
Keep the maximum profit
```

In short:

```text
minPrice = minimum price so far

profit = current price - minPrice

maxProfit = maximum profit so far
```

This gives an optimal **O(n) time** and **O(1) space** solution.
