package de.bankolino.candidateinsight;

import java.util.List;

/**
 * Interface for a candidate dossier.
 * Mediates all access to Candidate from CandidatePipeline and CandidateInsight.
 *
 * <p>Design note: programming to an interface allows the Candidate implementation
 * to be replaced (e.g., with a remote-fetched DTO) without changing callers.</p>
 */
public interface ICandidate {

    /** @return unique candidate identifier (assigned by TalentHive) */
    String getCandidateId();

    /** @return candidate's first name */
    String getName();

    /** @return candidate's last name */
    String getSurname();

    /** @return the position this candidate applied for */
    String getPositionId();

    /**
     * Adds an evaluation to this candidate's dossier.
     * @param evaluation the evaluation to record
     */
    void addEvaluation(Evaluation evaluation);

    /** @return all evaluations submitted for this candidate */
    List<Evaluation> getEvaluations();

    /**
     * Computes the average numeric score across all evaluations.
     * @return average score (0.0 if no evaluations exist)
     */
    double getAverageScore();
}
