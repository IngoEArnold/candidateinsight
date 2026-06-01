package de.bankolino.candidateinsight;

/**
 * Interface for user registration and authentication.
 * Isolates CandidateInsight (the facade) from the concrete
 * implementation of user persistence and credential verification.
 */
public interface IUserManagement {

    /**
     * Registers a new user in CandidateInsight.
     * @param name     first name
     * @param surname  last name
     * @param email    unique email (login identifier)
     * @param password initial password
     * @param role     HIRING_MANAGER or HR_ADMIN
     * @return the created User object
     * @throws IllegalArgumentException if email is already registered
     */
    User registerUser(String name, String surname, String email, String password, Role role);

    /**
     * Authenticates a user by credentials.
     * @param credentials email + password
     * @return the authenticated User
     * @throws IllegalArgumentException if credentials are invalid
     */
    User authenticate(Credentials credentials);
}
