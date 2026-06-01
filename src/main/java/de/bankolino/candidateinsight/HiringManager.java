package de.bankolino.candidateinsight;

/**
 * Represents a Bankolino hiring manager who evaluates candidates.
 * In future iterations, HiringManager may carry department, seniority level,
 * or workflow-approval authority.
 */
public class HiringManager extends User {

    /**
     * @param name     first name
     * @param surname  last name
     * @param email    unique login email
     * @param password initial password
     */
    public HiringManager(String name, String surname, String email, String password) {
        super(name, surname, email, password, Role.HIRING_MANAGER);
    }
}
