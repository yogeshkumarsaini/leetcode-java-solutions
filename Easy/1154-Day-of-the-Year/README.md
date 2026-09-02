# Day of the Year

## Problem Statement

Given a string `date` representing a **Gregorian calendar date** formatted as `YYYY-MM-DD`, return the **day number of the year**.

### Examples

#### Example 1

```text
Input: date = "2019-01-09"
Output: 9
```

**Explanation:**
January 9th is the 9th day of the year.

#### Example 2

```text
Input: date = "2019-02-10"
Output: 41
```

**Explanation:**

```text
January = 31 days
February = 10 days

31 + 10 = 41
```

---

## Approach

The solution uses a **Prefix Sum / Precomputed Cumulative Days** approach.

Instead of looping through every previous month, we store the total number of days before each month in an array:

```java
int[] days = {
    0,    // Before January
    31,   // Before February
    59,   // Before March
    90,   // Before April
    120,  // Before May
    151,  // Before June
    181,  // Before July
    212,  // Before August
    243,  // Before September
    273,  // Before October
    304,  // Before November
    334   // Before December
};
```

For a given month:

```text
Day of Year = Days before current month + Current day
```

For example:

```text
date = 2019-05-10

Days before May = 120
Current day      = 10

Answer = 120 + 10
       = 130
```

---

## Algorithm

1. Extract the `year` from positions `0-3`.
2. Extract the `month` from positions `5-6`.
3. Extract the `day` from positions `8-9`.
4. Create a cumulative-days array containing the number of days before every month.
5. Calculate:

```text
result = days[month - 1] + day
```

6. Check whether the year is a **leap year**.
7. If it is a leap year and the month is after February, add `1`.
8. Return the result.

---

## Leap Year Logic

A year is a leap year when:

```text
year % 400 == 0
```

OR

```text
year % 4 == 0 AND year % 100 != 0
```

In Java:

```java
year % 400 == 0 ||
(year % 4 == 0 && year % 100 != 0)
```

### Why?

The Gregorian calendar follows these rules:

* Every year divisible by `400` is a leap year.
* Every year divisible by `4` is normally a leap year.
* Years divisible by `100` are **not** leap years unless they are also divisible by `400`.

Examples:

```text
2000 → Leap Year
1900 → Not a Leap Year
2016 → Leap Year
2019 → Not a Leap Year
```

Since February has one extra day in a leap year, we only add `1` when:

```java
month > 2
```

---

## Step-by-Step Traversal

Consider:

```text
date = "2019-02-10"
```

### Step 1: Extract Year

```java
int year = Integer.parseInt(date.substring(0, 4));
```

Result:

```text
year = 2019
```

### Step 2: Extract Month

```java
int month = Integer.parseInt(date.substring(5, 7));
```

Result:

```text
month = 2
```

### Step 3: Extract Day

```java
int day = Integer.parseInt(date.substring(8, 10));
```

Result:

```text
day = 10
```

### Step 4: Find Days Before February

From the cumulative array:

```java
days[month - 1]
```

Since:

```text
month = 2
```

we access:

```text
days[1] = 31
```

So:

```text
Days before February = 31
```

### Step 5: Add Current Day

```text
result = 31 + 10
       = 41
```

### Step 6: Check Leap Year

```text
2019 is not a leap year
```

No additional day is required.

### Final Answer

```text
41
```

---

## Another Traversal Example

Consider:

```text
date = "2020-03-10"
```

### Extract Values

```text
year  = 2020
month = 3
day   = 10
```

### Days Before March

```text
days[2] = 59
```

Therefore:

```text
result = 59 + 10
       = 69
```

### Leap Year Check

```text
2020 % 4 == 0
2020 % 100 != 0
```

So `2020` is a leap year.

Because:

```text
month > 2
```

we add one extra day:

```text
result = 69 + 1
       = 70
```

Therefore:

```text
Output = 70
```

---

## Pattern Used

### Pattern: Prefix Sum / Precomputed Cumulative Values

The main pattern used in this solution is **Prefix Sum / Precomputation**.

Instead of calculating the total number of days month-by-month every time, we precompute the cumulative number of days before each month.

```text
January    → 0
February   → 31
March      → 59
April      → 90
May        → 120
June       → 151
July       → 181
August     → 212
September  → 243
October    → 273
November   → 304
December   → 334
```

Then the answer can be obtained directly:

```text
days[month - 1] + day
```

### Why Use This Pattern?

Without precomputation, we could loop through all previous months:

```text
January → February → March → ...
```

But that is unnecessary.

The cumulative array allows us to directly know how many days occurred before the current month.

Therefore:

```text
Previous Month Calculation
        ↓
Precomputed Cumulative Days
        ↓
Direct Lookup
        ↓
Add Current Day
```

This makes the solution simple, predictable, and efficient.

---

## Code

```java
class Solution {
    public int dayOfYear(String date) {

        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8, 10));

        int[] days = {
            0, 31, 59, 90, 120, 151,
            181, 212, 243, 273, 304, 334
        };

        int result = days[month - 1] + day;

        if (month > 2 &&
            (year % 400 == 0 ||
            (year % 4 == 0 && year % 100 != 0))) {

            result++;
        }

        return result;
    }
}
```

---

## Complexity Analysis

### Time Complexity

```text
O(1)
```

Why?

* Extracting year, month, and day takes constant work because the date length is fixed at `10`.
* Array lookup takes `O(1)`.
* Leap-year calculation takes `O(1)`.
* No loop is used.

Therefore:

```text
Time Complexity = O(1)
```

---

### Space Complexity

```text
O(1)
```

Why?

The solution uses a fixed-size array of `12` elements:

```java
int[] days = new int[12];
```

The size of this array does not depend on the input size.

Therefore:

```text
Space Complexity = O(1)
```

---

## Complexity Summary

| Complexity |  Value | Reason                                      |
| ---------- | -----: | ------------------------------------------- |
| Time       | `O(1)` | Direct array lookup + constant calculations |
| Space      | `O(1)` | Fixed 12-element array                      |

---

## Key Takeaways

* Use **substring** to extract year, month, and day.
* Use a **precomputed cumulative-days array** for direct calculation.
* Handle leap years separately.
* Add the leap-year extra day only when `month > 2`.
* No loop is required.
* Both time and auxiliary space complexity are `O(1)`.

### Formula

```text
Day of Year
= Days Before Current Month
+ Current Day
+ Leap Day (if applicable)
```

For a leap year after February:

```text
Answer = days[month - 1] + day + 1
```

Otherwise:

```text
Answer = days[month - 1] + day
```
