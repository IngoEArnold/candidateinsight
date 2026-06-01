package de.bankolino.candidateinsight;

/**
 * Defines the roles available in CandidateInsight.
 * Using an enum instead of a String prevents invalid role values
 * and allows switch-based dispatch where needed.
 */
public enum Role {
    /** A Bankolino employee who evaluates candidates. */
    HIRING_MANAGER,
    /** An administrator who manages users and triggers data sync. */
    HR_ADMIN
}
