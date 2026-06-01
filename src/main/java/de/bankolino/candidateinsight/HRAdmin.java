package de.bankolino.candidateinsight;

/**
 * Represents a CandidateInsight HR administrator.
 * HR admins register new users and trigger candidate pipeline synchronisation
 * from TalentHive.
 */
public class HRAdmin extends User {

    /**
     * @param name     first name
     * @param surname  last name
     * @param email    unique login email
     * @param password initial password
     */
    public HRAdmin(String name, String surname, String email, String password) {
        super(name, surname, email, password, Role.HR_ADMIN);
    }
}
