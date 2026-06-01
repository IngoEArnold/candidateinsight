package de.bankolino.candidateinsight;

import java.util.List;

/**
 * Read-only snapshot of evaluation results for a position.
 * Contains candidates sorted by their average score (descending).
 * Created by CandidatePipeline on demand; not persisted.
 */
public class EvaluationSummary {

    private final String positionId;

    /** Candidates sorted by average score, highest first. */
    private final List<ICandidate> rankedCandidates;

    /**
     * @param positionId       the position this summary is for
     * @param rankedCandidates candidates sorted by descending average score
     */
    public EvaluationSummary(String positionId, List<ICandidate> rankedCandidates) {
        this.positionId       = positionId;
        this.rankedCandidates = rankedCandidates;
    }

    /** @return the position ID */
    public String getPositionId() { return positionId; }

    /** @return candidates in descending average-score order */
    public List<ICandidate> getRankedCandidates() { return rankedCandidates; }

    /**
     * Prints the evaluation summary to stdout.
     * In a real application this would return a DTO for the UI layer.
     */
    public void display() {
        System.out.println("=== Evaluation Summary: Position " + positionId + " ===");
        if (rankedCandidates.isEmpty()) {
            System.out.println("  No evaluated candidates yet.");
            return;
        }
        int rank = 1;
        // Iterate over ranked candidates and print their evaluations
        for (ICandidate c : rankedCandidates) {
            System.out.printf("  #%d  %-20s  avg score: %.2f%n",
                rank++, c.getName() + " " + c.getSurname(), c.getAverageScore());
            for (Evaluation e : c.getEvaluations()) {
                System.out.println("       +-- " + e);
            }
        }
        System.out.println("=========================================");
    }
}
