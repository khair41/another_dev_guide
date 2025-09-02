# Java Problem-Solving Playbook

Welcome to the Java Problem-Solving Playbook! This is a personal project designed to be a hands-on study guide for mastering common data structures and algorithms (DSA) through practice. It contains a collection of classic coding problems, multiple solutions for each, and a testing framework to verify their correctness.

---

## 🗺️ Project Structure

This repository is organized to separate the core concepts (Data Structures and Algorithmic Patterns) from the problems that use them.

*   **`/data_structures`**: Contains detailed study guides for each core data structure (e.g., Arrays, Linked Lists).
*   **`/patterns`**: Contains detailed study guides for each core algorithmic pattern (e.g., Two Pointers, Sliding Window).
*   **`/src/main/java/com/framework`**: The core testing framework that powers the playbook.
*   **`/src/main/java/com/problems`**: Where all the coding problems and their solutions live, organized by topic.
*   **`/logs`**: A public journal documenting the development of this project.

---

## 📚 Study Guides

This playbook is organized into two main categories of study guides:

*   **[Data Structures](./data_structures/README.md)**
    *   *Guides explaining the fundamental ways of storing and organizing data, focusing on their trade-offs (e.g., Arrays, Hash Tables).*

*   **[Algorithmic Patterns](./patterns/README.md)**
    *   *Guides explaining reusable strategies for solving classes of problems, focusing on techniques that manipulate data (e.g., Two Pointers, Binary Search).*

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
