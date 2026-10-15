# Validate Phone Numbers

## Problem

Given a text file `file.txt` containing one phone number per line, print only the valid phone numbers.

A valid phone number must match one of these formats:

1. `xxx-xxx-xxxx`
2. `(xxx) xxx-xxxx`

Where `x` represents a digit.

### Example

Input:

```text
987-123-4567
123 456 7890
(123) 456-7890
```

Output:

```text
987-123-4567
(123) 456-7890
```

---

## Solution

```bash
grep -E '^([0-9]{3}-[0-9]{3}-[0-9]{4}|\([0-9]{3}\) [0-9]{3}-[0-9]{4})$' file.txt
```

---

## Approach

We use `grep` with the `-E` option to apply an Extended Regular Expression (ERE).

The regular expression checks whether the **entire line** matches either of the two allowed phone-number formats.

### Pattern

```text
^([0-9]{3}-[0-9]{3}-[0-9]{4}|\([0-9]{3}\) [0-9]{3}-[0-9]{4})$
```

### Pattern Breakdown

#### `^`

Matches the beginning of the line.

This prevents extra characters before the phone number.

#### `[0-9]{3}`

Matches exactly 3 digits.

Example:

```text
987
```

#### `-`

Matches a literal hyphen.

#### `[0-9]{4}`

Matches exactly 4 digits.

Example:

```text
4567
```

#### `\(` and `\)`

Match literal parentheses.

Parentheses normally have a special meaning in regular expressions, so they are escaped.

#### `[0-9]{3} [0-9]{3}`

Matches:

```text
123 456
```

There is exactly one space between the groups.

#### `|`

Means **OR**.

So the regex accepts either:

```text
xxx-xxx-xxxx
```

or:

```text
(xxx) xxx-xxxx
```

#### `$`

Matches the end of the line.

This ensures that the complete line must be a valid phone number.

---

## Algorithm

1. Read each line from `file.txt`.
2. Apply the regular expression to the complete line.
3. Check whether the line matches `xxx-xxx-xxxx`.
4. If not, check whether it matches `(xxx) xxx-xxxx`.
5. Print the line only when one of the formats matches.
6. Ignore all other lines.

---

## Step-by-Step Traversal

Suppose the file contains:

```text
987-123-4567
123 456 7890
(123) 456-7890
```

### Line 1

```text
987-123-4567
```

Matches:

```text
xxx-xxx-xxxx
```

Result: **Valid → Print**

### Line 2

```text
123 456 7890
```

Does not match either allowed format.

Result: **Invalid → Skip**

### Line 3

```text
(123) 456-7890
```

Matches:

```text
(xxx) xxx-xxxx
```

Result: **Valid → Print**

Final output:

```text
987-123-4567
(123) 456-7890
```

---

## Pattern Used

### Regular Expression (Regex)

The main pattern used is:

```text
^...$
```

with alternatives using:

```text
|
```

### Why Regex?

Regex is ideal here because the problem is based entirely on a fixed text pattern.

We need to verify:

- exact number of digits
- exact placement of `-`
- exact placement of parentheses
- exact space
- complete-line matching

Regex can express all these rules in a single command.

---

## Why `grep -E`?

`grep` searches text using patterns.

The `-E` option enables **Extended Regular Expressions**, which makes operators such as:

```text
()
|
{}
```

easier to use.

Therefore, `grep -E` is a natural choice for this problem.

---

## Time Complexity

Let `n` be the number of lines in `file.txt`.

Each line contains a phone number-sized string, so the regex checks only a small fixed number of characters.

Therefore:

```text
Time Complexity: O(n)
```

More generally, if `N` is the total number of characters in the file:

```text
Time Complexity: O(N)
```

---

## Space Complexity

`grep` processes the input line by line and does not need to store the entire file.

Therefore:

```text
Space Complexity: O(1)
```

apart from small internal buffers used by the command.

---

## Final One-Liner

```bash
grep -E '^([0-9]{3}-[0-9]{3}-[0-9]{4}|\([0-9]{3}\) [0-9]{3}-[0-9]{4})$' file.txt
```

## Key Takeaways

- `grep` → searches/filter lines.
- `-E` → enables Extended Regular Expressions.
- `^` → start of line.
- `$` → end of line.
- `[0-9]{3}` → exactly 3 digits.
- `[0-9]{4}` → exactly 4 digits.
- `|` → OR.
- `\(` and `\)` → literal parentheses.
- The complete-line anchors ensure that partial matches are not accepted.
