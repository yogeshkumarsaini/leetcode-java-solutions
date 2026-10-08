# LeetCode - Combine Two Tables

## MySQL Solution

```sql
SELECT 
    p.firstName,
    p.lastName,
    a.city,
    a.state
FROM Person p
LEFT JOIN Address a
    ON p.personId = a.personId;
```

## Approach

Is problem mein hume **Person table ke har person ko return karna hai**.

Lekin har person ka Address table mein address hona zaroori nahi hai.

Isliye hum **LEFT JOIN** use karte hain.

```sql
FROM Person p
LEFT JOIN Address a
    ON p.personId = a.personId
```

`Person` left table hai, isliye Person ka **har record result mein aayega**.

Agar Address table mein matching `personId` milta hai, to `city` aur `state` mil jayenge.

Agar matching address nahi milta, to:

```text
city  = NULL
state = NULL
```

---

## Step-by-Step Traversal

### Step 1: Person table se start

```text
personId | lastName | firstName
---------|----------|----------
1        | Wang     | Allen
2        | Alice    | Bob
```

### Step 2: Address table mein matching `personId` find karo

```text
personId | city          | state
---------|---------------|----------
2        | New York City | New York
3        | Leetcode      | California
```

### Step 3: Person ID = 1

Address table mein:

```text
personId = 1
```

nahi hai.

Isliye:

```text
Allen | Wang | NULL | NULL
```

### Step 4: Person ID = 2

Address table mein:

```text
personId = 2
```

mil gaya.

Isliye:

```text
Bob | Alice | New York City | New York
```

### Final Result

```text
firstName | lastName | city          | state
----------|----------|---------------|----------
Allen     | Wang     | NULL          | NULL
Bob       | Alice    | New York City | New York
```

---

## Algorithm

1. `Person` table ke sabhi records read karo.
2. `Address` table ke saath `LEFT JOIN` karo.
3. Matching condition rakho:

```sql
p.personId = a.personId
```

4. Agar address milta hai, `city` aur `state` return karo.
5. Agar address nahi milta, `NULL` return hoga.
6. Required columns return karo:

```text
firstName
lastName
city
state
```

---

## Pattern Used

### LEFT JOIN Pattern

Is problem mein **LEFT JOIN / Outer Join pattern** use hua hai.

General pattern:

```sql
SELECT ...
FROM TableA A
LEFT JOIN TableB B
    ON A.id = B.id;
```

### LEFT JOIN kab use karein?

Jab question kahe:

> First/Main table ke **saare records** chahiye, chahe second table mein matching record ho ya nahi.

Yahan:

```text
Person = Main table
Address = Optional information
```

Isliye:

```text
Person LEFT JOIN Address
```

correct hai.

---

## INNER JOIN kyun nahi?

Agar hum:

```sql
INNER JOIN
```

use karte, to sirf wahi persons aate jinka Address table mein matching address hai.

Person `1` ka address nahi hai, isliye woh result se remove ho jata.

Lekin problem ke according **Person table ka har person return hona chahiye**.

Isliye `LEFT JOIN` use kiya.

---

## Complexity

Let:

```text
P = Person table ke rows
A = Address table ke rows
```

### Time Complexity

High-level:

```text
O(P + A)
```

Actual SQL execution database ke optimizer, indexes aur join algorithm par depend karta hai.

Agar `Address.personId` indexed ho, to matching records efficiently find kiye ja sakte hain.

### Space Complexity

Database engine internally join ke liye memory structures use kar sakta hai, jaise hash table, sorting buffers, etc.

High-level:

```text
O(P + A)
```

Lekin application level par hum manually koi extra array, map ya stack create nahi kar rahe.

---

## Interview Answer

Agar interviewer puche:

**"Why did you use LEFT JOIN?"**

Answer:

> "Because every person from the Person table must be included in the result. Some persons may not have an address in the Address table. LEFT JOIN keeps all Person records and returns NULL for city and state when there is no matching address."

---

## Final Code

```sql
SELECT 
    p.firstName,
    p.lastName,
    a.city,
    a.state
FROM Person p
LEFT JOIN Address a
    ON p.personId = a.personId;
```

## Key Takeaway

```text
LEFT JOIN
   ↓
Keep ALL records from LEFT table
   +
Matching records from RIGHT table
   +
No match → NULL
```

Yaad rakho:

```text
LEFT JOIN  → Left table ke saare records
INNER JOIN → Sirf matching records
```
