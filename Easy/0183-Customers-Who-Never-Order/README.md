# Customers Who Never Order Anything

## Problem

Given two tables:

### Customers

| Column | Type |
|---|---|
| id | int |
| name | varchar |

`id` is the primary key.

### Orders

| Column | Type |
|---|---|
| id | int |
| customerId | int |

`customerId` references `Customers.id`.

Find all customers who have **never placed an order**.

---

## MySQL Solution

```sql
SELECT c.name AS Customers
FROM Customers c
LEFT JOIN Orders o
    ON c.id = o.customerId
WHERE o.customerId IS NULL;
```

---

## Approach

This solution uses the **LEFT JOIN + NULL check** pattern.

The idea is:

1. Start with every customer from the `Customers` table.
2. Use `LEFT JOIN` to match each customer with their orders.
3. If a customer has an order, the matching `Orders` columns contain values.
4. If a customer has no order, the `Orders` columns become `NULL`.
5. Filter those rows using:
   ```sql
   WHERE o.customerId IS NULL
   ```
6. Return the customer's name.

---

## Why LEFT JOIN?

We need to keep **all customers**, including customers who do not have an order.

A normal `INNER JOIN` would remove customers without matching orders.

Example:

```text
Customers
1 -> Joe
2 -> Henry
3 -> Sam
4 -> Max

Orders
1 -> customer 3
2 -> customer 1
```

After `LEFT JOIN`:

```text
Joe   -> 1
Henry -> NULL
Sam   -> 3
Max   -> NULL
```

Now we only need rows where the order information is `NULL`:

```text
Henry
Max
```

---

## Step-by-Step Traversal

Suppose we have:

```text
Customers
+----+-------+
| id | name  |
+----+-------+
| 1  | Joe   |
| 2  | Henry |
| 3  | Sam   |
| 4  | Max   |
+----+-------+

Orders
+----+------------+
| id | customerId |
+----+------------+
| 1  | 3          |
| 2  | 1          |
+----+------------+
```

### Step 1: Start with Customers

```text
Joe
Henry
Sam
Max
```

### Step 2: Match `Customers.id` with `Orders.customerId`

```text
Joe   -> customerId 1 -> order found
Henry -> customerId 2 -> no order
Sam   -> customerId 3 -> order found
Max   -> customerId 4 -> no order
```

### Step 3: Check for NULL

```sql
WHERE o.customerId IS NULL
```

Only these customers remain:

```text
Henry
Max
```

### Step 4: Return the name

```text
+-----------+
| Customers |
+-----------+
| Henry     |
| Max       |
+-----------+
```

---

## Pattern Used

### LEFT JOIN + IS NULL

This is a common SQL pattern for finding records in one table that **do not have a matching record** in another table.

General form:

```sql
SELECT A.column
FROM A
LEFT JOIN B
    ON A.id = B.a_id
WHERE B.a_id IS NULL;
```

### Why this pattern?

Because the requirement is:

> Find customers for whom no order exists.

`LEFT JOIN` preserves all customers, and `IS NULL` identifies customers without a matching order.

---

## Alternative Solution

The same problem can also be solved using `NOT EXISTS`:

```sql
SELECT c.name AS Customers
FROM Customers c
WHERE NOT EXISTS (
    SELECT 1
    FROM Orders o
    WHERE o.customerId = c.id
);
```

Both approaches are valid.

For this problem, `LEFT JOIN + IS NULL` is a simple and easy-to-understand solution.

---

## Complexity

Let:

- `C` = number of rows in `Customers`
- `O` = number of rows in `Orders`

### Time Complexity

```text
O(C + O)
```

With appropriate indexing on the join key (`Orders.customerId`), the database can efficiently find matching orders.

For a typical indexed relational database, the actual execution plan may use a hash join, nested-loop join, or another optimized strategy depending on the database and indexes.

### Space Complexity

```text
O(C + O)
```

The exact auxiliary memory depends on the database execution plan. For example, a hash join may require additional memory for a hash table, while another plan may use less.

For interview-style analysis, the key point is that the query does not create a permanent extra data structure; the database manages any temporary join memory internally.

---

## Important Point

Do **not** use:

```sql
WHERE o.customerId = NULL
```

Use:

```sql
WHERE o.customerId IS NULL
```

In SQL, `NULL` represents an unknown/missing value, so `=` cannot be used to test for `NULL`.

---

## Final Answer

```sql
SELECT c.name AS Customers
FROM Customers c
LEFT JOIN Orders o
    ON c.id = o.customerId
WHERE o.customerId IS NULL;
```

### Pattern

**LEFT JOIN + IS NULL**

### Purpose

Find records from the first table that have **no matching record** in the second table.
