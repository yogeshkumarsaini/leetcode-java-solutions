# Employees Earning More Than Their Managers

## MySQL Solution

```sql
SELECT e.name AS Employee
FROM Employee e
JOIN Employee m
    ON e.managerId = m.id
WHERE e.salary > m.salary;
```

## Approach

Hume employee ki salary ko uske manager ki salary se compare karna hai.

Lekin **employee aur manager dono same `Employee` table mein hain**, isliye hum **Self Join** use karenge.

Hum table ko 2 aliases dete hain:

```text
e → Employee
m → Manager
```

Relationship:

```text
e.managerId = m.id
```

Uske baad salary compare karte hain:

```text
e.salary > m.salary
```

Agar employee ki salary manager se zyada hai, to employee ka naam result mein aa jayega.

---

## Step-by-Step Traversal

Example:

```text
id | name  | salary | managerId
---|-------|--------|----------
1  | Joe   | 70000  | 3
2  | Henry | 80000  | 4
3  | Sam   | 60000  | NULL
4  | Max   | 90000  | NULL
```

### Step 1 — Employee ko identify karo

```sql
Employee e
```

`e` employee ko represent karega.

### Step 2 — Manager ko identify karo

```sql
Employee m
```

`m` manager ko represent karega.

### Step 3 — Employee aur Manager ko connect karo

```sql
ON e.managerId = m.id
```

Isse:

```text
Joe   → Sam
Henry → Max
```

### Step 4 — Salary compare karo

Joe:

```text
Joe  = 70000
Sam  = 60000

70000 > 60000
```

True → **Joe select hoga**

Henry:

```text
Henry = 80000
Max   = 90000

80000 > 90000
```

False → Henry select nahi hoga.

### Step 5 — Employee ka naam return karo

```sql
SELECT e.name AS Employee
```

Final result:

```text
+----------+
| Employee |
+----------+
| Joe      |
+----------+
```

---

## Algorithm

1. `Employee` table ko do aliases ke saath use karo.
2. `e` ko employee aur `m` ko manager maan lo.
3. `e.managerId = m.id` se employee aur manager ko match karo.
4. `e.salary > m.salary` condition check karo.
5. Condition true hone par employee ka naam return karo.

---

## Pattern Used: Self Join

### Self Join kya hota hai?

Jab hum **same table ko khud ke saath join** karte hain, use Self Join kehte hain.

```sql
FROM Employee e
JOIN Employee m
```

Yahan `Employee` table do baar use ho rahi hai:

```text
Employee e → Employee
Employee m → Manager
```

### Self Join kyun use kiya?

Kyuki manager bhi `Employee` table ka hi ek record hai.

Isliye hume ek hi table ke:

```text
Employee Row
       ↓
Manager Row
```

ko compare karna hai.

### Self Join kaha useful hota hai?

- Employee → Manager
- Employee → Supervisor
- Parent → Child
- User → Referrer
- Category → Parent Category
- Hierarchical data
- Same table ke records ko compare karna

---

## Query Breakdown

```sql
SELECT e.name AS Employee
```

Employee ka naam return karta hai.

```sql
FROM Employee e
```

`e` employee ka alias hai.

```sql
JOIN Employee m
```

`m` manager ka alias hai.

```sql
ON e.managerId = m.id
```

Employee ke `managerId` ko manager ke `id` se match karta hai.

```sql
WHERE e.salary > m.salary;
```

Sirf un employees ko select karta hai jinki salary manager se greater hai.

---

## Complexity

Let:

```text
n = number of employees
```

### Time Complexity

```text
O(n)
```

`id` primary key hone ki wajah se manager lookup efficient hota hai. Standard interview/LeetCode analysis mein is solution ko **O(n)** maana jata hai.

### Space Complexity

```text
O(n)
```

SQL engine join processing ke liye internal memory use kar sakta hai. Exact memory usage database optimizer aur execution plan par depend karti hai.

---

## Final Query

```sql
SELECT e.name AS Employee
FROM Employee e
JOIN Employee m
    ON e.managerId = m.id
WHERE e.salary > m.salary;
```

## Key Takeaway

Is problem ka main pattern yaad rakho:

```sql
FROM Table a
JOIN Table b
    ON a.some_id = b.id
```

Agar `Table a` aur `Table b` **same table** hain, to ye **Self Join** hai.

Is problem mein:

```text
e = Employee
m = Manager
```

Relationship:

```text
e.managerId = m.id
```

Comparison:

```text
e.salary > m.salary
```

Isliye answer:

```sql
SELECT e.name AS Employee
FROM Employee e
JOIN Employee m
    ON e.managerId = m.id
WHERE e.salary > m.salary;
```