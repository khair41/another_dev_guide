import com.framework.Problem;
import com.framework.ProblemRegistry;
import com.framework.TestRunner;

import java.util.Map;
import java.util.Scanner;

/**
 * The main entry point for the entire Problem-Solving Playbook.
 * This class provides a command-line interface to discover and run tests for any
 * problem registered in the ProblemRegistry.
 */
public class main {

    public static void main(String[] args) {
        ProblemRegistry registry = new ProblemRegistry();
        Scanner scanner = new Scanner(System.in);

        // Main application loop
        while (true) {
            System.out.println("\n====== Java Problem-Solving Playbook ======");
            Map<String, Problem<?, ?>> problems = registry.getAllProblems();

            if (problems.isEmpty()) {
                System.out.println("No com.problems are registered yet. Please register a problem in ProblemRegistry.java");
                break;
            }

            System.out.println("Available Problems:");
            // Display all registered com.problems in a formatted way.
            problems.forEach((id, problem) -> System.out.printf("  - [%s]: %s%n", id, problem.getName()));

            System.out.print("\nEnter a problem ID to run (or 'all', 'exit'): ");
            String choice = scanner.nextLine();

            if ("exit".equalsIgnoreCase(choice)) {
                break;
            }

            if ("all".equalsIgnoreCase(choice)) {
                registry.runAll();
                continue; // Loop back to the menu
            }

            Problem<?, ?> selectedProblem = registry.getProblem(choice);

            if (selectedProblem == null) {
                System.out.println("\nInvalid ID. Please try again.");
                continue;
            }

            // Use the generic TestRunner to run the selected problem.
            TestRunner.run(selectedProblem);
        }

        scanner.close();
        System.out.println("\nGoodbye!");
    }
}
