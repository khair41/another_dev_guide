# Java Problem-Solving Playbook

Welcome to the Java Problem-Solving Playbook! This is a personal project designed to be a hands-on study guide for mastering common data structures and algorithms (DSA) through practice. It contains a collection of classic coding problems, multiple solutions for each, and a testing framework to verify their correctness.

---

## 🗺️ Project Structure

This repository is organized to separate the testing framework from the problems themselves, making it easy to navigate and expand.

*   **`/src/main/java/com/framework`**: Contains the core testing framework, including the `Problem`, `Solution`, `TestRunner`, and `ProblemRegistry` classes. This is the engine that powers the playbook.
*   **`/src/main/java/com/problems`**: This is where all the coding problems and their solutions live. Each sub-package corresponds to a specific DSA topic.
*   **`/logs`**: Contains development logs that document the step-by-step process of building the framework and solving the problems. See the section below for more details.
*   **`README.md`** (this file): The central entry point and guide to the repository.

---

## 📚 Topics Covered

This playbook is organized by topic. Each topic has its own detailed `README.md` file that serves as a study guide, complete with core concepts, common patterns, and Java-specific tips.

*   **[Arrays & Hashing](./src/main/java/com/problems/arrays_hashing/README.md)**
    *   *A collection of problems focused on array manipulations and the use of HashMaps and HashSets for efficient lookups, frequency counting, and grouping.*

*More topics will be added here as you continue to build out the playbook!* 

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

## 📝 Development Logs

The **`/logs`** directory contains a series of markdown files that act as a public journal of this project's development. They document the thought process, decisions, and steps taken to build the framework and solve the problems, providing insight into how the project has evolved over time.

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
