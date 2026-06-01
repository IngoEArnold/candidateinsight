package de.bankolino.candidateinsight;

import java.util.List;

/**
 * Interface for a candidate pipeline: the collection of candidates
 * for a single open position, as synced from TalentHive.
 */
public interface ICandidatePipeline {

    /**
     * Replaces the current candidate list with freshly synced data from TalentHive.
     * @param candidates list of candidates received from TalentHiveConnector
     */
    void updateCandidates(List<ICandidate> candidates);

    /** @return all candidates currently in this pipeline */
    List<ICandidate> getCandidates();

    /**
     * Retrieves a single candidate by ID.
     * @param candidateId the candidate to retrieve
     * @return the candidate
     * @throws IllegalArgumentException if candidateId is unknown
     */
    ICandidate getCandidate(String candidateId);

    /**
     * Computes an EvaluationSummary ranking all candidates by average score.
     * @return evaluation summary for this pipeline's position
     */
    EvaluationSummary getEvaluationSummary();
}
