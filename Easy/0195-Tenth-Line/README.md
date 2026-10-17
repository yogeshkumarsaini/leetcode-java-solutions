# Print Tenth Line

## Problem

Given a text file `file.txt`, print only the **10th line** of the file.

If the file contains fewer than 10 lines, nothing should be printed.

### Example

`file.txt`:

```text
Line 1
Line 2
Line 3
Line 4
Line 5
Line 6
Line 7
Line 8
Line 9
Line 10
```

Output:

```text
Line 10
```

---

## Solution

```bash
sed -n '10p' file.txt
```

## Approach

We use the Linux/Unix `sed` command to read the file and print only a specific line.

The command:

```bash
sed -n '10p' file.txt
```

has three important parts:

- `sed` → Stream editor used to process text line by line.
- `-n` → Prevents `sed` from printing every line automatically.
- `10p` → `10` means line number 10 and `p` means print.
- `file.txt` → Input file.

Because of `-n`, only the line explicitly selected by `10p` is printed.

---

## Step-by-Step Traversal

Suppose the file contains 10 lines:

```text
Line 1
Line 2
Line 3
Line 4
Line 5
Line 6
Line 7
Line 8
Line 9
Line 10
```

### Step 1
`sed` starts processing `file.txt`.

### Step 2
It processes the lines sequentially:

```text
Line 1
Line 2
Line 3
...
```

### Step 3
When it reaches line 10, the command:

```text
10p
```

matches that line.

### Step 4
`p` prints:

```text
Line 10
```

### Step 5
Because `-n` is enabled, the other lines are not printed.

Final output:

```text
Line 10
```

---

## Algorithm

1. Open/read `file.txt` using `sed`.
2. Suppress normal automatic output using `-n`.
3. Select line number `10`.
4. Print the selected line using `p`.
5. If line 10 does not exist, print nothing.

### Pseudocode

```text
START
    Read file.txt
    Suppress automatic output
    Select line 10
    Print line 10
END
```

---

## Why `sed`?

`sed` is a good choice because this problem is specifically about selecting a line from a text stream.

Instead of writing a loop or loading the complete file into memory, we can directly express:

```bash
10p
```

which means:

> Print line 10.

---

## Pattern Used

### Pattern: Sequential Text Processing / Stream Processing

`sed` processes the input as a stream of text, line by line.

This pattern is useful when:

- Working with text files.
- Selecting specific lines.
- Filtering or transforming text.
- Processing large files without storing the entire file in memory.

For this problem, we only need one specific line, so stream processing is a natural fit.

---

## Complexity Analysis

Let `n` be the total number of lines in the file.

### Time Complexity

```text
O(n)
```

`sed` processes the input sequentially. In the general case, it may read through the file until it reaches the 10th line or reaches EOF.

For a fixed 10th line, the amount of useful work is effectively constant, but the command-line tool still operates as a stream over the input.

### Space Complexity

```text
O(1)
```

`sed` does not need to store the complete file in memory. It processes the input as a stream and uses constant extra space for this operation.

---

## Important Note: Fewer Than 10 Lines

If `file.txt` contains fewer than 10 lines:

```bash
sed -n '10p' file.txt
```

prints nothing.

For example:

```text
Line 1
Line 2
Line 3
```

Output:

```text
(no output)
```

This satisfies the problem requirement.

---

## Other Possible Solutions

The problem can be solved in multiple ways.

### 1. `sed`

```bash
sed -n '10p' file.txt
```

Recommended because it directly expresses the requirement.

### 2. `awk`

```bash
awk 'NR==10 {print}' file.txt
```

Explanation:

- `NR` = current record/line number.
- `NR==10` = select the 10th line.
- `{print}` = print that line.

### 3. `head` + `tail`

```bash
head -n 10 file.txt | tail -n 1
```

Explanation:

1. `head -n 10` gets the first 10 lines.
2. `tail -n 1` gets the last line from those 10 lines.
3. Therefore, the result is line 10.

### Comparison

| Method | Command | Main Idea |
|---|---|---|
| `sed` | `sed -n '10p' file.txt` | Select line 10 directly |
| `awk` | `awk 'NR==10 {print}' file.txt` | Check line number |
| `head + tail` | `head -n 10 file.txt \| tail -n 1` | Get first 10, then last |

---

## Best Solution

```bash
sed -n '10p' file.txt
```

### Why?

- Short and clean.
- Easy to understand.
- Directly selects line 10.
- Uses standard Unix/Linux text-processing tools.
- Produces no output when fewer than 10 lines exist.

---

## GitHub

Save the solution as:

```text
README.md
```

The main solution is:

```bash
sed -n '10p' file.txt
```
