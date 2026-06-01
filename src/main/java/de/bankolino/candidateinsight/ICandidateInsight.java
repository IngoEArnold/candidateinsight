package de.bankolino.candidateinsight;

import java.util.List;

/**
 * Facade interface for CandidateInsight.
 * All external interactions (from Client and future UI layers) go through
 * this interface. The facade hides the internal collaboration structure
 * from callers and provides a coarse-grained, use-case-aligned API.
 *
 * <p>Design note: the facade pattern here corresponds directly to the
 * CandidateInsight participant in the UC sequence diagrams.</p>
 */
public interface ICandidateInsight {

    /**
     * UC1 - Registers a new user (HIRING_MANAGER or HR_ADMIN).
     * @param name     first name
     * @param surname  last name
     * @param email    unique login email
     * @param password initial password
     * @param role     the user's role
     * @return the newly created User
     */
    User registerUser(String name, String surname, String email, String password, Role role);

    /**
     * UC2 - Authenticates a user by email and password.
     * @param email    login email
     * @param password password
     * @return the authenticated User
     */
    User logIn(String email, String password);

    /**
     * UC3 - Triggers a sync of the candidate pipeline from TalentHive.
     * @param positionId the Bankolino position to sync
     */
    void syncCandidates(String positionId);

    /**
     * UC4 - Returns all candidates in the pipeline for the given position.
     * @param positionId the position ID
     * @return list of candidates
     */
    List<ICandidate> viewPipeline(String positionId);

    /**
     * UC5 (step 1) - Retrieves the dossier for a single candidate.
     * @param positionId  the position the candidate belongs to
     * @param candidateId the candidate to review
     * @return the candidate's dossier
     */
    ICandidate reviewCandidate(String positionId, String candidateId);

    /**
     * UC5 (step 2) - Submits a rating and comment for a candidate.
     * @param positionId  the position the candidate belongs to
     * @param candidateId the candidate being rated
     * @param evaluator   the hiring manager submitting the rating
     * @param rating      the typed rating
     * @param comment     free-text justification
     */
    void rateCandidate(String positionId, String candidateId, User evaluator,
                       EvaluationRating rating, String comment);

    /**
     * UC6 - Returns a ranked evaluation summary for all candidates in a position.
     * @param positionId the position to summarise
     * @return evaluation summary sorted by average score descending
     */
    EvaluationSummary viewEvaluationSummary(String positionId);
}
