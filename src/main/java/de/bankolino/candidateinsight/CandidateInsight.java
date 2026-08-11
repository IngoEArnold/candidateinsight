package de.bankolino.candidateinsight;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Central facade of the CandidateInsight application.
 * Coordinates UserManagement, TalentHiveConnector, and CandidatePipeline
 * to implement the 6 use cases defined in Iteration #1.
 *
 * <p>Architecture note: the facade delegates all real work to collaborators
 * accessed via interfaces. CandidateInsight never instantiates collaborators
 * itself - they are injected via the constructor (Dependency Injection).</p>
 */
public class CandidateInsight implements ICandidateInsight {

    private final IUserManagement userManagement;
    private final ITalentHiveConnector talentHiveConnector;

    /**
     * Map from positionId to its CandidatePipeline.
     * A pipeline is created lazily on the first sync for a given position.
     */
    private final Map<String, ICandidatePipeline> pipelines = new HashMap<String, ICandidatePipeline>();

    /**
     * Constructor injection: all dependencies are provided externally (by Composer).
     * @param userManagement      the user management component
     * @param talentHiveConnector the TalentHive integration component
     */
    public CandidateInsight(IUserManagement userManagement,
                            ITalentHiveConnector talentHiveConnector) {
        this.userManagement      = userManagement;
        this.talentHiveConnector = talentHiveConnector;
    }

    // --- UC1: Register User (given as example of delegation pattern) --------------

    @Override
    public User registerUser(String name, String surname, String email,
                             String password, Role role) {
        // Delegate directly to the UserManagement component (no logic in the facade)
        return userManagement.registerUser(name, surname, email, password, role);
    }

    // --- UC2: Log In (given as example of value object usage) --------------------

    @Override
    public User logIn(String email, String password) {
        // Wrap credentials in a value object and delegate to UserManagement
        return userManagement.authenticate(new Credentials(email, password));
    }

    // --- UC3: Sync Candidate Pipeline --------------------------------------------

    /**
     * Fetches candidates from TalentHive and stores them in a CandidatePipeline.
     *
     * <p>Hint:
     * <ol>
     *   <li>Call {@code talentHiveConnector.fetchCandidates(positionId)}.</li>
     *   <li>Look up the pipeline in {@code pipelines}. If absent, create a
     *       new {@link CandidatePipeline} and put it in the map.</li>
     *   <li>Call {@code pipeline.updateCandidates(fetched)}.</li>
     * </ol>
     * </p>
     *
     * @param positionId the Bankolino position to sync
     */
    @Override
    public void syncCandidates(String positionId) {
        System.out.println("[CandidateInsight] Syncing candidates for position: " + positionId);
        List<ICandidate> fetched = talentHiveConnector.fetchCandidates(positionId);

// Create pipeline on first sync for this position; reuse on subsequent syncs
        ICandidatePipeline pipeline = pipelines.get(positionId);
        if (pipeline == null) {
            pipeline = new CandidatePipeline(positionId);
            pipelines.put(positionId, pipeline);
        }
        pipeline.updateCandidates(fetched);
    }

    // --- UC4: View Candidate Pipeline --------------------------------------------

    /**
     * Returns all candidates for the given position.
     *
     * <p>Hint: call {@link #getPipelineOrThrow(String)}, then
     * {@link ICandidatePipeline#getCandidates()}.</p>
     *
     * @param positionId the position ID
     * @return list of candidates
     */
    @Override
    public List<ICandidate> viewPipeline(String positionId) {
        return getPipelineOrThrow(positionId).getCandidates();
    }

    // --- UC5: Review and Rate Candidate ------------------------------------------

    /**
     * Retrieves the dossier for a single candidate.
     *
     * <p>Hint: call {@link #getPipelineOrThrow(String)}, then
     * {@link ICandidatePipeline#getCandidate(String)}.</p>
     *
     * @param positionId  the position the candidate belongs to
     * @param candidateId the candidate to review
     * @return the candidate's dossier
     */
    @Override
    public ICandidate reviewCandidate(String positionId, String candidateId) {
        return getPipelineOrThrow(positionId).getCandidate(candidateId);
    }

    /**
     * Submits a rating for a candidate.
     *
     * <p>Hint:
     * <ol>
     *   <li>Retrieve the candidate via {@link #getPipelineOrThrow(String)} and
     *       {@link ICandidatePipeline#getCandidate(String)}.</li>
     *   <li>Create a new {@link Evaluation} object.</li>
     *   <li>Call {@link ICandidate#addEvaluation(Evaluation)} on the candidate.</li>
     *   <li>Print a log line to stdout.</li>
     * </ol>
     * </p>
     *
     * @param positionId  the position the candidate belongs to
     * @param candidateId the candidate being rated
     * @param evaluator   the hiring manager submitting the rating
     * @param rating      the typed rating
     * @param comment     free-text justification
     */
    @Override
    public void rateCandidate(String positionId, String candidateId, User evaluator,
                              EvaluationRating rating, String comment) {
        ICandidate candidate = getPipelineOrThrow(positionId).getCandidate(candidateId);
        candidate.addEvaluation(new Evaluation(evaluator, rating, comment));
        System.out.println("[CandidateInsight] Evaluation recorded for "
                + candidate.getName() + " " + candidate.getSurname()
                + " by " + evaluator.getName() + ": " + rating);
    }

    // --- UC6: View Evaluation Summary --------------------------------------------

    /**
     * Returns a ranked evaluation summary.
     *
     * <p>Hint: call {@link #getPipelineOrThrow(String)}, then
     * {@link ICandidatePipeline#getEvaluationSummary()}.</p>
     *
     * @param positionId the position to summarise
     * @return evaluation summary sorted by average score descending
     */
    @Override
    public EvaluationSummary viewEvaluationSummary(String positionId) {
        return getPipelineOrThrow(positionId).getEvaluationSummary();
    }

    // --- Internal Helpers --------------------------------------------------------

    /**
     * Returns the pipeline for the given position, or throws if not yet synced.
     * @param positionId the position to look up
     * @return the pipeline for this position
     * @throws IllegalStateException if no pipeline exists for positionId
     */
    private ICandidatePipeline getPipelineOrThrow(String positionId) {
        ICandidatePipeline pipeline = pipelines.get(positionId);
        if (pipeline == null) {
            throw new IllegalStateException(
                "No candidate pipeline for position '" + positionId
                + "'. Run syncCandidates() first.");
        }
        return pipeline;
    }
}
