# Java Problem-Solving Playbook

Welcome to the Java Problem-Solving Playbook! This is a personal project designed to be a hands-on study guide for mastering common data structures and algorithms (DSA) through practice. It contains a collection of classic coding problems, multiple solutions for each, and a testing framework to verify their correctness.

---

## 🗺️ Project Structure

This repository is organized to separate the core concepts (Data Structures and Algorithmic Patterns) from the problems that use them.

*   **`/data_structures`**: Contains high-level study guides for each core data structure (e.g., Arrays, Linked Lists).
*   **`/patterns`**: Contains a library of clean, reusable code templates for common algorithmic patterns.
*   **`/src/main/java/com/framework`**: The core testing framework that powers the playbook.
*   **`/src/main/java/com/problems`**: Where all the coding problems and their solutions live, organized by topic.
*   **`/logs`**: A public journal documenting the development of this project.

---

## 📚 Topics Covered

This playbook is organized by topic. Each topic has its own detailed `README.md` file that serves as a study guide, complete with core concepts, common patterns, and a checklist of solved and practice problems.

*   **[Arrays & Hashing](./src/main/java/com/problems/arrays_hashing/README.md)**
*   **[Two Pointers](./src/main/java/com/problems/two_pointers/README.md)**
*   **[Stack](./src/main/java/com/problems/stack/README.md)**

*More topics will be added here as the playbook grows!* 

---

## 🚀 How to Use This Framework

This project is built around a simple, interactive command-line interface.

1.  **Run the `main.java` file** to start the application.
2.  You will be greeted with a menu listing all the problems that have been discovered by the framework.

### Menu Options

*   **Enter a Problem ID**: Type the ID of a problem (e.g., `0001` for Two Sum) to run all of its solutions and see a detailed test report.
*   **Type `all`**: This will run the test suite for **every single registered problem** and provide a final summary of all passed and failed problems.
*   **Type `exit`**: This will close the application.

---

## ✨ How to Add a New Problem

Adding a new problem is designed to be easy using the built-in generator.

1.  **Activate the AI Assistant**.
2.  **Provide a prompt** like: `"create the template for [Problem Name]"`.
3.  The assistant will ask for a few key details (input/output types, primary topic, etc.) and then automatically generate the necessary `Problem` and `Solution` class files in the correct directories.

### Excluding a Problem from the Menu

If you are still working on a problem and don't want it to appear in the menu or be included in the `all` command, simply add the `@ExcludeFromRegistry` annotation above the problem class definition:

```java
@ExcludeFromRegistry
public class MyWorkInProgressProblem implements Problem<...>
```

Remove the annotation when you are ready to include it.

### TODO
- Investigate
- Tail recursion
- Lazy evaluation? to achieve constant time in increment operation in:
    - https://leetcode.com/problems/design-a-stack-with-increment-operation/
    - you dont need to calculate and store all the values before k, in case you want to read a value with an increment
    - you simply store at k position what is the increment, then you assume that the values before that element
    - should include the increment at k, if you pop the element that is storing the increment, move the increment to the
    - last known position.

- Kadane algorithm
