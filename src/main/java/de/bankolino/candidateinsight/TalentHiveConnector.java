package de.bankolino.candidateinsight;

import java.util.ArrayList;
import java.util.List;

/**
 * Simulates the TalentHive external system.
 * In production, this class would call the TalentHive REST API,
 * handle authentication, and map TalentHive's data model to
 * CandidateInsight's internal ICandidate representation.
 *
 * <p>For this prototype, candidate data is hardcoded to keep the
 * focus on architecture rather than infrastructure plumbing.</p>
 */
public class TalentHiveConnector implements ITalentHiveConnector {

    /**
     * Returns simulated candidate data for the given position.
     * In production: HTTP GET /positions/{positionId}/candidates
     *
     * <p>Hint: create three {@link Candidate} objects with IDs "C001", "C002", "C003"
     * and add them to a list. Use realistic names (first name, last name).</p>
     *
     * @param positionId the position to fetch candidates for
     * @return list of simulated candidates
     */
    @Override
    public List<ICandidate> fetchCandidates(String positionId) {
        // TODO [Unit03 - Step 2a]: Implement simulated TalentHive response.
        //   1. Print a log line to stdout (e.g., "[TalentHiveConnector] Fetching candidates for ...").
        //   2. Create a List<ICandidate> and add three Candidate objects:
        //      - new Candidate("C001", "Max",  "Mueller", positionId)
        //      - new Candidate("C002", "Sara", "Schmidt", positionId)
        //      - new Candidate("C003", "Leon", "Fischer", positionId)
        //   3. Return the list.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
