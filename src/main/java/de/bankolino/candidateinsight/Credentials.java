package de.bankolino.candidateinsight;

/**
 * Value object encapsulating login credentials.
 * Treated as immutable (no setters) to prevent accidental mutation.
 *
 * <p>Grouping email and password into a single object clarifies the
 * authenticate() method signature and makes it easy to extend
 * (e.g., adding MFA token) without changing callers.</p>
 */
public class Credentials {

    private final String email;
    private final String password;

    /**
     * @param email    the user's email address (unique account identifier)
     * @param password the user's password (plain text for simplicity; hash in production)
     */
    public Credentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /** @return the email address */
    public String getEmail() { return email; }

    /** @return the password */
    public String getPassword() { return password; }
}
