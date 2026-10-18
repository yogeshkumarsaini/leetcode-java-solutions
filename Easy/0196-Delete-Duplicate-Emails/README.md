# Delete Duplicate Emails

## Problem

Given a `Person` table:

| Column | Type | Description |
|---|---|---|
| `id` | int | Primary key |
| `email` | varchar | Email address |

Delete all duplicate email records while keeping **only one row for each email**, specifically the row with the **smallest `id`**.

---

## MySQL Solution

```sql
DELETE p1
FROM Person p1
JOIN Person p2
ON p1.email = p2.email
AND p1.id > p2.id;
```

---

## Approach

We use a **Self Join**.

The `Person` table is joined with itself:

- `p1` = row that may be deleted
- `p2` = another row having the same email

The condition:

```sql
p1.email = p2.email
```

finds rows with duplicate emails.

Then:

```sql
p1.id > p2.id
```

ensures that `p1` has a larger ID than another row with the same email.

Therefore, `p1` is a duplicate and can be safely deleted.

The row having the smallest `id` has no smaller ID with the same email, so it is preserved.

---

## Step-by-Step Algorithm

1. Take the `Person` table as `p1`.
2. Create another reference to the same table as `p2`.
3. Match rows where:
   ```sql
   p1.email = p2.email
   ```
4. Compare their IDs.
5. If:
   ```sql
   p1.id > p2.id
   ```
   then `p1` is the duplicate row.
6. Delete `p1`.
7. Continue until only the row with the smallest ID remains for each email.

---

## Step-by-Step Traversal

For the input:

```text
id | email
---|------------------
1  | john@example.com
2  | bob@example.com
3  | john@example.com
```

### Compare `id = 1`

`p1.id = 1`

There is no row with the same email and a smaller ID.

So, keep it.

### Compare `id = 2`

`p1.id = 2`

There is no row with the same email and a smaller ID.

So, keep it.

### Compare `id = 3`

`p1.id = 3`

Another row has:

```text
email = john@example.com
id = 1
```

Since:

```text
3 > 1
```

row `id = 3` is a duplicate.

Delete it.

### Final Result

```text
id | email
---|------------------
1  | john@example.com
2  | bob@example.com
```

---

## Why This Pattern?

### Pattern: Self Join + Comparison

A **Self Join** is used because we need to compare rows inside the same table.

We need to answer:

> "Does another row with the same email exist with a smaller ID?"

The self join makes this comparison simple:

```sql
p1.email = p2.email
AND p1.id > p2.id
```

This is more direct than using a subquery for this deletion problem.

---

## Why `p1.id > p2.id`?

This condition is the key part of the solution.

Suppose:

```text
1 | john@example.com
3 | john@example.com
5 | john@example.com
```

For `id = 3`:

```text
3 > 1
```

So delete `3`.

For `id = 5`:

```text
5 > 1
5 > 3
```

So delete `5`.

For `id = 1`:

```text
1 > 3  -> false
1 > 5  -> false
```

Therefore, `id = 1` is kept.

So the **smallest ID automatically survives**.

---

## Complexity

Let `n` be the number of rows in `Person`.

### Time Complexity

**O(n²)** in the general case for a self-join comparison.

However, MySQL's query optimizer can use indexes to make the actual execution more efficient depending on the table structure and indexes.

### Space Complexity

**O(n)** auxiliary space in the worst case for join processing, depending on the MySQL execution plan.

The query itself does not create a separate result table.

---

## Key SQL Concept

```sql
DELETE p1
FROM Person p1
JOIN Person p2
ON p1.email = p2.email
AND p1.id > p2.id;
```

### Important Points

- `p1` is the row we may delete.
- `p2` is used to find an earlier duplicate.
- Same email → duplicate candidate.
- Larger ID → delete.
- Smaller ID → keep.
- `DELETE` is used directly, as required by the problem.

---

## Pattern Summary

| Concept | Usage |
|---|---|
| Self Join | Compare rows from the same table |
| Duplicate Detection | Match same `email` |
| ID Comparison | `p1.id > p2.id` |
| Delete Pattern | Delete the larger-ID duplicate |
| Goal | Keep smallest ID for every email |

---

## Final Code

```sql
DELETE p1
FROM Person p1
JOIN Person p2
ON p1.email = p2.email
AND p1.id > p2.id;
```

## Complexity Summary

- **Time:** O(n²) general self-join comparison
- **Space:** O(n) worst-case auxiliary join space, depending on execution plan
- **Pattern:** Self Join + Comparison
