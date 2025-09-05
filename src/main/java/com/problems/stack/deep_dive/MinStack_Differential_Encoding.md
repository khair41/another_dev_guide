# Deep Dive: Advanced Stack Solutions for "Min Stack"

This document explores the more advanced and optimized solutions for the "Min Stack" problem. While a two-stack approach is intuitive, the single-stack solutions demonstrate clever patterns for space optimization and state management.

---

## The Core Challenge: Restoring State

The main difficulty in designing a Min Stack is not the `push`, `pop`, or `top` operations; it's handling `getMin` in O(1) time. Specifically, how do you know what the *previous* minimum was after the *current* minimum is popped from the stack?

---

## Solution: Differential Encoding on a Stack

This is a highly optimized and clever solution that relies on a mathematical trick. The core idea is that the stack **does not store the actual values**. Instead, it stores the **difference** between the pushed value and the `min` value at that time. The sign of this difference becomes a powerful signal.

### Why This is Viable: The LIFO Property

The key here is the **LIFO (Last-In, First-Out)** property of a stack. It guarantees that operations are undone in the perfect reverse order of how they were applied. The `top` of the stack always represents the **most recent state change**. When we `pop`, we are always processing the most recent change, allowing us to reliably "rewind" the state of our `min` variable one step at a time.

### The Logic: Encoding and Decoding State

The sign of the stored difference acts as a signal to tell us how to interpret the data.

*   **Case 1: Stored value is NON-NEGATIVE (`diff >= 0`)**
    *   **What it means**: The pushed value `val` was greater than or equal to the `min` when it was pushed. The `min` value did **not** change.
    *   **Example**: `min` is **-5**. We `push(val = -3)`. The stored `diff` is `-3 - (-5) = 2`. The `min` remains -5.

*   **Case 2: Stored value is NEGATIVE (`diff < 0`)**
    *   **What it means**: This is a special **MARKER**. The pushed value `val` was a new absolute minimum.
    *   **Example**: `min` is **-5**. We `push(val = -8)`. The stored `diff` is `-8 - (-5) = -3`. We then update `min` to -8.

### Example Trace: `push(-5)`, `push(-8)`, `pop()`

1.  **`push(-5)`**: `min` becomes **-5**. We push `0`.
    *   **State**: `stack: [0]`, `min: -5`
2.  **`push(-8)`**: New value is less than `min`. We push `val - min` -> `-8 - (-5) = -3`. We update `min` to **-8**.
    *   **State**: `stack: [0, -3]`, `min: -8`
3.  **`pop()`**: We pop `-3`. It's negative, which is our signal. We restore the previous minimum: `min = min - diff` -> `-8 - (-3) = -5`.
    *   **State**: `stack: [0]`, `min: -5`

---

### Pattern Classification

*   **State Encoding on a Stack**: The most accurate description. We are encoding information about the system's state (`min`) into the values we store.
*   **Lazy State Restoration**: We defer the work of figuring out the previous minimum until the last possible moment—only when a `pop` operation forces us to.
*   **In-Band Signaling**: The negative numbers act as a special command or "signal" mixed in with the regular data, telling the `pop` method to perform a special state-restoration action.

#### Is This a Form of Masking?

This is an excellent question. While it feels similar to masking because it overloads a single variable with multiple meanings, it is technically different.

*   **Bitmasking** uses **bitwise operations** (`&`, `|`, `^`) to pack multiple independent flags or values into the bits of a single number. Think of it like using the bits of an integer as a panel of on/off switches.
*   **Our Solution** uses **arithmetic operations** (`-`, `+`) to store a *relationship* between a value and a changing baseline (`min`).

So, while not a form of masking, it shares the same clever spirit of using a single piece of data to represent more than one piece of information.
