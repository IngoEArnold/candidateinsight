package de.bankolino.candidateinsight;

import java.util.List;

/**
 * Interface for the TalentHive integration boundary.
 * Decouples CandidateInsight from the concrete TalentHive API implementation.
 * Enables easy substitution with a mock or stub in tests.
 *
 * <p>Architecture note: this interface represents the Anti-Corruption Layer
 * boundary between Bankolino's internal model and TalentHive's external model.</p>
 */
public interface ITalentHiveConnector {

    /**
     * Fetches the current list of candidates for the given position from TalentHive.
     * @param positionId the Bankolino position identifier
     * @return list of candidates as reported by TalentHive
     */
    List<ICandidate> fetchCandidates(String positionId);
}
