# 197. Rising Temperature

## Problem

Given a `Weather` table containing:

- `id` — unique identifier
- `recordDate` — date of the temperature record
- `temperature` — temperature on that date

Find the `id` of every date where the temperature is **higher than the previous day (yesterday)**.

The result can be returned in any order.

---

## SQL Solution

```sql
SELECT w1.id
FROM Weather w1
JOIN Weather w2
    ON w1.recordDate = DATE_ADD(w2.recordDate, INTERVAL 1 DAY)
WHERE w1.temperature > w2.temperature;
```

---

## Approach

We use a **Self Join**.

The `Weather` table contains both:

1. Today's record
2. Yesterday's record

So we join the table with itself:

- `w1` represents the current/today's row.
- `w2` represents yesterday's row.

The join condition is:

```sql
w1.recordDate = DATE_ADD(w2.recordDate, INTERVAL 1 DAY)
```

This means:

> Today's date must be exactly one day after yesterday's date.

After matching the two rows, we compare their temperatures:

```sql
w1.temperature > w2.temperature
```

If today's temperature is greater than yesterday's temperature, we return today's `id`.

---

## Why Self Join?

The problem requires comparing two rows from the **same table**:

```text
Today's row  <->  Yesterday's row
```

A self join is a natural SQL pattern for comparing related rows within the same table.

### Pattern Used

**Self Join + Date Comparison**

We use this pattern because:

- Both values are stored in the same table.
- We need to compare the current date with the previous calendar date.
- `DATE_ADD()` makes the previous/current date relationship explicit.
- It avoids relying on `id` values being consecutive.

---

## Algorithm

1. Take the `Weather` table as `w1` for today's records.
2. Take the same `Weather` table as `w2` for yesterday's records.
3. Match `w1` with `w2` when:
   ```sql
   w1.recordDate = w2.recordDate + 1 day
   ```
4. Compare:
   ```sql
   w1.temperature > w2.temperature
   ```
5. If the condition is true, return `w1.id`.

---

## Step-by-Step Traversal

For the example:

| id | recordDate | temperature |
|---:|------------|------------:|
| 1 | 2015-01-01 | 10 |
| 2 | 2015-01-02 | 25 |
| 3 | 2015-01-03 | 20 |
| 4 | 2015-01-04 | 30 |

### Step 1

For `2015-01-01`:

There is no previous day in the table.

So it is ignored.

### Step 2

For `2015-01-02`:

```text
Today:      25
Yesterday: 10
```

```text
25 > 10  -> TRUE
```

Return:

```text
id = 2
```

### Step 3

For `2015-01-03`:

```text
Today:      20
Yesterday: 25
```

```text
20 > 25  -> FALSE
```

Do not return the id.

### Step 4

For `2015-01-04`:

```text
Today:      30
Yesterday: 20
```

```text
30 > 20  -> TRUE
```

Return:

```text
id = 4
```

### Final Result

```text
2
4
```

---

## Query Breakdown

### 1. Create two references to the same table

```sql
FROM Weather w1
JOIN Weather w2
```

`w1` and `w2` are aliases for the same `Weather` table.

---

### 2. Find yesterday's record

```sql
ON w1.recordDate = DATE_ADD(w2.recordDate, INTERVAL 1 DAY)
```

If:

```text
w2.recordDate = 2015-01-01
```

then:

```text
DATE_ADD(w2.recordDate, INTERVAL 1 DAY)
= 2015-01-02
```

So it matches:

```text
w1.recordDate = 2015-01-02
```

---

### 3. Compare temperatures

```sql
WHERE w1.temperature > w2.temperature
```

Only rows where today's temperature is greater than yesterday's are kept.

---

### 4. Return today's ID

```sql
SELECT w1.id
```

We return the ID of the current day's record, not yesterday's ID.

---

## Important Point

Do **not** assume that:

```text
today.id = yesterday.id + 1
```

The problem only guarantees that `id` is unique. IDs do not necessarily represent consecutive dates.

For example:

| id | recordDate |
|---:|------------|
| 10 | 2015-01-01 |
| 25 | 2015-01-02 |

The correct relationship is based on `recordDate`, not `id`.

That is why we use:

```sql
w1.recordDate = DATE_ADD(w2.recordDate, INTERVAL 1 DAY)
```

---

## Complexity

### Time Complexity

**O(n²)** in the general theoretical sense of a self join, because the database may compare rows from the same table during the join.

However, in practice, the database optimizer can use indexes and an efficient join strategy, so the actual performance can be much better.

### Space Complexity

**O(n)** auxiliary space in the worst case for the join operation, depending on the database execution plan and indexes.

The SQL query itself does not explicitly create an additional table in memory.

---

## Pattern Summary

| Pattern | Why Used |
|---------|----------|
| Self Join | Compare rows from the same table |
| Date Arithmetic | Identify the exact previous calendar day |
| `DATE_ADD()` | Move yesterday's date forward by one day |
| Comparison | Check whether today's temperature is higher |

---

## Key Takeaway

When a SQL problem asks you to compare a row with the **previous/next row or date in the same table**, a **Self Join** is a useful pattern.

Here:

```sql
w1 = today
w2 = yesterday
```

Then:

```sql
w1.temperature > w2.temperature
```

identifies the dates with a rising temperature.

---

## Final Query

```sql
SELECT w1.id
FROM Weather w1
JOIN Weather w2
    ON w1.recordDate = DATE_ADD(w2.recordDate, INTERVAL 1 DAY)
WHERE w1.temperature > w2.temperature;
```
