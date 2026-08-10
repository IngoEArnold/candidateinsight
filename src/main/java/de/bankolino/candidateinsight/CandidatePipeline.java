package de.bankolino.candidateinsight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages the list of candidates for one open position.
 * Acts as a local cache of TalentHive data; updated via syncCandidates().
 *
 * <p>Architecture note: CandidatePipeline is responsible for all candidate
 * aggregation logic (ranking, summary generation). CandidateInsight (the facade)
 * delegates to it rather than computing summaries itself - Single Responsibility.</p>
 */
public class CandidatePipeline implements ICandidatePipeline {

    private final String positionId;

    /**
     * Candidates keyed by candidateId for O(1) lookup.
     * LinkedHashMap preserves insertion order for stable display.
     */
    private final Map<String, ICandidate> candidates = new LinkedHashMap<String, ICandidate>();

    /**
     * @param positionId the position this pipeline manages
     */
    public CandidatePipeline(String positionId) {
        this.positionId = positionId;
    }

    /**
     * Adds new candidates to the pipeline.
     * Additive semantics: candidates already in the map keep their existing evaluations.
     *
     * <p>Hint: iterate over {@code newCandidates}. For each candidate,
     * add it to the map only if its ID is not already present
     * ({@link Map#containsKey(Object)}).</p>
     *
     * @param newCandidates list of candidates received from TalentHive
     */
    @Override
    public void updateCandidates(List<ICandidate> newCandidates) {
        // Only add candidates not already present (preserves existing evaluations)
        for (ICandidate c : newCandidates) {
            if (!candidates.containsKey(c.getCandidateId())) {
                candidates.put(c.getCandidateId(), c);
            }
        }
        System.out.println("[CandidatePipeline] Pipeline '" + positionId
                + "' updated: " + candidates.size() + " candidate(s).");
    }

    /**
     * Returns all candidates in the pipeline as a new list.
     * @return copy of the candidate list (changes to the returned list do not affect the pipeline)
     */
    @Override
    public List<ICandidate> getCandidates() {
        return new ArrayList<ICandidate>(candidates.values());
    }

    /**
     * Retrieves a candidate by ID.
     *
     * <p>Hint: use {@link Map#get(Object)} and throw
     * {@link IllegalArgumentException} if the result is null.</p>
     *
     * @param candidateId the unique candidate identifier
     * @return the candidate
     * @throws IllegalArgumentException if candidateId is unknown
     */
    @Override
    public ICandidate getCandidate(String candidateId) {
        ICandidate c = candidates.get(candidateId);
        if (c == null) {
            throw new IllegalArgumentException("Candidate not found: " + candidateId);
        }
        return c;
    }

    /**
     * Builds an EvaluationSummary: sorts all candidates by average score (descending).
     * Candidates with no evaluations appear at the bottom (score 0.0).
     *
     * <p>Hint: copy the map values into an ArrayList, then sort using
     * {@link Collections#sort(List, Comparator)} with an anonymous Comparator
     * that calls {@link Double#compare(double, double)} on average scores.
     * Remember: descending order means comparing b to a, not a to b.</p>
     *
     * @return evaluation summary for this position
     */
    @Override
    public EvaluationSummary getEvaluationSummary() {
        List<ICandidate> ranked = new ArrayList<ICandidate>(candidates.values());

// Sort by average score descending using an explicit Comparator (no lambdas)
        Collections.sort(ranked, new Comparator<ICandidate>() {
            @Override
            public int compare(ICandidate a, ICandidate b) {
                return Double.compare(b.getAverageScore(), a.getAverageScore());
            }
        });

        return new EvaluationSummary(positionId, ranked);
    }
}
