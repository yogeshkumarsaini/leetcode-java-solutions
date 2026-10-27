# Implement Stack Using Queues

## Problem

Implement a **LIFO (Last-In-First-Out) Stack** using only standard operations of queues.

The stack should support:

- `push(int x)` - Push an element onto the top of the stack.
- `pop()` - Remove and return the top element.
- `top()` - Return the top element without removing it.
- `empty()` - Check whether the stack is empty.

The implementation uses **two queues**: `q1` and `q2`.

---

## Approach

A queue follows **FIFO (First-In-First-Out)**, while a stack follows **LIFO (Last-In-First-Out)**.

To make a queue behave like a stack, we always keep the **newest element at the front of `q1`**.

For every `push(x)`:

1. Insert `x` into `q2`.
2. Move all existing elements from `q1` to `q2`.
3. Swap `q1` and `q2`.
4. Now the newly inserted element is at the front of `q1`.

Because the top element is always at the front of `q1`, both `pop()` and `top()` become simple queue operations.

---

## Algorithm

### `push(x)`

```text
1. Add x to q2.
2. While q1 is not empty:
      Remove the front element from q1.
      Add it to q2.
3. Swap q1 and q2.
```

### `pop()`

```text
1. Remove and return the front element of q1.
```

### `top()`

```text
1. Return the front element of q1.
```

### `empty()`

```text
1. Return whether q1 is empty.
```

---

## Step-by-Step Traversal

Suppose:

```text
push(1)
push(2)
push(3)
pop()
```

### Step 1: `push(1)`

```text
q1 = []
q2 = [1]
```

Swap:

```text
q1 = [1]
q2 = []
```

Stack:

```text
Top -> 1
```

### Step 2: `push(2)`

Add `2` to `q2`:

```text
q2 = [2]
```

Move all elements from `q1`:

```text
q2 = [2, 1]
q1 = []
```

Swap:

```text
q1 = [2, 1]
q2 = []
```

Stack:

```text
Top -> 2 -> 1
```

### Step 3: `push(3)`

```text
q2 = [3]
```

Move old elements:

```text
q2 = [3, 2, 1]
```

Swap:

```text
q1 = [3, 2, 1]
q2 = []
```

Stack:

```text
Top -> 3 -> 2 -> 1
```

### Step 4: `pop()`

```java
q1.poll();
```

removes:

```text
3
```

Remaining:

```text
q1 = [2, 1]
```

Therefore:

```text
pop() = 3
```

This correctly follows **LIFO** behavior.

---

## Java Code

```java
import java.util.LinkedList;
import java.util.Queue;

class MyStack {

    Queue<Integer> q1;
    Queue<Integer> q2;

    public MyStack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    public void push(int x) {

        q2.offer(x);

        while (!q1.isEmpty()) {
            q2.offer(q1.poll());
        }

        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    public int pop() {
        return q1.poll();
    }

    public int top() {
        return q1.peek();
    }

    public boolean empty() {
        return q1.isEmpty();
    }
}
```

---

## Code Explanation

### `push()`

```java
q2.offer(x);
```

First, add the new element to `q2`.

Then:

```java
while (!q1.isEmpty()) {
    q2.offer(q1.poll());
}
```

Move all old elements behind the newly inserted element.

For example:

```text
Before:
q1 = [2, 1]

push(3)

q2 = [3]

Move q1:

q2 = [3, 2, 1]
```

Then swap:

```java
Queue<Integer> temp = q1;
q1 = q2;
q2 = temp;
```

Now:

```text
q1 = [3, 2, 1]
```

So the newest element is always at the front.

### `pop()`

```java
return q1.poll();
```

The stack top is always at the front of `q1`, so `poll()` removes the correct element.

### `top()`

```java
return q1.peek();
```

`peek()` returns the front element without removing it.

### `empty()`

```java
return q1.isEmpty();
```

Checks whether the stack contains any elements.

---

## Pattern Used

### Two-Queue Simulation / Queue Reordering

This solution uses the **Two-Queue Simulation pattern**.

A queue naturally works as:

```text
FIFO
First In -> First Out
```

But a stack requires:

```text
LIFO
Last In -> First Out
```

Instead of changing the queue operations, we change the **order of the elements**.

After every `push()`:

```text
Newest element -> Front
Older elements -> Behind it
```

Therefore:

```text
q1.front() = Stack.top()
```

This is why `pop()` and `top()` can both be implemented in `O(1)` time.

---

## Complexity Analysis

Let `n` be the number of elements currently in the stack.

| Operation | Time | Space |
|---|---:|---:|
| `push()` | O(n) | O(n) |
| `pop()` | O(1) | O(1) |
| `top()` | O(1) | O(1) |
| `empty()` | O(1) | O(1) |

### Why `push()` is O(n)?

During every `push()`, all existing elements are moved from `q1` to `q2`.

So if there are `n` elements:

```text
n elements → moved once
```

Therefore:

```text
push() = O(n)
```

### Why `pop()` is O(1)?

Only the front element is removed:

```java
q1.poll();
```

Therefore:

```text
pop() = O(1)
```

### Why `top()` is O(1)?

Only the front element is checked:

```java
q1.peek();
```

Therefore:

```text
top() = O(1)
```

### Why `empty()` is O(1)?

Only an empty check is performed:

```java
q1.isEmpty();
```

Therefore:

```text
empty() = O(1)
```

### Space Complexity

The two queues together contain at most `n` elements.

```text
Space = O(n)
```

---

## Follow-Up: One Queue

Yes, we can implement the stack using only **one queue**.

The idea is:

1. Add the new element to the back.
2. Rotate the old elements from front to back.
3. This moves the newest element to the front.

Example:

```text
Before:
q = [2, 1]

Add 3:
q = [2, 1, 3]

Rotate old elements:
q = [3, 2, 1]
```

### One-Queue Java Solution

```java
import java.util.LinkedList;
import java.util.Queue;

class MyStack {

    Queue<Integer> q;

    public MyStack() {
        q = new LinkedList<>();
    }

    public void push(int x) {

        q.offer(x);

        int size = q.size();

        for (int i = 0; i < size - 1; i++) {
            q.offer(q.poll());
        }
    }

    public int pop() {
        return q.poll();
    }

    public int top() {
        return q.peek();
    }

    public boolean empty() {
        return q.isEmpty();
    }
}
```

### One-Queue Complexity

| Operation | Time | Space |
|---|---:|---:|
| `push()` | O(n) | O(n) |
| `pop()` | O(1) | O(1) |
| `top()` | O(1) | O(1) |
| `empty()` | O(1) | O(1) |

---

## Key Takeaway

The main concept is:

```text
Queue  = FIFO
Stack  = LIFO
```

We simulate LIFO by keeping the **latest pushed element at the front of the queue**.

For the two-queue approach:

```text
push() → O(n)
pop()  → O(1)
top()  → O(1)
empty() → O(1)

Space → O(n)
```