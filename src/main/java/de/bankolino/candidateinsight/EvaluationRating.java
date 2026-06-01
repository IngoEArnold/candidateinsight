package de.bankolino.candidateinsight;

/**
 * Typed evaluation rating for a candidate.
 * Each constant carries a numeric score (1-5) to enable
 * quantitative ranking in the EvaluationSummary.
 *
 * <p>Design note: using an enum instead of raw ints makes the business
 * intent explicit and prevents out-of-range values at compile time.</p>
 */
public enum EvaluationRating {
    /** Strongest positive rating: hire without reservation. */
    STRONG_YES(5),
    /** Positive rating: recommend hiring. */
    YES(4),
    /** Neutral: acceptable but not outstanding. */
    NEUTRAL(3),
    /** Negative: lean against hiring. */
    NO(2),
    /** Strongest negative: do not hire. */
    STRONG_NO(1);

    private final int score;

    EvaluationRating(int score) {
        this.score = score;
    }

    /**
     * Returns the numeric score (1-5) for ranking calculations.
     * @return numeric score
     */
    public int getScore() {
        return score;
    }
}
