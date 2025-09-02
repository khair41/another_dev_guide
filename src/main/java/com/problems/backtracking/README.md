# Dev Log - Session 1

**Today's Goal:** Get the project set up and really dive into recursion.

---

## 1. Getting the Project Set Up

*   ### What I did:
    Set up my Java problem-solving library and fixed some annoying Git issues that popped up right away.

*   ### Why I did it:
    To get a clean, safe place to keep all my solved com.problems. Getting Git right from the start saves a ton of headaches later.

*   ### How I did it:
    *   **Config:** Made a `.gitignore` to keep compiled code (`.class`) and IDE junk (`.idea/`, `.vscode/`) out of my repo. Wrote a `README.md` to remind myself what this project is for.
    *   **Git Fix #1 (Branch Name):** My local branch was `master` but GitHub's was `main`. Renamed mine with `git branch -m master main` to fix the push error.
    *   **Git Fix #2 (Weird History):** Got a `fatal: refusing to merge unrelated histories` error. This happens when you make the first commit locally AND on GitHub. Fixed it by forcing a merge with `git pull origin main --allow-unrelated-histories`.

---

## 2. Diving into Recursion & Backtracking

*   ### What I looked at:
    The main ideas behind recursion and the backtracking pattern. I practiced by building and fixing functions to sort a queue and print all subsets of a set.

*   ### Why bother?
    To really get my head around solving com.problems by breaking them down. This is super important for harder stuff like Dynamic Programming (DP). The main goal was to get comfortable with how these patterns work and their pros and cons.

*   ### How it works:
    *   **Recursion (The basic idea):**
        1.  **Base Case:** Find the simplest version of the problem you can solve directly (like an empty array or `n=0`). This is your exit.
        2.  **Recursive Step:** Figure out how to use the solution for a slightly smaller problem to solve the one you're on now. The trick is to always get closer to the base case.
        *   *Lightbulb moment:* The call stack handles keeping track of "where you are" for you. That's why recursion can feel cleaner than writing a loop with a bunch of tracking variables.

    *   **Backtracking (A specific recipe for recursion):**
        1.  **Choose:** Try one option.
        2.  **Explore:** Recurse to see what happens.
        3.  **Un-choose:** Backtrack by undoing your choice. This is the magic step. It lets you go back and try another option as if the first one never happened.

---

## 3. Useful Stuff & Templates

*   **Code:**
    *   [Recursion Examples](../src/main/java/com/practice/coaching/recursion.java)
    *   [Backtracking Template](../src/main/java/com/practice/coaching/BacktrackingProblemTemplate.java)

*   **Backtracking Cheat Sheet:**
    ```pseudocode
    function backtrack(current_path, all_solutions, other_stuff):

        // Is this path a full solution?
        if is_a_solution(current_path):
            add a copy of current_path to all_solutions
            return

        // Try all possible next steps
        for each choice in get_possible_choices(other_stuff):

            // 1. CHOOSE
            add choice to current_path

            // 2. EXPLORE
            backtrack(current_path, all_solutions, other_stuff)

            // 3. UN-CHOOSE (this is the backtrack!)
            remove choice from current_path
    ```


TODO



https://leetcode.com/problems/combinations/description/


https://leetcode.com/problems/find-the-punishment-number-of-an-integer/description/
https://leetcode.com/problems/minimum-time-to-break-locks-i/description/

Hard
https://leetcode.com/problems/next-special-palindrome-number/description/ 
