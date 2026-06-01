package de.bankolino.candidateinsight;

/**
 * Represents a single evaluation of a candidate by a hiring manager.
 * Immutable: once submitted, an evaluation cannot be changed.
 *
 * <p>Rationale: audit compliance requires that evaluation history
 * is preserved without modification.</p>
 */
public class Evaluation {

    private final User evaluator;
    private final EvaluationRating rating;
    private final String comment;

    /**
     * @param evaluator the hiring manager who submitted this evaluation
     * @param rating    the typed rating (STRONG_YES to STRONG_NO)
     * @param comment   free-text justification for the rating
     */
    public Evaluation(User evaluator, EvaluationRating rating, String comment) {
        this.evaluator = evaluator;
        this.rating    = rating;
        this.comment   = comment;
    }

    /** @return the evaluator */
    public User getEvaluator() { return evaluator; }

    /** @return the typed rating */
    public EvaluationRating getRating() { return rating; }

    /** @return free-text comment */
    public String getComment() { return comment; }

    @Override
    public String toString() {
        return evaluator.getName() + " " + evaluator.getSurname()
            + ": " + rating + " (score=" + rating.getScore() + ") - \"" + comment + "\"";
    }
}
