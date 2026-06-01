package de.bankolino.candidateinsight;

/**
 * Assembles the CandidateInsight application by wiring all components together.
 * No component instantiates its own collaborators - all wiring happens here.
 *
 * <p>Architecture note: the Composer pattern (a simplified Dependency Injection
 * container) centralises object graph construction. Changing which concrete class
 * implements an interface requires editing exactly one place: this file.</p>
 *
 * <p>In a production system, a DI framework (Spring, Guice) would replace
 * this class and manage object lifecycles automatically.</p>
 */
public class Composer {

    /**
     * Creates and wires the fully assembled CandidateInsight application.
     * @return a ready-to-use ICandidateInsight instance
     */
    public static ICandidateInsight compose() {
        // Instantiate concrete components
        IUserManagement userManagement          = new UserManagement();
        ITalentHiveConnector talentHiveConnector = new TalentHiveConnector();

        // Inject collaborators into the facade via constructor injection
        return new CandidateInsight(userManagement, talentHiveConnector);
    }
}
