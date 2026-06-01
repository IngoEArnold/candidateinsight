package de.bankolino.candidateinsight;

import java.util.List;

/**
 * Driver class that demonstrates the CandidateInsight core use cases
 * (Iteration #1) in a single end-to-end scenario.
 *
 * <p>Scenario (as specified in the exercise):
 * <ol>
 *   <li>UC1 - HR Admin Carol registers Alice (HiringManager) and Bob (HiringManager)</li>
 *   <li>UC2 - All three users log in</li>
 *   <li>UC3 - Carol syncs the candidate pipeline for position SWE-001</li>
 *   <li>UC4 - Alice views the candidate pipeline</li>
 *   <li>UC5 - Alice rates Max Mueller (C001) and Sara Schmidt (C002)</li>
 *   <li>UC5 - Bob rates Max Mueller (C001)</li>
 *   <li>UC6 - Alice views the evaluation summary for SWE-001</li>
 * </ol>
 * </p>
 */
public class Client {

    /**
     * Entry point: assembles the application and runs the scenario.
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        // Assemble the application via the Composer
        ICandidateInsight ci = Composer.compose();

        System.out.println("\n====== CandidateInsight - Iteration #1 Demo ======\n");

        // UC1: Register users
        System.out.println("--- UC1: Register users ---");
        ci.registerUser("Carol", "Meier",  "carol@bankolino.com", "pass1", Role.HR_ADMIN);
        ci.registerUser("Alice", "Bauer",  "alice@bankolino.com", "pass2", Role.HIRING_MANAGER);
        ci.registerUser("Bob",   "Keller", "bob@bankolino.com",   "pass3", Role.HIRING_MANAGER);

        // UC2: Log in
        System.out.println("\n--- UC2: Log in ---");
        User carol = ci.logIn("carol@bankolino.com", "pass1");
        User alice = ci.logIn("alice@bankolino.com", "pass2");
        User bob   = ci.logIn("bob@bankolino.com",   "pass3");

        // UC3: Sync candidate pipeline (Carol triggers as HR Admin)
        System.out.println("\n--- UC3: Sync candidate pipeline (position SWE-001) ---");
        ci.syncCandidates("SWE-001");

        // UC4: Alice views the pipeline
        System.out.println("\n--- UC4: Alice views pipeline for SWE-001 ---");
        List<ICandidate> pipeline = ci.viewPipeline("SWE-001");
        for (ICandidate c : pipeline) {
            System.out.println("  " + c);
        }

        // UC5: Alice reviews and rates candidates
        System.out.println("\n--- UC5: Alice reviews and rates candidates ---");

        ICandidate maxMueller = ci.reviewCandidate("SWE-001", "C001");
        System.out.println("  Alice reviews: " + maxMueller);
        ci.rateCandidate("SWE-001", "C001", alice,
            EvaluationRating.YES, "Strong technical skills");

        ICandidate saraSchmidt = ci.reviewCandidate("SWE-001", "C002");
        System.out.println("  Alice reviews: " + saraSchmidt);
        ci.rateCandidate("SWE-001", "C002", alice,
            EvaluationRating.STRONG_YES, "Excellent culture fit and technical depth");

        // UC5: Bob rates Max Mueller
        System.out.println("\n--- UC5: Bob rates Max Mueller ---");
        ci.rateCandidate("SWE-001", "C001", bob,
            EvaluationRating.NEUTRAL, "Good but not outstanding");

        // UC6: Alice views evaluation summary
        System.out.println("\n--- UC6: Alice views evaluation summary for SWE-001 ---");
        EvaluationSummary summary = ci.viewEvaluationSummary("SWE-001");
        summary.display();

        System.out.println("\n====== End of Demo ======\n");
    }
}
