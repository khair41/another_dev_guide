# Patters I need to remember

- For unbounded knapsack problems
  - Found in Coin change ii: how to reach out to the conclusion that i need a fixed order on the coins to find number of ways

    - Anytime a problem asks for the "number of ways" to build a target sum using a set of items (coins, numbers, etc.) where:

You can use each item an unlimited number of times.

The order of the items in the solution does not matter (e.g., [1, 2] is the same as [2, 1]).

...your justification will always be those two steps:

Identify the Trap: State that a simple search will find permutations like [1, 2] and [2, 1], but the problem asks for combinations.

Impose an Order: Justify your DP state by explaining that you will "process the items in a fixed order (e.g., the order in the array) to prevent duplicates."

This justification is what leads you directly to the correct recursive state: solve(i, amount). Mastering this "fixed-order processing" pattern is the key to solving this whole family of problems.