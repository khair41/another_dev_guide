package com.framework;

import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * A registry to discover and hold all com.problems in the playbook.
 * This version is fully dynamic and uses the Reflections library to automatically
 * scan the `com.problems` package for all classes that implement the Problem interface.
 */
public class ProblemRegistry {

    // A private record to hold the problem and its file path.
    private record ProblemInfo(Problem<?, ?> problem, String path) {}

    private final Map<String, ProblemInfo> problems = new TreeMap<>();

    public ProblemRegistry() {
        Reflections reflections = new Reflections("com.problems");
        Set<Class<? extends Problem>> problemClasses = reflections.getSubTypesOf(Problem.class);

        String projectRoot = System.getProperty("user.dir").replace("\\", "/");

        for (Class<? extends Problem> problemClass : problemClasses) {
            if (problemClass.isAnnotationPresent(ExcludeFromRegistry.class)) {
                continue; // Skip excluded problems
            }

            try {
                Problem<?, ?> problem = problemClass.getDeclaredConstructor().newInstance();
                String path = projectRoot + "/src/main/java/" + problemClass.getName().replace('.', '/') + ".java";
                register(new ProblemInfo(problem, path));
            } catch (Exception e) {
                System.err.println("Error: Failed to instantiate problem: " + problemClass.getName());
                e.printStackTrace();
            }
        }
        System.out.printf("Registry initialized. Found %d problems.%n", problems.size());
    }

    private void register(ProblemInfo problemInfo) {
        String id = problemInfo.problem.getID();
        if (id == null || id.isBlank()) {
            System.err.printf("Warning: Skipping problem with blank ID. Name: %s%n", problemInfo.problem.getName());
            return;
        }
        if (problems.containsKey(id)) {
            System.err.printf("Warning: Duplicate problem ID \"%s\" detected. Overwriting.%n", id);
        }
        problems.put(id, problemInfo);
    }

    public Map<String, Problem<?, ?>> getAllProblems() {
        Map<String, Problem<?, ?>> problemMap = new TreeMap<>();
        for (Map.Entry<String, ProblemInfo> entry : problems.entrySet()) {
            problemMap.put(entry.getKey(), entry.getValue().problem());
        }
        return problemMap;
    }

    public Problem<?, ?> getProblem(String id) {
        ProblemInfo info = problems.get(id);
        return (info != null) ? info.problem() : null;
    }

    public void runAll() {
        List<ProblemInfo> passedProblems = new ArrayList<>();
        List<ProblemInfo> failedProblems = new ArrayList<>();

        for (ProblemInfo problemInfo : problems.values()) {
            boolean passed = TestRunner.run(problemInfo.problem());
            if (passed) {
                passedProblems.add(problemInfo);
            } else {
                failedProblems.add(problemInfo);
            }
        }

        // --- Final Summary ---
        System.out.println("\n================================================================================");
        System.out.println("                        RUN ALL SUMMARY");
        System.out.println("================================================================================");

        if (failedProblems.isEmpty()) {
            System.out.println("\n✅✅✅  All problems passed! ✅✅✅\n");
        } else {
            System.out.println("\n--- ❌ FAILED PROBLEMS ---");
            for (ProblemInfo info : failedProblems) {
                System.out.printf("  - [%s] %s\n", info.problem().getID(), info.problem().getName());
                System.out.printf("    Path: %s\n", info.path());
            }
        }

        if (!passedProblems.isEmpty()) {
            System.out.println("\n--- ✅ PASSED PROBLEMS ---");
            for (ProblemInfo info : passedProblems) {
                System.out.printf("  - [%s] %s\n", info.problem().getID(), info.problem().getName());
            }
        }
        System.out.println("\n================================================================================");
    }
}
