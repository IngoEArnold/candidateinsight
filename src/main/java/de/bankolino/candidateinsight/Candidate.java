package de.bankolino.candidateinsight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Concrete implementation of a candidate dossier.
 * In Iteration #1, candidate data originates from TalentHive (via sync).
 * In Iteration #2, this class is extended to support a multi-stage evaluation workflow.
 */
public class Candidate implements ICandidate {

    private final String candidateId;
    private final String name;
    private final String surname;
    private final String positionId;

    /**
     * Mutable list of evaluations: appended as hiring managers submit ratings.
     * Not synchronised - single-threaded use assumed for this prototype.
     */
    private final List<Evaluation> evaluations = new ArrayList<Evaluation>();

    /**
     * @param candidateId unique identifier (from TalentHive)
     * @param name        first name
     * @param surname     last name
     * @param positionId  the position this candidate applied for
     */
    public Candidate(String candidateId, String name, String surname, String positionId) {
        this.candidateId = candidateId;
        this.name        = name;
        this.surname     = surname;
        this.positionId  = positionId;
    }

    @Override public String getCandidateId() { return candidateId; }
    @Override public String getName()        { return name; }
    @Override public String getSurname()     { return surname; }
    @Override public String getPositionId()  { return positionId; }

    /**
     * Adds an evaluation to the candidate's dossier.
     * @param evaluation evaluation submitted by a hiring manager
     */
    @Override
    public void addEvaluation(Evaluation evaluation) {
        // TODO [Unit03 - Step 3a]: Add the evaluation to the evaluations list.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns an unmodifiable view of all evaluations.
     *
     * <p>Hint: use {@link Collections#unmodifiableList(List)} to prevent
     * callers from modifying the internal list directly.</p>
     *
     * @return read-only list of evaluations
     */
    @Override
    public List<Evaluation> getEvaluations() {
        // TODO [Unit03 - Step 3b]: Return an unmodifiable view of evaluations.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Computes the arithmetic mean of all evaluation scores.
     * Returns 0.0 if no evaluations have been submitted.
     *
     * <p>Hint: use a for-each loop (no streams). Call
     * {@link EvaluationRating#getScore()} on each evaluation's rating.</p>
     *
     * @return average score between 1.0 and 5.0, or 0.0 if unrated
     */
    @Override
    public double getAverageScore() {
        // TODO [Unit03 - Step 3c]: Compute the average score.
        //   1. Return 0.0 if evaluations is empty.
        //   2. Sum the scores using a for-each loop over evaluations.
        //   3. Return (double) total / evaluations.size().
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String toString() {
        return "[" + candidateId + "] " + name + " " + surname
            + " (position: " + positionId + ", evaluations: " + evaluations.size() + ")";
    }
}
