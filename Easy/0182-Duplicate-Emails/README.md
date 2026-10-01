# Duplicate Emails

## Problem

Given a `Person` table containing `id` and `email`, find all email addresses that appear more than once.

The `email` column is guaranteed to be non-NULL.

### Example

Input:

| id | email |
|---:|---|
| 1 | a@b.com |
| 2 | c@d.com |
| 3 | a@b.com |

Output:

| Email |
|---|
| a@b.com |

---

## MySQL Solution

```sql
SELECT email
FROM Person
GROUP BY email
HAVING COUNT(email) > 1;
```

---

## Approach

We need to identify emails that occur multiple times.

The query uses:

1. `GROUP BY email` to put the same email values into one group.
2. `COUNT(email)` to count how many rows are present in each email group.
3. `HAVING COUNT(email) > 1` to keep only groups that occur more than once.

---

## Algorithm

1. Read the `email` column from the `Person` table.
2. Group rows having the same email using `GROUP BY email`.
3. Count the number of rows in every email group.
4. Keep only groups where the count is greater than `1`.
5. Return the duplicate email values.

---

## Step-by-Step Traversal

Suppose the table is:

```text
id   email
1    a@b.com
2    c@d.com
3    a@b.com
```

### Step 1: GROUP BY email

The rows are grouped like this:

```text
a@b.com -> 2 rows
c@d.com -> 1 row
```

### Step 2: COUNT(email)

```text
a@b.com -> COUNT = 2
c@d.com -> COUNT = 1
```

### Step 3: Apply HAVING

Condition:

```sql
HAVING COUNT(email) > 1
```

So:

```text
a@b.com -> 2 > 1 -> keep
c@d.com -> 1 > 1 -> remove
```

### Final Result

```text
a@b.com
```

---

## Why `HAVING` Instead of `WHERE`?

`WHERE` filters individual rows **before** grouping.

`HAVING` filters groups **after** `GROUP BY`.

Because we need to check the result of `COUNT()`, we use `HAVING`.

```sql
GROUP BY email
HAVING COUNT(email) > 1
```

---

## Pattern Used

### Pattern: `GROUP BY + HAVING`

This is a common SQL pattern for finding:

- Duplicate values
- Repeated records
- Groups satisfying a condition
- Values occurring more than N times

General pattern:

```sql
SELECT column
FROM table
GROUP BY column
HAVING COUNT(column) > 1;
```

### Why this pattern?

The problem asks:

> Which email values occur more than once?

That is a **group-level condition**, so we group by email and then filter the groups using `HAVING`.

---

## Complexity

Let `n` be the number of rows in the `Person` table.

### Time Complexity

**O(n)** average/expected for the hash-based grouping approach.

A database may also implement `GROUP BY` using sorting, in which case the cost can be **O(n log n)**.

So the practical complexity depends on the database execution plan.

### Space Complexity

**O(k)** auxiliary space for grouping, where `k` is the number of distinct email values.

In the worst case, `k = n`, so worst-case auxiliary space is:

**O(n)**

The exact memory usage depends on the MySQL execution plan and indexes.

---

## Short Explanation

```sql
SELECT email
FROM Person
GROUP BY email
HAVING COUNT(email) > 1;
```

- `GROUP BY email` → groups identical emails.
- `COUNT(email)` → counts each email.
- `HAVING COUNT(email) > 1` → returns only repeated emails.

---

## Key Takeaway

Whenever a SQL problem asks:

> Find values that appear more than once.

Think of:

```sql
GROUP BY column
HAVING COUNT(*) > 1
```

For this problem:

```sql
SELECT email
FROM Person
GROUP BY email
HAVING COUNT(*) > 1;
```
