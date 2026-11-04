# 232. Implement Queue using Stacks

## Problem Statement

Implement a **First In First Out (FIFO) Queue** using only two stacks.

The queue should support the following operations:

- `push(int x)` – Insert an element at the back of the queue.
- `pop()` – Remove and return the front element.
- `peek()` – Return the front element without removing it.
- `empty()` – Check whether the queue is empty.

### Example

**Input:**
```text
["MyQueue", "push", "push", "peek", "pop", "empty"]
[[], [1], [2], [], [], []]
```

**Output:**
```text
[null, null, null, 1, 1, false]
```

**Explanation:**

```java
MyQueue myQueue = new MyQueue();

myQueue.push(1);    // Queue: [1]
myQueue.push(2);    // Queue: [1, 2]

myQueue.peek();     // Returns 1
myQueue.pop();      // Returns 1

myQueue.empty();    // Returns false
```

---

## 1. Approach: Two Stacks (Lazy Transfer)

We use two stacks to implement a FIFO queue.

### Stack 1: inputStack

- Stores newly inserted elements.
- All `push()` operations happen here.

### Stack 2: outputStack

- Stores elements in reverse order.
- Helps us access the oldest element first.
- All `pop()` and `peek()` operations use this stack.

### Working Principle

Whenever we need to access the front element:

1. Check whether `outputStack` is empty.
2. If it is empty, transfer all elements from `inputStack` to `outputStack`.
3. This reverses the order of elements.
4. The top element of `outputStack` becomes the front of the queue.
5. If `outputStack` already contains elements, do not transfer anything.

**Important:** We transfer elements only when `outputStack` is empty. This avoids unnecessary operations and provides amortized O(1) time complexity.

---

## 2. Java Solution

```java
import java.util.Stack;

class MyQueue {

    Stack<Integer> inputStack;
    Stack<Integer> outputStack;

    public MyQueue() {
        inputStack = new Stack<>();
        outputStack = new Stack<>();
    }

    public void push(int x) {
        inputStack.push(x);
    }

    public int pop() {
        peek();
        return outputStack.pop();
    }

    public int peek() {

        if (outputStack.isEmpty()) {

            while (!inputStack.isEmpty()) {
                outputStack.push(inputStack.pop());
            }
        }

        return outputStack.peek();
    }

    public boolean empty() {
        return inputStack.isEmpty() && outputStack.isEmpty();
    }
}
```

---

## 3. Code Explanation

### 1. Stack Declaration

```java
Stack<Integer> inputStack;
Stack<Integer> outputStack;
```

We create two stacks:

- `inputStack`: Stores incoming elements.
- `outputStack`: Provides elements in FIFO order.

### 2. Constructor

```java
public MyQueue() {
    inputStack = new Stack<>();
    outputStack = new Stack<>();
}
```

Initializes both stacks as empty.

### 3. Push Operation

```java
public void push(int x) {
    inputStack.push(x);
}
```

Inserts the new element into `inputStack`.

Example:

```text
push(1)
push(2)
push(3)

inputStack = [1, 2, 3]
outputStack = []
```

The top of `inputStack` is 3.

### 4. Peek Operation

```java
public int peek() {

    if (outputStack.isEmpty()) {

        while (!inputStack.isEmpty()) {
            outputStack.push(inputStack.pop());
        }
    }

    return outputStack.peek();
}
```

This operation returns the front element without removing it.

- If `outputStack` is empty, transfer all elements from `inputStack`.
- Otherwise, directly return the top element of `outputStack`.

### 5. Pop Operation

```java
public int pop() {
    peek();
    return outputStack.pop();
}
```

First, `peek()` ensures that the oldest element is available on top of `outputStack`.

Then `pop()` removes and returns that element.

### 6. Empty Operation

```java
public boolean empty() {
    return inputStack.isEmpty() && outputStack.isEmpty();
}
```

The queue is empty only when both stacks are empty.

---

## 4. Algorithm

1. Initialize two empty stacks: `inputStack` and `outputStack`.
2. For `push(x)`:
   - Push element `x` into `inputStack`.
3. For `peek()`:
   - Check if `outputStack` is empty.
   - If empty, move all elements from `inputStack` to `outputStack`.
   - Return the top element of `outputStack`.
4. For `pop()`:
   - Call `peek()` to ensure the front element is available.
   - Remove and return the top element of `outputStack`.
5. For `empty()`:
   - Return true if both stacks are empty.
   - Otherwise, return false.

---

## 5. Step-by-Step Traversal

Let's understand the execution using the following operations:

```text
push(1)
push(2)
push(3)
peek()
pop()
push(4)
peek()
pop()
pop()
```

### Step 1: push(1)

```text
inputStack  = [1]
outputStack = []
```

### Step 2: push(2)

```text
inputStack  = [1, 2]
outputStack = []
```

### Step 3: push(3)

```text
inputStack  = [1, 2, 3]
outputStack = []
```

### Step 4: peek()

`outputStack` is empty, so transfer all elements.

```text
inputStack  = []

outputStack = [3, 2, 1]
                         ↑
                       TOP
```

Returns `1`.

### Step 5: pop()

Removes the top element from `outputStack`.

```text
inputStack  = []

outputStack = [3, 2]
                    ↑
                   TOP
```

Returns `1`.

### Step 6: push(4)

Insert 4 into `inputStack`.

```text
inputStack  = [4]
outputStack = [3, 2]
```

### Step 7: peek()

`outputStack` is not empty, so no transfer happens.

Returns `2`.

### Step 8: pop()

Removes 2.

```text
inputStack  = [4]
outputStack = [3]
```

Returns `2`.

### Step 9: pop()

Removes 3.

```text
inputStack  = [4]
outputStack = []
```

Returns `3`.

**Final Queue:**

```text
Front → [4] ← Rear
```

---

## 6. Complexity Analysis

| Operation | Time Complexity | Explanation |
|---|---|---|
| `push()` | O(1) | Pushes directly into inputStack. |
| `pop()` | Amortized O(1) | Removes from outputStack; transfer happens only when needed. |
| `peek()` | Amortized O(1) | Returns the top element after an optional transfer. |
| `empty()` | O(1) | Checks whether both stacks are empty. |

### Why Amortized O(1)?

Consider inserting `n` elements.

Each element:

1. Is pushed once into `inputStack`.
2. Is transferred once into `outputStack`.
3. Is removed once from `outputStack`.

Therefore, each element is processed at most a constant number of times.

Total operations for `n` elements are O(n).

Thus:

**Amortized Time Complexity: O(1) per operation.**

### Space Complexity

- `inputStack` can store up to n elements.
- `outputStack` can store up to n elements.
- Both stacks together store at most n queue elements.

**Auxiliary Space Complexity: O(n)**

---

## 7. Pattern Used

### Two Stacks + Lazy Transfer Pattern

**Why this pattern?**

A stack follows LIFO (Last In First Out), whereas a queue follows FIFO (First In First Out).

Using two stacks reverses the order twice:

- First stack stores elements in insertion order.
- Transferring elements to the second stack reverses their order.
- The oldest element becomes accessible at the top.

**Key concept:**

```text
Stack 1 (LIFO)
      ↓
   Reverse
      ↓
Stack 2 (LIFO)
      ↓
Queue (FIFO)
```

### Where can you use this pattern?

- Implement Queue using Stacks.
- Understand amortized analysis.
- Reverse data using stacks.
- Solve problems involving order conversion between LIFO and FIFO.
- Design data structures using existing data structures.

---

## 8. Important Interview Points

1. Why do we need two stacks?
   - To convert LIFO behavior into FIFO behavior.

2. Why do we transfer elements only when `outputStack` is empty?
   - To maintain the correct queue order and avoid unnecessary transfers.

3. What is the time complexity of `push()`?
   - O(1).

4. What is the amortized time complexity?
   - O(1) per operation.

5. What is the space complexity?
   - O(n).

6. Which pattern is used?
   - Two Stacks + Lazy Transfer.

---

## 9. Conclusion

We successfully implemented a FIFO Queue using two stacks.

The solution uses the Lazy Transfer technique, where elements are transferred only when necessary.

**Final Complexity:**

- Time: O(1) amortized per operation.
- Space: O(n).

**LeetCode:** 232 – Implement Queue using Stacks

**Topic:** Stack, Queue, Design, Amortized Analysis

**Pattern:** Two Stacks + Lazy Transfer